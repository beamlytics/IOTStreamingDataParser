package com.beamlytics.examples.schema;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;
import org.joda.time.Instant;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_IOTDataSchema extends IOTDataSchema {

  @Nullable
  private final Instant eventTimestamp;

  @Nullable
  private final String deviceId;

  @Nullable
  private final Location deviceLocation;

  @Nullable
  private final String monitoredAttribute;

  @Nullable
  private final String monitoredUnit;

  @Nullable
  private final Double monitoredValue;

  private AutoValue_IOTDataSchema(
      @Nullable Instant eventTimestamp,
      @Nullable String deviceId,
      @Nullable Location deviceLocation,
      @Nullable String monitoredAttribute,
      @Nullable String monitoredUnit,
      @Nullable Double monitoredValue) {
    this.eventTimestamp = eventTimestamp;
    this.deviceId = deviceId;
    this.deviceLocation = deviceLocation;
    this.monitoredAttribute = monitoredAttribute;
    this.monitoredUnit = monitoredUnit;
    this.monitoredValue = monitoredValue;
  }

  @Nullable
  @SchemaFieldName("event_timestamp")
  @Override
  public Instant getEventTimestamp() {
    return eventTimestamp;
  }

  @Nullable
  @SchemaFieldName("device_id")
  @Override
  public String getDeviceId() {
    return deviceId;
  }

  @Nullable
  @SchemaFieldName("device_location")
  @Override
  public Location getDeviceLocation() {
    return deviceLocation;
  }

  @Nullable
  @SchemaFieldName("monitored_attribute")
  @Override
  public String getMonitoredAttribute() {
    return monitoredAttribute;
  }

  @Nullable
  @SchemaFieldName("monitored_unit")
  @Override
  public String getMonitoredUnit() {
    return monitoredUnit;
  }

  @Nullable
  @SchemaFieldName("monitored_value")
  @Override
  public Double getMonitoredValue() {
    return monitoredValue;
  }

  @Override
  public String toString() {
    return "IOTDataSchema{"
        + "eventTimestamp=" + eventTimestamp + ", "
        + "deviceId=" + deviceId + ", "
        + "deviceLocation=" + deviceLocation + ", "
        + "monitoredAttribute=" + monitoredAttribute + ", "
        + "monitoredUnit=" + monitoredUnit + ", "
        + "monitoredValue=" + monitoredValue
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof IOTDataSchema) {
      IOTDataSchema that = (IOTDataSchema) o;
      return (this.eventTimestamp == null ? that.getEventTimestamp() == null : this.eventTimestamp.equals(that.getEventTimestamp()))
          && (this.deviceId == null ? that.getDeviceId() == null : this.deviceId.equals(that.getDeviceId()))
          && (this.deviceLocation == null ? that.getDeviceLocation() == null : this.deviceLocation.equals(that.getDeviceLocation()))
          && (this.monitoredAttribute == null ? that.getMonitoredAttribute() == null : this.monitoredAttribute.equals(that.getMonitoredAttribute()))
          && (this.monitoredUnit == null ? that.getMonitoredUnit() == null : this.monitoredUnit.equals(that.getMonitoredUnit()))
          && (this.monitoredValue == null ? that.getMonitoredValue() == null : this.monitoredValue.equals(that.getMonitoredValue()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (eventTimestamp == null) ? 0 : eventTimestamp.hashCode();
    h$ *= 1000003;
    h$ ^= (deviceId == null) ? 0 : deviceId.hashCode();
    h$ *= 1000003;
    h$ ^= (deviceLocation == null) ? 0 : deviceLocation.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredAttribute == null) ? 0 : monitoredAttribute.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredUnit == null) ? 0 : monitoredUnit.hashCode();
    h$ *= 1000003;
    h$ ^= (monitoredValue == null) ? 0 : monitoredValue.hashCode();
    return h$;
  }

  @Override
  public IOTDataSchema.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends IOTDataSchema.Builder {
    private Instant eventTimestamp;
    private String deviceId;
    private Location deviceLocation;
    private String monitoredAttribute;
    private String monitoredUnit;
    private Double monitoredValue;
    Builder() {
    }
    private Builder(IOTDataSchema source) {
      this.eventTimestamp = source.getEventTimestamp();
      this.deviceId = source.getDeviceId();
      this.deviceLocation = source.getDeviceLocation();
      this.monitoredAttribute = source.getMonitoredAttribute();
      this.monitoredUnit = source.getMonitoredUnit();
      this.monitoredValue = source.getMonitoredValue();
    }
    @Override
    public IOTDataSchema.Builder setEventTimestamp(Instant eventTimestamp) {
      this.eventTimestamp = eventTimestamp;
      return this;
    }
    @Override
    public IOTDataSchema.Builder setDeviceId(String deviceId) {
      this.deviceId = deviceId;
      return this;
    }
    @Override
    public IOTDataSchema.Builder setDeviceLocation(Location deviceLocation) {
      this.deviceLocation = deviceLocation;
      return this;
    }
    @Override
    public IOTDataSchema.Builder setMonitoredAttribute(String monitoredAttribute) {
      this.monitoredAttribute = monitoredAttribute;
      return this;
    }
    @Override
    public IOTDataSchema.Builder setMonitoredUnit(String monitoredUnit) {
      this.monitoredUnit = monitoredUnit;
      return this;
    }
    @Override
    public IOTDataSchema.Builder setMonitoredValue(Double monitoredValue) {
      this.monitoredValue = monitoredValue;
      return this;
    }
    @Override
    public IOTDataSchema build() {
      return new AutoValue_IOTDataSchema(
          this.eventTimestamp,
          this.deviceId,
          this.deviceLocation,
          this.monitoredAttribute,
          this.monitoredUnit,
          this.monitoredValue);
    }
  }

}
