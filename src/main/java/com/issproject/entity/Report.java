package com.issproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.issproject.dto.PositionJSON;
import lombok.Data;
import javax.persistence.*;
@Entity
@Table(name = "report")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    private int id;
    @JsonProperty("timestamp")
    @Column(name = "timestamp")
    private long timeStamp;
    @JsonProperty("iss_position")
    @Transient
    private PositionJSON position;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
    @Transient
    @JsonProperty("message")
    private String message;
    @Column(name = "location")
    private String location;
    @Transient
    private int occurrencies = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public PositionJSON getPosition() {
        return position;
    }

    public void setPosition(PositionJSON position) {
        this.position = position;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public int getOccurrencies() {
        return occurrencies;
    }

    public void setOccurrencies(int occurrencies) {
        this.occurrencies = occurrencies;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
