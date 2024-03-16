package com.beamlytics.examples.schema;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.AutoValueSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;
import org.joda.time.Instant;

import com.google.auto.value.AutoValue;


public class TestSchema {
    

    @AutoValue
    @DefaultSchema(AutoValueSchema.class)
    public static abstract class EventSchema {

    @SchemaFieldName("event_datetime")
    public @Nullable abstract String getEventTime();

    @SchemaFieldName("event")
    public @Nullable abstract String getEvent();

    @SchemaFieldName("timestamp")
    public @Nullable abstract Instant getTimestamp();

    @SchemaFieldName("timestamp2")
    public @Nullable abstract Long getTimestamp2();

    @SchemaFieldName("user_id")
    public @Nullable abstract Long getUid();

    public abstract Builder toBuilder();

    public static Builder builder() {
      return new AutoValue_TestSchema_EventSchema.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

      public abstract Builder setEventTime(String value);

      public abstract Builder setEvent(String value);

      public  abstract Builder setTimestamp(Instant value);

      public  abstract Builder setTimestamp2(Long value);
    //   {

    //     //return LogicalTypes.timestampMicros().addToSchema(AutoValue_TestSchema_EventSchema).

    //   }

      public abstract Builder setUid(Long value);

      public abstract EventSchema build();
    }



    }
}
