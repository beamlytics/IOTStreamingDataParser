package com.beamlytics.examples.utils;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import org.joda.time.Instant;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ErrorMsg extends ErrorMsg {

  @Nullable
  private final String transform;

  @Nullable
  private final String error;

  @Nullable
  private final String data;

  @Nullable
  private final Instant timestamp;

  private AutoValue_ErrorMsg(
      @Nullable String transform,
      @Nullable String error,
      @Nullable String data,
      @Nullable Instant timestamp) {
    this.transform = transform;
    this.error = error;
    this.data = data;
    this.timestamp = timestamp;
  }

  @Nullable
  @Override
  public String getTransform() {
    return transform;
  }

  @Nullable
  @Override
  public String getError() {
    return error;
  }

  @Nullable
  @Override
  public String getData() {
    return data;
  }

  @Nullable
  @Override
  public Instant getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "ErrorMsg{"
        + "transform=" + transform + ", "
        + "error=" + error + ", "
        + "data=" + data + ", "
        + "timestamp=" + timestamp
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ErrorMsg) {
      ErrorMsg that = (ErrorMsg) o;
      return (this.transform == null ? that.getTransform() == null : this.transform.equals(that.getTransform()))
          && (this.error == null ? that.getError() == null : this.error.equals(that.getError()))
          && (this.data == null ? that.getData() == null : this.data.equals(that.getData()))
          && (this.timestamp == null ? that.getTimestamp() == null : this.timestamp.equals(that.getTimestamp()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (transform == null) ? 0 : transform.hashCode();
    h$ *= 1000003;
    h$ ^= (error == null) ? 0 : error.hashCode();
    h$ *= 1000003;
    h$ ^= (data == null) ? 0 : data.hashCode();
    h$ *= 1000003;
    h$ ^= (timestamp == null) ? 0 : timestamp.hashCode();
    return h$;
  }

  @Override
  public ErrorMsg.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends ErrorMsg.Builder {
    private String transform;
    private String error;
    private String data;
    private Instant timestamp;
    Builder() {
    }
    private Builder(ErrorMsg source) {
      this.transform = source.getTransform();
      this.error = source.getError();
      this.data = source.getData();
      this.timestamp = source.getTimestamp();
    }
    @Override
    public ErrorMsg.Builder setTransform(String transform) {
      this.transform = transform;
      return this;
    }
    @Override
    public ErrorMsg.Builder setError(String error) {
      this.error = error;
      return this;
    }
    @Override
    public ErrorMsg.Builder setData(String data) {
      this.data = data;
      return this;
    }
    @Override
    public ErrorMsg.Builder setTimestamp(Instant timestamp) {
      this.timestamp = timestamp;
      return this;
    }
    @Override
    public ErrorMsg build() {
      return new AutoValue_ErrorMsg(
          this.transform,
          this.error,
          this.data,
          this.timestamp);
    }
  }

}
