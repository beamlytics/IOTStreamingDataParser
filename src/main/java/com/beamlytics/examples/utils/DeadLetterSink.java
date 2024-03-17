/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beamlytics.examples.utils;

import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.CreateDisposition;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.WriteDisposition;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beamlytics.examples.options.IOTDataPipelineOptions;
import com.beamlytics.examples.utils.DeploymentAnnotations.NoPartialResultsOnDrain;

/**
 * Output Raw Error Messages to DeadLetter sinks.
 *
 * <p>Log {@link SinkType#LOG} output and BigQuery {@link SinkType#BIGQUERY} are currently
 * supported.
 *
 * <p>--BigQuery Sink
 *
 * <p>Errors are sent to {@link RetailPipelineReportingOptions#getDeadLetterTable()}
 */
@NoPartialResultsOnDrain
//@Experimental
public class DeadLetterSink extends PTransform<PCollection<ErrorMsg>, PDone> {

  private static final Logger LOG = LoggerFactory.getLogger(DeadLetterSink.class);

  // Enum which defines the sink type for DeadLetter sink.
  public enum SinkType {
    BIGQUERY,
    LOG
  }

  private SinkType sinkType;

  private static final Instant MAX_DATE = Instant.parse("9999-12-31");
  private static final Instant MIN_DATE = Instant.parse("0001-01-01");

  // Hide default constructor
  private DeadLetterSink() {}
  ;

  public static DeadLetterSink createSink(SinkType type) {

    switch (type) {
      case BIGQUERY:
        {
          return createBigQuerySink();
        }
      case LOG:
        {
          return createLogSink();
        }
    }
    throw new IllegalArgumentException("Type can not be null");
  }

  private static DeadLetterSink createBigQuerySink() {
    DeadLetterSink sink = new DeadLetterSink();
    sink.sinkType = SinkType.BIGQUERY;
    return sink;
  }

  private static DeadLetterSink createLogSink() {
    DeadLetterSink sink = new DeadLetterSink();
    sink.sinkType = SinkType.LOG;
    return sink;
  }

  @Override
  public PDone expand(PCollection<ErrorMsg> input) {

    IOTDataPipelineOptions options =
        input.getPipeline().getOptions().as(IOTDataPipelineOptions.class);

    if (sinkType.equals(SinkType.BIGQUERY) && !options.getTestMode()) {

      String table =
          String.format(
              "%s:%s", options.getDataWarehouseOutputProject(), options.getDeadLetterTable());

      // BigQuery does not support Timestamps outside of 0001 to 9999 range. We move to Max.
      // Values larger than 9999 are possible if the elements are in a Global Window.

      // TODO Protect from NPE
      input
          .apply(
              MapElements.into(TypeDescriptor.of(ErrorMsg.class))
                  .via(
                      x ->
                          x.toBuilder()
                              .setTimestamp(
                                  (x.getTimestamp() == null
                                          || x.getTimestamp().isAfter(MAX_DATE)
                                          || x.getTimestamp().isBefore(MIN_DATE))
                                      ? MAX_DATE
                                      : x.getTimestamp())
                              .build()))
          .apply(
              BigQueryIO.<ErrorMsg>write()
                  .withWriteDisposition(WriteDisposition.WRITE_APPEND)
                  .withCreateDisposition(CreateDisposition.CREATE_IF_NEEDED)
                  .to(table)
                  .useBeamSchema());
    }

    if (sinkType.equals(SinkType.LOG) || options.getTestMode()) {
      input.apply(
          MapElements.into(TypeDescriptors.strings())
              .via(
                  x -> {
                    LOG.info(
                        String.format(
                            "%s failed with %s at %s in %s",
                            x.getData(), x.getError(), x.getTimestamp(), x.getTransform()));
                    return "";
                  }));
    }

    return PDone.in(input.getPipeline());
  }
}
