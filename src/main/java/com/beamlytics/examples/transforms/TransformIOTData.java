package com.beamlytics.examples.transforms;


import org.apache.beam.sdk.schemas.NoSuchSchemaException;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.schemas.transforms.Group;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.Row;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.joda.time.Duration;

import com.beamlytics.examples.options.IOTDataPipelineOptions;
import com.beamlytics.examples.schema.IOTDataSchema;
import com.beamlytics.examples.utils.JSONUtils;


public class TransformIOTData extends PTransform< PCollection<String>,PCollection<Row> >{

    
    
    @Override
    public @UnknownKeyFor @NonNull @Initialized PCollection<Row> expand(
            @UnknownKeyFor @NonNull @Initialized PCollection<String> input) {


                IOTDataPipelineOptions options =
                input.getPipeline().getOptions().as(IOTDataPipelineOptions.class);

                Schema iotDataSchema = null;
    

                try {
            iotDataSchema = input.getPipeline().getSchemaRegistry().getSchema(IOTDataSchema.class);
        } catch (NoSuchSchemaException e) {
            throw new IllegalArgumentException("Unable to get Schema for ClickStreamEvent class.");
        }

        PCollection<Row> iotRows =
                input.apply(JSONUtils.JSONtoRowWithDeadLetterSink.withSchema(iotDataSchema));

        final PCollection<Row> windowed_iotRows = iotRows
                                            .apply("WindowFn", Window.<Row>into(FixedWindows.of(Duration.standardMinutes(1) )))
                                                                           //.withAllowedLateness(Duration.standardSeconds(10)))
                                             //.triggering(AfterWatermark.pastEndOfWindow())
                                             //.discardingFiredPanes());
                                             .apply(Group.<Row>byFieldNames("device_id").aggregateField("monitored_value", org.apache.beam.sdk.transforms.Mean.of(), "average"));
                                                         //.aggregateField("monitored_value",Count.combineFn(),"count"));

        

        return windowed_iotRows;

                

                //return input.apply("NewTransform1",ParDo.of(new MyDoFn()));




}
}




