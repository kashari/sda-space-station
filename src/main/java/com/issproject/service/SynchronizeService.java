package com.issproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.issproject.dao.*;
import com.issproject.dto.AstroJSON;
import com.issproject.dto.OpenStreetMap;
import com.issproject.entity.Astronaut;
import com.issproject.entity.Report;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SynchronizeService {

    private static final String REPORT_JSON = "http://api.open-notify.org/iss-now.json";
    private static final String ASTRONAUT_JSON = "http://api.open-notify.org/astros.json";
    AstronautDAO astronautDAO = new AstronautDAOImpl();
    ReportDAO reportDAO = new ReportDAOImpl();

    public AstroJSON getAstroJSON(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .uri(URI.create(ASTRONAUT_JSON))
                .build();
        try{
            HttpResponse<String>response = client.send(request,HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            AstroJSON astroJSON = mapper.readValue(response.body(),AstroJSON.class);
            astronautDAO.saveAstronauts(astroJSON.getPeople());
            return astroJSON;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //https://nominatim.openstreetmap.org/reverse?format=json&lat=40.730610&lon=-73.935242
    public static OpenStreetMap getOpenStreetMap(String lat, String lon){
        final String OPEN_STREET_URL_JSON = "https://nominatim.openstreetmap.org/reverse?format=json&lat="+lat+"&lon="+lon;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .uri(URI.create(OPEN_STREET_URL_JSON))
                .build();
        try{
            HttpResponse <String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            OpenStreetMap openStreetMap = mapper.readValue(response.body(),OpenStreetMap.class);
            return openStreetMap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Report getReportJSON(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .uri(URI.create(REPORT_JSON))
                .build();
        try{
            HttpResponse<String>response = client.send(request,HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            Report report = mapper.readValue(response.body(),Report.class);
            report.setLatitude(report.getPosition().getLatitude());
            report.setLongitude(report.getPosition().getLongitude());
            reportDAO.save(report);
            reportDAO.displayReport(report);

            return report;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void displayAllReports(){
        reportDAO.displayAllReports();
    }



    public List<Astronaut> getAstronauts(){
        List<Astronaut> astronauts = getAstroJSON().getPeople();
        astronautDAO.saveAstronauts(astronauts);
        return astronautDAO.getAstronauts();
    }

    public void displayAstronauts(){
        astronautDAO.displayAstronauts();
    }

    public void displayAstronoutsByCraft(String craftName) {
        astronautDAO.displayAstronautsByCraft(craftName);
    }

    public void displayAllAstronauts() {
        astronautDAO.displayAstronauts();
    }
}
