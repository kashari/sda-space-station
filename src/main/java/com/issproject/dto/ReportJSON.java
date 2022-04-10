package com.issproject.dto;

public class ReportJSON {
    private String message;
    private long timestamp;
    private Position iss_position;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Position getIss_position() {
        return iss_position;
    }

    public void setIss_position(Position iss_position) {
        this.iss_position = iss_position;
    }
}
