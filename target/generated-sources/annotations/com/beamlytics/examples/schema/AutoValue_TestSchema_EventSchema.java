package com.beamlytics.examples.schema;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;
import org.joda.time.Instant;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_TestSchema_EventSchema extends TestSchema.EventSchema {

  @Nullable
  private final String eventTime;

  @Nullable
  private final String event;

  @Nullable
  private final Instant timestamp;

  @Nullable
  private final Long timestamp2;

  @Nullable
  private final Long uid;

  private AutoValue_TestSchema_EventSchema(
      @Nullable String eventTime,
      @Nullable String event,
      @Nullable Instant timestamp,
      @Nullable Long timestamp2,
      @Nullable Long uid) {
    this.eventTime = eventTime;
    this.event = event;
    this.timestamp = timestamp;
    this.timestamp2 = timestamp2;
    this.uid = uid;
  }

  @Nullable
  @SchemaFieldName("event_datetime")
  @Override
  public String getEventTime() {
    return eventTime;
  }

  @Nullable
  @SchemaFieldName("event")
  @Override
  public String getEvent() {
    return event;
  }

  @Nullable
  @SchemaFieldName("timestamp")
  @Override
  public Instant getTimestamp() {
    return timestamp;
  }

  @Nullable
  @SchemaFieldName("timestamp2")
  @Override
  public Long getTimestamp2() {
    return timestamp2;
  }

  @Nullable
  @SchemaFieldName("user_id")
  @Override
  public Long getUid() {
    return uid;
  }

  @Override
  public String toString() {
    return "EventSchema{"
        + "eventTime=" + eventTime + ", "
        + "event=" + event + ", "
        + "timestamp=" + timestamp + ", "
        + "timestamp2=" + timestamp2 + ", "
        + "uid=" + uid
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof TestSchema.EventSchema) {
      TestSchema.EventSchema that = (TestSchema.EventSchema) o;
      return (this.eventTime == null ? that.getEventTime() == null : this.eventTime.equals(that.getEventTime()))
          && (this.event == null ? that.getEvent() == null : this.event.equals(that.getEvent()))
          && (this.timestamp == null ? that.getTimestamp() == null : this.timestamp.equals(that.getTimestamp()))
          && (this.timestamp2 == null ? that.getTimestamp2() == null : this.timestamp2.equals(that.getTimestamp2()))
          && (this.uid == null ? that.getUid() == null : this.uid.equals(that.getUid()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (eventTime == null) ? 0 : eventTime.hashCode();
    h$ *= 1000003;
    h$ ^= (event == null) ? 0 : event.hashCode();
    h$ *= 1000003;
    h$ ^= (timestamp == null) ? 0 : timestamp.hashCode();
    h$ *= 1000003;
    h$ ^= (timestamp2 == null) ? 0 : timestamp2.hashCode();
    h$ *= 1000003;
    h$ ^= (uid == null) ? 0 : uid.hashCode();
    return h$;
  }

  @Override
  public TestSchema.EventSchema.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends TestSchema.EventSchema.Builder {
    private String eventTime;
    private String event;
    private Instant timestamp;
    private Long timestamp2;
    private Long uid;
    Builder() {
    }
    private Builder(TestSchema.EventSchema source) {
      this.eventTime = source.getEventTime();
      this.event = source.getEvent();
      this.timestamp = source.getTimestamp();
      this.timestamp2 = source.getTimestamp2();
      this.uid = source.getUid();
    }
    @Override
    public TestSchema.EventSchema.Builder setEventTime(String eventTime) {
      this.eventTime = eventTime;
      return this;
    }
    @Override
    public TestSchema.EventSchema.Builder setEvent(String event) {
      this.event = event;
      return this;
    }
    @Override
    public TestSchema.EventSchema.Builder setTimestamp(Instant timestamp) {
      this.timestamp = timestamp;
      return this;
    }
    @Override
    public TestSchema.EventSchema.Builder setTimestamp2(Long timestamp2) {
      this.timestamp2 = timestamp2;
      return this;
    }
    @Override
    public TestSchema.EventSchema.Builder setUid(Long uid) {
      this.uid = uid;
      return this;
    }
    @Override
    public TestSchema.EventSchema build() {
      return new AutoValue_TestSchema_EventSchema(
          this.eventTime,
          this.event,
          this.timestamp,
          this.timestamp2,
          this.uid);
    }
  }

}
