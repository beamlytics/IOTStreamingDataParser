package com.beamlytics.examples.schema;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.AutoValueSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;
import org.joda.time.Instant;


import com.google.auto.value.AutoValue;

@AutoValue
@DefaultSchema(AutoValueSchema.class)
public abstract class IOTDataSchema {



  @SchemaFieldName("event_timestamp")
  public @Nullable abstract Instant getEventTimestamp();

  @SchemaFieldName("device_id")
  public @Nullable abstract String getDeviceId();

  @SchemaFieldName("device_location")
  public @Nullable abstract Location getDeviceLocation();

  // what is being monitored : temperature, wind velocity, stream velocity, blah
  // blah
  // TODO : Need to build a validation of what can be monitored using an enum
  @SchemaFieldName("monitored_attribute")
  public @Nullable abstract String getMonitoredAttribute();

  // what unit the data will be sent by device
  // for temp, it could be "F" or "C"
  // TODO : Need to build validation for monitored unit for monitored attribute,
  // based on map of attribute and unit
  @SchemaFieldName("monitored_unit")
  public @Nullable abstract String getMonitoredUnit();

  @SchemaFieldName("monitored_value")
  public @Nullable abstract Double getMonitoredValue();

  public abstract Builder toBuilder();

  public static Builder builder() {
    return new AutoValue_IOTDataSchema.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

  

    public abstract Builder setEventTimestamp(Instant value);

    public abstract Builder setDeviceId(String value);

    public abstract Builder setDeviceLocation(Location value);

    public abstract Builder setMonitoredAttribute(String value);

    public abstract Builder setMonitoredUnit(String value);

    public abstract Builder setMonitoredValue(Double value);

    // Builds a new IOTDataSchema object.

    public abstract IOTDataSchema build();

  }



}
