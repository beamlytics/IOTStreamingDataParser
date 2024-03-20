package com.beamlytics.examples.schema;

import com.google.auto.value.AutoValue;
import org.apache.beam.sdk.schemas.AutoValueSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;
import org.joda.time.Instant;

import javax.annotation.Nullable;

@AutoValue
@DefaultSchema(AutoValueSchema.class)
public abstract class TempAvgAggregator {

    public static Builder builder() {

        return new AutoValue_TempAvgAggregator.Builder();
    }

    @SchemaFieldName("device_id")
    public @Nullable
    abstract String getDeviceId();

    @SchemaFieldName("monitored_attribute")
    public @Nullable
    abstract String getMonitoredAttribute();

    @SchemaFieldName("monitored_attribute_unit")
    public @Nullable
    abstract String getMonitoredAttributeUnit();

    @SchemaFieldName("monitored_attribute_average")
    public @Nullable
    abstract Double getMonitoredAttributeAverage();

    @SchemaFieldName("start_time")
    public @Nullable
    abstract Instant getStartTime();

    @SchemaFieldName("durationMS")
    public @Nullable
    abstract Long getDurationms();

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setDeviceId(String value);

        public abstract Builder setMonitoredAttribute(String value);


        public abstract Builder setMonitoredAttributeUnit(String value);


        public abstract Builder setMonitoredAttributeAverage(Double value);

        public abstract Builder setStartTime(Instant value);

        public abstract Builder setDurationms(Long value);

        public abstract TempAvgAggregator build();
    }
}