package com.beamlytics.examples.utils;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import org.apache.beam.sdk.schemas.Schema;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_JSONUtils_JSONtoRowWithDeadLetterSink extends JSONUtils.JSONtoRowWithDeadLetterSink {

  @Nullable
  private final DeadLetterSink.SinkType sinkType;

  private final Schema schema;

  private AutoValue_JSONUtils_JSONtoRowWithDeadLetterSink(
      @Nullable DeadLetterSink.SinkType sinkType,
      Schema schema) {
    this.sinkType = sinkType;
    this.schema = schema;
  }

  @Nullable
  @Override
  DeadLetterSink.SinkType getSinkType() {
    return sinkType;
  }

  @Override
  Schema getSchema() {
    return schema;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof JSONUtils.JSONtoRowWithDeadLetterSink) {
      JSONUtils.JSONtoRowWithDeadLetterSink that = (JSONUtils.JSONtoRowWithDeadLetterSink) o;
      return (this.sinkType == null ? that.getSinkType() == null : this.sinkType.equals(that.getSinkType()))
          && this.schema.equals(that.getSchema());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (sinkType == null) ? 0 : sinkType.hashCode();
    h$ *= 1000003;
    h$ ^= schema.hashCode();
    return h$;
  }

  static final class Builder extends JSONUtils.JSONtoRowWithDeadLetterSink.Builder {
    private DeadLetterSink.SinkType sinkType;
    private Schema schema;
    Builder() {
    }
    @Override
    public JSONUtils.JSONtoRowWithDeadLetterSink.Builder setSinkType(DeadLetterSink.SinkType sinkType) {
      this.sinkType = sinkType;
      return this;
    }
    @Override
    public JSONUtils.JSONtoRowWithDeadLetterSink.Builder setSchema(Schema schema) {
      if (schema == null) {
        throw new NullPointerException("Null schema");
      }
      this.schema = schema;
      return this;
    }
    @Override
    public JSONUtils.JSONtoRowWithDeadLetterSink build() {
      if (this.schema == null) {
        String missing = " schema";
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_JSONUtils_JSONtoRowWithDeadLetterSink(
          this.sinkType,
          this.schema);
    }
  }

}
