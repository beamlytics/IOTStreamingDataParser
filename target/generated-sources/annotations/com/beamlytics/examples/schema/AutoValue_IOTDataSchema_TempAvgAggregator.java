package com.beamlytics.examples.schema;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import org.joda.time.Instant;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_IOTDataSchema_TempAvgAggregator extends IOTDataSchema.TempAvgAggregator {

  @Nullable
  private final Long durationMS;

  @Nullable
  private final Instant startTime;

  @Nullable
  private final String deviceId;

  @Nullable
  private final Double monitoredAttributeAverage;

  @Nullable
  private final String monitoredAttribute;

  @Nullable
  private final String monitoredAttributeUnit;

  private AutoValue_IOTDataSchema_TempAvgAggregator(
      @Nullable Long durationMS,
      @Nullable Instant startTime,
      @Nullable String deviceId,
      @Nullable Double monitoredAttributeAverage,
      @Nullable String monitoredAttribute,
      @Nullable String monitoredAttributeUnit) {
    this.durationMS = durationMS;
    this.startTime = startTime;
    this.deviceId = deviceId;
    this.monitoredAttributeAverage = monitoredAttributeAverage;
    this.monitoredAttribute = monitoredAttribute;
    this.monitoredAttributeUnit = monitoredAttributeUnit;
  }

  @Nullable
  @Override
  public Long getDurationMS() {
    return durationMS;
  }

  @Nullable
  @Override
  public Instant getStartTime() {
    return startTime;
  }

  @Nullable
  @Override
  public String getDeviceId() {
    return deviceId;
  }

  @Nullable
  @Override
  public Double getMonitoredAttributeAverage() {
    return monitoredAttributeAverage;
  }

  @Nullable
  @Override
  public String getMonitoredAttribute() {
    return monitoredAttribute;
  }

  @Nullable
  @Override
  public String getMonitoredAttributeUnit() {
    return monitoredAttributeUnit;
  }

  @Override
  public String toString() {
    return "TempAvgAggregator{"
        + "durationMS=" + durationMS + ", "
        + "startTime=" + startTime + ", "
        + "deviceId=" + deviceId + ", "
        + "monitoredAttributeAverage=" + monitoredAttributeAverage + ", "
        + "monitoredAttribute=" + monitoredAttribute + ", "
        + "monitoredAttributeUnit=" + monitoredAttributeUnit
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof IOTDataSchema.TempAvgAggregator) {
      IOTDataSchema.TempAvgAggregator that = (IOTDataSchema.TempAvgAggregator) o;
      return (this.durationMS == null ? that.getDurationMS() == null : this.durationMS.equals(that.getDurationMS()))
          && (this.startTime == null ? that.getStartTime() == null : this.startTime.equals(that.getStartTime()))
          && (this.deviceId == null ? that.getDeviceId() == null : this.deviceId.equals(that.getDeviceId()))
          && (this.monitoredAttributeAverage == null ? that.getMonitoredAttributeAverage() == null : this.monitoredAttributeAverage.equals(that.getMonitoredAttributeAverage()))
          && (this.monitoredAttribute == null ? that.getMonitoredAttribute() == null : this.monitoredAttribute.equals(that.getMonitoredAttribute()))
          && (this.monitoredAttributeUnit == null ? that.getMonitoredAttributeUnit() == null : this.monitoredAttributeUnit.equals(that.getMonitoredAttributeUnit()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (durationMS == null) ? 0 : durationMS.hashCode();
    h$ *= 1000003;
    h$ ^= (startTime == null) ? 0 : startTime.hashCode();
    h$ *= 1000003;
    h$ ^= (deviceId == null) ? 0 : deviceId.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredAttributeAverage == null) ? 0 : monitoredAttributeAverage.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredAttribute == null) ? 0 : monitoredAttribute.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredAttributeUnit == null) ? 0 : monitoredAttributeUnit.hashCode();
    return h$;
  }

  @Override
  public IOTDataSchema.TempAvgAggregator.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends IOTDataSchema.TempAvgAggregator.Builder {
    private Long durationMS;
    private Instant startTime;
    private String deviceId;
    private Double monitoredAttributeAverage;
    private String monitoredAttribute;
    private String monitoredAttributeUnit;
    Builder() {
    }
    private Builder(IOTDataSchema.TempAvgAggregator source) {
      this.durationMS = source.getDurationMS();
      this.startTime = source.getStartTime();
      this.deviceId = source.getDeviceId();
      this.monitoredAttributeAverage = source.getMonitoredAttributeAverage();
      this.monitoredAttribute = source.getMonitoredAttribute();
      this.monitoredAttributeUnit = source.getMonitoredAttributeUnit();
    }
    @Override
    public IOTDataSchema.TempAvgAggregator.Builder setDurationMS(Long durationMS) {
      this.durationMS = durationMS;
      return this;
    }
    @Override
    public IOTDataSchema.TempAvgAggregator.Builder setStartTime(Instant startTime) {
      this.startTime = startTime;
      return this;
    }
    @Override
    public IOTDataSchema.TempAvgAggregator.Builder setDeviceId(String deviceId) {
      this.deviceId = deviceId;
      return this;
    }
    @Override
    public IOTDataSchema.TempAvgAggregator.Builder setMonitoredAttributeAverage(Double monitoredAttributeAverage) {
      this.monitoredAttributeAverage = monitoredAttributeAverage;
      return this;
    }
    @Override
    public IOTDataSchema.TempAvgAggregator.Builder setMonitoredAttribute(String monitoredAttribute) {
      this.monitoredAttribute = monitoredAttribute;
      return this;
    }
    @Override
    public IOTDataSchema.TempAvgAggregator.Builder setMonitoredAttributeUnit(String monitoredAttributeUnit) {
      this.monitoredAttributeUnit = monitoredAttributeUnit;
      return this;
    }
    @Override
    public IOTDataSchema.TempAvgAggregator build() {
      return new AutoValue_IOTDataSchema_TempAvgAggregator(
          this.durationMS,
          this.startTime,
          this.deviceId,
          this.monitoredAttributeAverage,
          this.monitoredAttribute,
          this.monitoredAttributeUnit);
    }
  }

}
