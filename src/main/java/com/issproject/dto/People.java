package com.issproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.issproject.entity.Astronaut;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class People {
    @JsonProperty("people")
    private List<Astronaut>astronauts;
    @JsonProperty("message")
    private String message;
    @JsonProperty("number")
    private int number;
}
