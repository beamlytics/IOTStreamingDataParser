/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beamlytics.examples.schema;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.AutoValueSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;

import com.google.auto.value.AutoValue;

@AutoValue
@DefaultSchema(AutoValueSchema.class)
public abstract class Location {

  @AutoValue.Builder
  public abstract static class Builder {
   
    public abstract Builder setLatitude(@Nullable String latitude);
    public abstract Builder setLongitude(@Nullable String longitude);
    public abstract Builder setAddressLine1(@Nullable String addressLine1);
    public abstract Builder setAddressLine2(@Nullable String addressLine2);
    public abstract Builder setCity(@Nullable String city);
    public abstract Builder setState(@Nullable String state);
    public abstract Builder setZip(@Nullable String zip);
    public abstract Builder setCountry(@Nullable String country);
    public abstract Builder setLocationType(@Nullable String locationType);
    public abstract Builder setMarker(@Nullable String marker); 
    public abstract Builder setLocationId(String locationId);
    public abstract Location build();
  }

  public static Builder builder() {

    return new AutoValue_Location.Builder();
  }

  @SchemaFieldName("location_id")
  public @Nullable abstract String getLocationId();

  // can be variable, if sensor is movable  
  @SchemaFieldName("latitude")
  public @Nullable abstract String getLatitude();

  // can be variable, if sensor is movable  
  @SchemaFieldName("longitude")
  public @Nullable abstract String getLongitude();

  //can be null, e.g. if reading a stream velocity in a river -- and it is a untethered floating device. 
  @SchemaFieldName("address_line1")
  public @Nullable abstract String getAddressLine1();

  @SchemaFieldName("address_line2")
  public @Nullable abstract String getAddressLine2();

  @SchemaFieldName("city")
  public @Nullable abstract String getCity();

  @SchemaFieldName("state")
  public @Nullable abstract String getState();

  @SchemaFieldName("zip")
  public @Nullable abstract String getZip();
 

  @SchemaFieldName("country")
  public @Nullable abstract String getCountry();

  @SchemaFieldName("location_type")
  public @Nullable abstract String getLocationType();

  // can be used to note address marker id , gps geolocation id etc
  @SchemaFieldName("marker")
  public @Nullable abstract String getMarker();
}