package com.beamlytics.examples;


import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.junit.Rule;
import org.junit.Test;

import com.beamlytics.examples.options.StarterPipelineOptions;
import com.beamlytics.examples.pipelines.StarterPipeline;
import com.beamlytics.examples.schema.TestSchema.EventSchema;


public class StarterPipelineTest {

StarterPipelineOptions options = PipelineOptionsFactory.as(StarterPipelineOptions.class);

  {
    options.setTestMode(true);
  }

  @Rule public transient TestPipeline p = TestPipeline.fromOptions(options);



  /**
   *
   */
  @Test
  public void testPipeline() {
    
    @UnknownKeyFor @NonNull @Initialized PCollection<EventSchema> input =  p.apply(new TestStreamGenerator());

StarterPipeline starterPipeline  = new StarterPipeline();

starterPipeline.testEvents = input;
try {
  starterPipeline.startPipeline(p);
} catch (Exception e) {

  e.printStackTrace();
}



   

   // @SuppressWarnings("rawtypes")
    //PCollection<String> stage1_Transformed = input.apply("convert to string",  ToJson.of());
    //stage1_Transformed.apply(new NewTransform1());
    //PAssert.that(stage1_Transformed).containsInAnyOrder("HELLO", "WORLD");

    //PCollection<KV<String,Integer>> stage2_Transformed = stage1_Transformed.apply(new NewTransform2());
    //PAssert.that(stage2_Transformed).containsInAnyOrder(KV.of("HELLO", 5), KV.of("WORLD", 5));
    //p.run().waitUntilFinish();
  }
}

