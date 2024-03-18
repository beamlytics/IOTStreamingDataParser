package com.beamlytics.examples.transforms;


import org.apache.beam.sdk.schemas.NoSuchSchemaException;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.schemas.Schema.Field;
import org.apache.beam.sdk.schemas.Schema.FieldType;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.OutputReceiver;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;
import org.apache.beam.sdk.transforms.DoFn.Timestamp;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
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
import com.beamlytics.examples.schema.IOTDataSchema.TempAvgAggregator;
import javax.annotation.Nullable;
import org.apache.beam.sdk.schemas.transforms.*;
import org.joda.time.Instant;




public class TransformIOTData extends PTransform< PCollection<String>,PCollection<Row> >{

     Duration pageViewCountWindowDuration;

    public TransformIOTData(Duration pageViewCountWindowDuration) {
    this.pageViewCountWindowDuration = pageViewCountWindowDuration;
    }

    public TransformIOTData(@Nullable String name, Duration pageViewCountWindowDuration) {
    super(name);
    this.pageViewCountWindowDuration = pageViewCountWindowDuration;
    }

    
    
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
                                             .apply(Group.<Row>byFieldNames("device_id")
                                                        .aggregateField("monitored_value", org.apache.beam.sdk.transforms.Mean.of(), "average"));
                                            
                                             .apply(CreateAverageViewAggregatorMetadata.create(pageViewCountWindowDuration.toMillis()));  
        

        return windowed_iotRows;
}


public static class CreateAverageViewAggregatorMetadata
      extends PTransform<PCollection<Row>, PCollection<TempAvgAggregator>> {

    Long durationMS;

    public static CreateAverageViewAggregatorMetadata create(Long durationMS) {
      return new CreateAverageViewAggregatorMetadata(durationMS);
    }

    public CreateAverageViewAggregatorMetadata(Long durationMS) {
      this.durationMS = durationMS;
    }

    public CreateAverageViewAggregatorMetadata(@Nullable String name, Long durationMS) {
      super(name);
      this.durationMS = durationMS;
    }

    @Override
    public PCollection<TempAvgAggregator> expand(PCollection<Row> input) {

      // TODO #18 the schema registry for PageViewAggregator throws a class cast issue
      Schema schema =
          Schema.of(
              Field.of("page", FieldType.STRING),
              Field.of("count", FieldType.INT64),
              Field.of("startTime", FieldType.INT64),
              Field.of("durationMS", FieldType.INT64));

      return input
          // Note key and value are results of Group + Count operation in the previous transform.
          .apply(Select.fieldNames("key.page", "value.count"))
          // We need to add these fields to the ROW object before we convert the POJO
          .apply(
              AddFields.<Row>create()
                  .field("startTime", FieldType.INT64)
                  .field("durationMS", FieldType.INT64))
          .apply(
              ParDo.of(
                  new DoFn<Row, Row>() {
                    @ProcessElement
                    public void process(
                        @Element Row input, @Timestamp Instant time, OutputReceiver<Row> o) {
                      // The default timestamp attached to a combined value is the end of the window
                      // To find the start of the window we deduct the duration + 1 as beam windows
                      // are (start,end] with epsilon of 1 ms
                      Row row =
                          Row.fromRow(input)
                              .withFieldValue("durationMS", durationMS)
                              .withFieldValue("startTime", time.getMillis() - durationMS + 1)
                              .build();
                      o.output(row);
                    }
                  }))
          .setRowSchema(schema)
          .apply(Convert.fromRows(TempAvgAggregator.class));
    }
  }


}




