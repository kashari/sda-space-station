package com.issproject.dao;

import com.issproject.entity.Astronaut;

import java.util.List;

public interface AstronoutDAO {
    void saveAstronauts(List<Astronaut> astronauts);
    boolean astronautExist(Astronaut astronaut);
    List <Astronaut>getAstronauts();

}
