package com.beamlytics.examples.transforms;


import org.apache.beam.sdk.schemas.NoSuchSchemaException;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.Row;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;

import com.beamlytics.examples.options.StarterPipelineOptions;
import com.beamlytics.examples.schema.TestSchema.EventSchema;
import com.beamlytics.examples.utils.JSONUtils;


public class NewTransform1 extends PTransform< PCollection<String>,PCollection<Row> >{

    @Override
    public @UnknownKeyFor @NonNull @Initialized PCollection<Row> expand(
            @UnknownKeyFor @NonNull @Initialized PCollection<String> input) {


                StarterPipelineOptions options =
                input.getPipeline().getOptions().as(StarterPipelineOptions.class);

                Schema csEvtSchema = null;
    

                try {
            csEvtSchema = input.getPipeline().getSchemaRegistry().getSchema(EventSchema.class);
        } catch (NoSuchSchemaException e) {
            throw new IllegalArgumentException("Unable to get Schema for ClickStreamEvent class.");
        }

        PCollection<Row> csEvtRows =
                input.apply(JSONUtils.JSONtoRowWithDeadLetterSink.withSchema(csEvtSchema));

                return csEvtRows;

                //return input.apply("NewTransform1",ParDo.of(new MyDoFn()));




}
}




