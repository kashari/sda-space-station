package com.issproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.issproject.dto.Position;
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
    private Position position;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
    @Transient
    @JsonProperty("message")
    private String message;
    public int getId() {
        return id;
    }

}
