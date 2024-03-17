package com.beamlytics.examples.schema;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Location extends Location {

  @Nullable
  private final String locationId;

  @Nullable
  private final String latitude;

  @Nullable
  private final String longitude;

  @Nullable
  private final String addressLine1;

  @Nullable
  private final String addressLine2;

  @Nullable
  private final String city;

  @Nullable
  private final String state;

  @Nullable
  private final String zip;

  @Nullable
  private final String country;

  @Nullable
  private final String locationType;

  @Nullable
  private final String marker;

  private AutoValue_Location(
      @Nullable String locationId,
      @Nullable String latitude,
      @Nullable String longitude,
      @Nullable String addressLine1,
      @Nullable String addressLine2,
      @Nullable String city,
      @Nullable String state,
      @Nullable String zip,
      @Nullable String country,
      @Nullable String locationType,
      @Nullable String marker) {
    this.locationId = locationId;
    this.latitude = latitude;
    this.longitude = longitude;
    this.addressLine1 = addressLine1;
    this.addressLine2 = addressLine2;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.country = country;
    this.locationType = locationType;
    this.marker = marker;
  }

  @Nullable
  @SchemaFieldName("location_id")
  @Override
  public String getLocationId() {
    return locationId;
  }

  @Nullable
  @SchemaFieldName("latitude")
  @Override
  public String getLatitude() {
    return latitude;
  }

  @Nullable
  @SchemaFieldName("longitude")
  @Override
  public String getLongitude() {
    return longitude;
  }

  @Nullable
  @SchemaFieldName("address_line1")
  @Override
  public String getAddressLine1() {
    return addressLine1;
  }

  @Nullable
  @SchemaFieldName("address_line2")
  @Override
  public String getAddressLine2() {
    return addressLine2;
  }

  @Nullable
  @SchemaFieldName("city")
  @Override
  public String getCity() {
    return city;
  }

  @Nullable
  @SchemaFieldName("state")
  @Override
  public String getState() {
    return state;
  }

  @Nullable
  @SchemaFieldName("zip")
  @Override
  public String getZip() {
    return zip;
  }

  @Nullable
  @SchemaFieldName("country")
  @Override
  public String getCountry() {
    return country;
  }

  @Nullable
  @SchemaFieldName("location_type")
  @Override
  public String getLocationType() {
    return locationType;
  }

  @Nullable
  @SchemaFieldName("marker")
  @Override
  public String getMarker() {
    return marker;
  }

  @Override
  public String toString() {
    return "Location{"
        + "locationId=" + locationId + ", "
        + "latitude=" + latitude + ", "
        + "longitude=" + longitude + ", "
        + "addressLine1=" + addressLine1 + ", "
        + "addressLine2=" + addressLine2 + ", "
        + "city=" + city + ", "
        + "state=" + state + ", "
        + "zip=" + zip + ", "
        + "country=" + country + ", "
        + "locationType=" + locationType + ", "
        + "marker=" + marker
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Location) {
      Location that = (Location) o;
      return (this.locationId == null ? that.getLocationId() == null : this.locationId.equals(that.getLocationId()))
          && (this.latitude == null ? that.getLatitude() == null : this.latitude.equals(that.getLatitude()))
          && (this.longitude == null ? that.getLongitude() == null : this.longitude.equals(that.getLongitude()))
          && (this.addressLine1 == null ? that.getAddressLine1() == null : this.addressLine1.equals(that.getAddressLine1()))
          && (this.addressLine2 == null ? that.getAddressLine2() == null : this.addressLine2.equals(that.getAddressLine2()))
          && (this.city == null ? that.getCity() == null : this.city.equals(that.getCity()))
          && (this.state == null ? that.getState() == null : this.state.equals(that.getState()))
          && (this.zip == null ? that.getZip() == null : this.zip.equals(that.getZip()))
          && (this.country == null ? that.getCountry() == null : this.country.equals(that.getCountry()))
          && (this.locationType == null ? that.getLocationType() == null : this.locationType.equals(that.getLocationType()))
          && (this.marker == null ? that.getMarker() == null : this.marker.equals(that.getMarker()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (locationId == null) ? 0 : locationId.hashCode();
    h$ *= 1000003;
    h$ ^= (latitude == null) ? 0 : latitude.hashCode();
    h$ *= 1000003;
    h$ ^= (longitude == null) ? 0 : longitude.hashCode();
    h$ *= 1000003;
    h$ ^= (addressLine1 == null) ? 0 : addressLine1.hashCode();
    h$ *= 1000003;
    h$ ^= (addressLine2 == null) ? 0 : addressLine2.hashCode();
    h$ *= 1000003;
    h$ ^= (city == null) ? 0 : city.hashCode();
    h$ *= 1000003;
    h$ ^= (state == null) ? 0 : state.hashCode();
    h$ *= 1000003;
    h$ ^= (zip == null) ? 0 : zip.hashCode();
    h$ *= 1000003;
    h$ ^= (country == null) ? 0 : country.hashCode();
    h$ *= 1000003;
    h$ ^= (locationType == null) ? 0 : locationType.hashCode();
    h$ *= 1000003;
    h$ ^= (marker == null) ? 0 : marker.hashCode();
    return h$;
  }

  static final class Builder extends Location.Builder {
    private String locationId;
    private String latitude;
    private String longitude;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String locationType;
    private String marker;
    Builder() {
    }
    @Override
    public Location.Builder setLocationId(String locationId) {
      this.locationId = locationId;
      return this;
    }
    @Override
    public Location.Builder setLatitude(@Nullable String latitude) {
      this.latitude = latitude;
      return this;
    }
    @Override
    public Location.Builder setLongitude(@Nullable String longitude) {
      this.longitude = longitude;
      return this;
    }
    @Override
    public Location.Builder setAddressLine1(@Nullable String addressLine1) {
      this.addressLine1 = addressLine1;
      return this;
    }
    @Override
    public Location.Builder setAddressLine2(@Nullable String addressLine2) {
      this.addressLine2 = addressLine2;
      return this;
    }
    @Override
    public Location.Builder setCity(@Nullable String city) {
      this.city = city;
      return this;
    }
    @Override
    public Location.Builder setState(@Nullable String state) {
      this.state = state;
      return this;
    }
    @Override
    public Location.Builder setZip(@Nullable String zip) {
      this.zip = zip;
      return this;
    }
    @Override
    public Location.Builder setCountry(@Nullable String country) {
      this.country = country;
      return this;
    }
    @Override
    public Location.Builder setLocationType(@Nullable String locationType) {
      this.locationType = locationType;
      return this;
    }
    @Override
    public Location.Builder setMarker(@Nullable String marker) {
      this.marker = marker;
      return this;
    }
    @Override
    public Location build() {
      return new AutoValue_Location(
          this.locationId,
          this.latitude,
          this.longitude,
          this.addressLine1,
          this.addressLine2,
          this.city,
          this.state,
          this.zip,
          this.country,
          this.locationType,
          this.marker);
    }
  }

}
