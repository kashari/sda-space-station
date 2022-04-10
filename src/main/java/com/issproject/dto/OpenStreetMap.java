package com.issproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenStreetMap {
//    @JsonProperty("place_id")
//    private long placeId;
//    private String licence;
//    @JsonProperty("osm_type")
//    private String osmType;
//    @JsonProperty("osm_id")
//    private long osmId;
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lon")
    private String longitude;
    @JsonProperty("country")
    private String country;
    @JsonProperty("country_code")
    private String countryCode;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OpenStreetMap{");
        sb.append("latitude='").append(latitude).append('\'');
        sb.append(", longitude='").append(longitude).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
