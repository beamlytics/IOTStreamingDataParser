package com.beamlytics.examples.schema;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;
import org.joda.time.Instant;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_TempAvgAggregator extends TempAvgAggregator {

  @Nullable
  private final String deviceId;

  @Nullable
  private final String monitoredAttribute;

  @Nullable
  private final String monitoredAttributeUnit;

  @Nullable
  private final Double monitoredAttributeAverage;

  @Nullable
  private final Instant startTime;

  @Nullable
  private final Long durationms;

  private AutoValue_TempAvgAggregator(
      @Nullable String deviceId,
      @Nullable String monitoredAttribute,
      @Nullable String monitoredAttributeUnit,
      @Nullable Double monitoredAttributeAverage,
      @Nullable Instant startTime,
      @Nullable Long durationms) {
    this.deviceId = deviceId;
    this.monitoredAttribute = monitoredAttribute;
    this.monitoredAttributeUnit = monitoredAttributeUnit;
    this.monitoredAttributeAverage = monitoredAttributeAverage;
    this.startTime = startTime;
    this.durationms = durationms;
  }

  @Nullable
  @SchemaFieldName("device_id")
  @Override
  public String getDeviceId() {
    return deviceId;
  }

  @Nullable
  @SchemaFieldName("monitored_attribute")
  @Override
  public String getMonitoredAttribute() {
    return monitoredAttribute;
  }

  @Nullable
  @SchemaFieldName("monitored_attribute_unit")
  @Override
  public String getMonitoredAttributeUnit() {
    return monitoredAttributeUnit;
  }

  @Nullable
  @SchemaFieldName("monitored_attribute_average")
  @Override
  public Double getMonitoredAttributeAverage() {
    return monitoredAttributeAverage;
  }

  @Nullable
  @SchemaFieldName("start_time")
  @Override
  public Instant getStartTime() {
    return startTime;
  }

  @Nullable
  @SchemaFieldName("durationMS")
  @Override
  public Long getDurationms() {
    return durationms;
  }

  @Override
  public String toString() {
    return "TempAvgAggregator{"
        + "deviceId=" + deviceId + ", "
        + "monitoredAttribute=" + monitoredAttribute + ", "
        + "monitoredAttributeUnit=" + monitoredAttributeUnit + ", "
        + "monitoredAttributeAverage=" + monitoredAttributeAverage + ", "
        + "startTime=" + startTime + ", "
        + "durationms=" + durationms
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof TempAvgAggregator) {
      TempAvgAggregator that = (TempAvgAggregator) o;
      return (this.deviceId == null ? that.getDeviceId() == null : this.deviceId.equals(that.getDeviceId()))
          && (this.monitoredAttribute == null ? that.getMonitoredAttribute() == null : this.monitoredAttribute.equals(that.getMonitoredAttribute()))
          && (this.monitoredAttributeUnit == null ? that.getMonitoredAttributeUnit() == null : this.monitoredAttributeUnit.equals(that.getMonitoredAttributeUnit()))
          && (this.monitoredAttributeAverage == null ? that.getMonitoredAttributeAverage() == null : this.monitoredAttributeAverage.equals(that.getMonitoredAttributeAverage()))
          && (this.startTime == null ? that.getStartTime() == null : this.startTime.equals(that.getStartTime()))
          && (this.durationms == null ? that.getDurationms() == null : this.durationms.equals(that.getDurationms()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (deviceId == null) ? 0 : deviceId.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredAttribute == null) ? 0 : monitoredAttribute.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredAttributeUnit == null) ? 0 : monitoredAttributeUnit.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredAttributeAverage == null) ? 0 : monitoredAttributeAverage.hashCode();
    h$ *= 1000003;
    h$ ^= (startTime == null) ? 0 : startTime.hashCode();
    h$ *= 1000003;
    h$ ^= (durationms == null) ? 0 : durationms.hashCode();
    return h$;
  }

  @Override
  public TempAvgAggregator.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends TempAvgAggregator.Builder {
    private String deviceId;
    private String monitoredAttribute;
    private String monitoredAttributeUnit;
    private Double monitoredAttributeAverage;
    private Instant startTime;
    private Long durationms;
    Builder() {
    }
    private Builder(TempAvgAggregator source) {
      this.deviceId = source.getDeviceId();
      this.monitoredAttribute = source.getMonitoredAttribute();
      this.monitoredAttributeUnit = source.getMonitoredAttributeUnit();
      this.monitoredAttributeAverage = source.getMonitoredAttributeAverage();
      this.startTime = source.getStartTime();
      this.durationms = source.getDurationms();
    }
    @Override
    public TempAvgAggregator.Builder setDeviceId(String deviceId) {
      this.deviceId = deviceId;
      return this;
    }
    @Override
    public TempAvgAggregator.Builder setMonitoredAttribute(String monitoredAttribute) {
      this.monitoredAttribute = monitoredAttribute;
      return this;
    }
    @Override
    public TempAvgAggregator.Builder setMonitoredAttributeUnit(String monitoredAttributeUnit) {
      this.monitoredAttributeUnit = monitoredAttributeUnit;
      return this;
    }
    @Override
    public TempAvgAggregator.Builder setMonitoredAttributeAverage(Double monitoredAttributeAverage) {
      this.monitoredAttributeAverage = monitoredAttributeAverage;
      return this;
    }
    @Override
    public TempAvgAggregator.Builder setStartTime(Instant startTime) {
      this.startTime = startTime;
      return this;
    }
    @Override
    public TempAvgAggregator.Builder setDurationms(Long durationms) {
      this.durationms = durationms;
      return this;
    }
    @Override
    public TempAvgAggregator build() {
      return new AutoValue_TempAvgAggregator(
          this.deviceId,
          this.monitoredAttribute,
          this.monitoredAttributeUnit,
          this.monitoredAttributeAverage,
          this.startTime,
          this.durationms);
    }
  }

}
