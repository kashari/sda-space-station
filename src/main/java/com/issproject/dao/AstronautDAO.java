package com.issproject.dao;

import com.issproject.entity.Astronaut;

import java.util.List;

public interface AstronautDAO {
    void saveAstronauts(List<Astronaut> astronauts);
    boolean astronautExist(Astronaut astronaut);
    List <Astronaut>getAstronauts();
    void displayAstronauts();
    void displayAstronautsByCraft(String craft);

}
