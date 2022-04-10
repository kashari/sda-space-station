package com.issproject.dto;

public class Result {
    private String location;
    private float Occurrencies;
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getOccurrencies() {
        return Occurrencies;
    }

    public void setOccurrencies(float occurrencies) {
        Occurrencies = occurrencies;
    }

    public void setOccurrencies(int occurrencies) {
        Occurrencies = occurrencies;
    }
}
