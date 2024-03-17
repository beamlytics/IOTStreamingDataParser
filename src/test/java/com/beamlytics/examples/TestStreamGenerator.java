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
package com.beamlytics.examples;

import org.apache.beam.sdk.io.GenerateSequence;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beamlytics.examples.schema.IOTDataSchema;
import com.beamlytics.examples.schema.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** Small testing injector, to be used for integration testing. */
public class TestStreamGenerator extends PTransform<PBegin, PCollection<IOTDataSchema>> {

  private static final Logger LOG = LoggerFactory.getLogger(TestStreamGenerator.class);



  @Override
  public PCollection<IOTDataSchema> expand(PBegin input) {

    return input
        //.apply(GenerateSequence.from(0).to(3).withRate(1, Duration.standardSeconds(5)))
        .apply(GenerateSequence.from(0).to(5).withRate(1, Duration.standardSeconds(1)))
        .apply(ParDo.of(new CreateEventSchemaStream()));

  }

  private static class CreateEventSchemaStream extends DoFn<Long, IOTDataSchema> {
    Gson gson = null;

    DateTimeFormatter fm;

    @Setup
    public void setUp() {
      gson = new GsonBuilder().serializeNulls().create();
      fm = DateTimeFormat.forPattern("yyyy-MM-dd HH:MM:SS");
    }

    /**
     * @param pc
     * @param time
     */
    @ProcessElement
    public void process(ProcessContext pc, @Timestamp Instant time) {

    for (int i=1 ; i<5 ; i++){

      Location location_rand =  Location.builder()
      .setLatitude(Double.toString(new java.util.Random().nextDouble()*(36.3524-36.3390)+36.3390))
      .setLongitude(Double.toString(new java.util.Random().nextDouble()*(94.1432-94.1292)+94.1292))
      .setLocationId(Integer.toString(i%3))
      .build();

          IOTDataSchema iot_data =
            IOTDataSchema.builder()
                .setDeviceId("device-"+ i % 3)
                .setDeviceLocation(location_rand)
                .setMonitoredAttribute("temperature")
                .setMonitoredUnit("celsius")
                .setMonitoredValue(new java.util.Random().nextDouble()*(30-20)+20)
                .setEventTimestamp(Instant.now())
                .build();


        pc.outputWithTimestamp(iot_data, time);
        LOG.debug(String.format("Generating Msg: %s", gson.toJson(iot_data)));
    }
      

      }//processElement closes
    }//DoFn closes
  }//TestStreamGenerator closes

