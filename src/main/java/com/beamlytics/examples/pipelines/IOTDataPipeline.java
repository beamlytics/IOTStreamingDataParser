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
package com.beamlytics.examples.pipelines;

import static org.apache.beam.vendor.guava.v32_1_2_jre.com.google.common.base.Preconditions.*;

import java.util.logging.Logger;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.ToJson;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.Row;

import com.beamlytics.examples.options.IOTDataPipelineOptions;
import com.beamlytics.examples.schema.IOTDataSchema;
import com.beamlytics.examples.transforms.TransformIOTData;
import com.beamlytics.examples.utils.Print;
import com.google.common.annotations.VisibleForTesting;



/**
 * A starter example for writing Beam programs.
 *
 * <p>The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>To run this starter example locally using DirectRunner, just
 * execute it without any additional parameters from your favorite development
 * environment.
 *
 * <p>To run this starter example using managed resource in Google Cloud
 * Platform, you should specify the following command-line options:
 *   --project=<YOUR_PROJECT_ID>
 *   --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>
 *   --runner=DataflowRunner
 */
public class IOTDataPipeline {
  private static final Logger LOG = Logger.getLogger(IOTDataPipeline.class.getName());
  //private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);getLogger(StarterPipeline.class);
 
  @VisibleForTesting public static PCollection<IOTDataSchema> testEvents = null;

@SuppressWarnings({ "rawtypes", "unchecked" })
public void startPipeline(Pipeline p) throws Exception
{
   IOTDataPipelineOptions options = p.getOptions().as(IOTDataPipelineOptions.class);
   Boolean prodMode  = !options.getTestMode();

   PCollection<String> inputCollection = null;
   if (prodMode) {
    inputCollection =
          p.apply(
              "ReadIOTStream",
              PubsubIO.readStrings()
                  .fromSubscription(options.getTopicName())
                  .withTimestampAttribute("TIMESTAMP"));
    } else {
      checkNotNull(testEvents, "In TestMode you must set testEvents");
      inputCollection = testEvents.apply(ToJson.of());
    }
  
  PCollection<Row> transformed_iot_data = inputCollection.apply(new TransformIOTData());
  //PCollection<KV<String,Integer>> tranformed2  = transformed1.apply(new NewTransform2());
  
  
  
  transformed_iot_data.apply("Printing",ParDo.of(new Print("printing final output ")));


  

    
  p.run();
}


  
  public static void main(String[] args) {
    Pipeline p = Pipeline.create(
        PipelineOptionsFactory.fromArgs(args).withValidation().create());
  try {
      new IOTDataPipeline().startPipeline(p);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }   
    
  }

  
}