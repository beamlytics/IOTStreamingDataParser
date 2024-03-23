package com.beamlytics.examples.transforms;


import org.apache.beam.sdk.schemas.NoSuchSchemaException;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.schemas.Schema.Field;
import org.apache.beam.sdk.schemas.Schema.FieldType;
import org.apache.beam.sdk.transforms.DoFn;
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
import com.beamlytics.examples.schema.TempAvgAggregator;
import com.beamlytics.examples.utils.JSONUtils;

import javax.annotation.Nullable;
import org.apache.beam.sdk.schemas.transforms.*;
import org.joda.time.Instant;




public class TransformIOTData extends PTransform< PCollection<String>,PCollection<Row> >{

    Duration averageWindowDuration;

    public TransformIOTData(Duration averageWindowDuration) {
    this.averageWindowDuration = averageWindowDuration;
    }

    public TransformIOTData(@Nullable String name, Duration averageWindowDuration) {
    super(name);
    this.averageWindowDuration = averageWindowDuration;
    }


    //TODO: #1 add side input to read data from device master data
    @Override

    public @UnknownKeyFor @NonNull @Initialized PCollection<Row> expand(
            @UnknownKeyFor @NonNull @Initialized PCollection<String> input) {


                IOTDataPipelineOptions options =
                input.getPipeline().getOptions().as(IOTDataPipelineOptions.class);

                Schema iotDataSchema = null;
    

                try {
                    //TODO: #2 can we read schema from a device master data instead of reading from class file
                    iotDataSchema = input.getPipeline().getSchemaRegistry().getSchema(IOTDataSchema.class);
        } catch (NoSuchSchemaException e) {
            throw new IllegalArgumentException("Unable to get Schema for ClickStreamEvent class.");
        }


        // TODO: #3 After we have enriched the data with master device data, check if alert conidiotns are met
        //  and send the alerts in real-time to queue

        PCollection<Row> iotRows =
                input.apply(JSONUtils.JSONtoRowWithDeadLetterSink.withSchema(iotDataSchema));

        //TODO: #9 Use a filter to start different pipeline depending on device type, measurement type etc. EG following code is just doing time window average for temp, it should be moved to a different pipeline. 
        // A well-depth monitor could have different needs to be monitored



        // TODO: #7 Ensure widowing is done based on event time at Source
        final PCollection<Row> windowed_iotRows = iotRows
                                            .apply("WindowFn", Window.<Row>into(FixedWindows.of(Duration.standardMinutes(1) )))
        //TODO: #6 allowed latness and triggering needs to be configured in master data for devices based on what is being measured and important of events

                                             //.withAllowedLateness(Duration.standardSeconds(10)))
                                             //.triggering(AfterWatermark.pastEndOfWindow())
                                             //.discardingFiredPanes());
                //TODO: #4 instead of hard coding the attributes to group by , use the configuration read from device master data
                                             .apply(Group.<Row>byFieldNames("device_id","monitored_unit","monitored_attribute" )
                                                        .aggregateField("monitored_value", org.apache.beam.sdk.transforms.Mean.of(), "monitored_attribute_average"))
                                            .apply(CreateAverageViewAggregatorMetadata.create(averageWindowDuration.getMillis()));
        

        return windowed_iotRows;


        //TODO: #10 provide capability to merge 2 or more pipelines and apply composite alerting and monitorring e.g. temp>>200 and humidity<<30
        // This can be configured in master data as well

}


public static class CreateAverageViewAggregatorMetadata
      extends PTransform<PCollection<Row>, PCollection<Row>> {

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
    public PCollection<Row> expand(PCollection<Row> input) {

      // TODO #5 : this schema should also be made configurable in device master data
      Schema schema =
          Schema.of(
              Field.of("device_id", FieldType.STRING),
              Field.of("monitored_attribute", FieldType.STRING),
              Field.of("monitored_attribute_unit", FieldType.STRING),
              Field.of("monitored_attribute_average", FieldType.DOUBLE),
              Field.of("startTime", FieldType.INT64),
              Field.of("durationMS", FieldType.INT64)
              );

      return input
          // Note key and value are results of Group + Count operation in the previous transform.
          .apply(Select.fieldNames("key.device_id","key.monitored_attribute","key.monitored_unit", "value.monitored_attribute_average"))
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
                               .withFieldValue("startTime", time.getMillis() - durationMS + 1)
                               .withFieldValue("durationMS", durationMS)
                               .build();
                      o.output(row);
                    }
                  }))
          .setRowSchema(schema);
          //TODO: #8 check why this errors out to convert from rows to schema
          //.apply(Convert.fromRows(TempAvgAggregator.class));
    }
  }


}




