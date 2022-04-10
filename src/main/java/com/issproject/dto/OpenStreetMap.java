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
    @JsonProperty("address")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
