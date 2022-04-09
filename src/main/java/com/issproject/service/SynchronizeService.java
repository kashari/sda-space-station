package com.issproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.issproject.dao.AstronoutDAO;
import com.issproject.dao.AstronoutDAOImpl;
import com.issproject.dao.SateliteDAO;
import com.issproject.dao.SateliteDAOImpl;
import com.issproject.dto.People;
import com.issproject.entity.Astronaut;
import com.issproject.entity.Satelite;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SynchronizeService {

    private static final String SATELITE_JSON = "http://api.open-notify.org/iss-now.json";
    private static final String ASTRONOUT_JSON = "http://api.open-notify.org/astros.json";
    SateliteDAO sateliteDAO = new SateliteDAOImpl();
    AstronoutDAO astronoutDAO = new AstronoutDAOImpl();


    public Satelite getSatelite() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(SATELITE_JSON))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            Satelite satelite = mapper.readValue(response.body(), Satelite.class);
            sateliteDAO.save(satelite);
            return satelite;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public People getPeople() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(ASTRONOUT_JSON))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            People people = mapper.readValue(response.body(), People.class);
            return people;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void displaySatelitePosition(Satelite satelite){
        System.out.println();
        System.out.println(satelite.getId() + "\tLongitude: " + satelite.getPosition().getLongitude()
        +"\tLatitude: "+satelite.getPosition().getLatitude());
    }

    public List<Astronaut>getAstronauts(){
        List <Astronaut> astronauts = getPeople().getAstronauts();
        astronoutDAO.saveAstronauts(astronauts);
        return astronauts;
    }

    public void displayAstronoutsByCraft(String craftName) {
        for (Astronaut a: getAstronauts()){
            if (a.getCraft().equalsIgnoreCase(craftName)){
                System.out.println("Craft: "+a.getCraft() +
                        "\t Name: "+ a.getName());
            }
        }
    }

    public void displayAllAstronouts() {
        for (Astronaut a: getAstronauts()){
            System.out.println("Craft: "+a.getCraft() +
                    "\t Name: "+ a.getName());
        }
    }
}
