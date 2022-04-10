package com.issproject.dto;

import com.issproject.entity.Astronaut;

import java.util.List;

public class AstroJSON {
    private String message;
    private int number;
    private List<Astronaut> people;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Astronaut> getPeople() {
        return people;
    }

    public void setPeople(List<Astronaut> people) {
        this.people = people;
    }
}
