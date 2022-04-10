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

    AstronautDAO astronautDAO = new AstronautDAOImpl();
    ReportDAO reportDAO = new ReportDAOImpl();

    public AstroJSON getAstroJSON(){
        HttpClient client = HttpClient.newHttpClient();
        String ASTRONAUT_JSON = "http://api.open-notify.org/astros.json";
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
            return mapper.readValue(response.body(),OpenStreetMap.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Report getReportJSON(){
        HttpClient client = HttpClient.newHttpClient();
        String REPORT_JSON = "http://api.open-notify.org/iss-now.json";
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
            if (getOpenStreetMap(report.getLatitude(),report.getLongitude()).getAddress() ==null){
                report.setLocation("OCEAN");
            }
            else{
                report.setLocation(getOpenStreetMap(report.getLatitude(),report.getLongitude()).getAddress().getCountry());
            }
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
        reportDAO.createCsvFileForAllReports();
    }

    public void displayAstronoutsByCraft(String craftName) {
        astronautDAO.displayAstronautsByCraft(craftName);
    }

    public void displayAllAstronauts() {
        astronautDAO.displayAstronauts();
        astronautDAO.createCsvFileForAllAstronauts();
    }

    public void displayReportsGroupedByCountry(){
        reportDAO.createCsvFileGroupedByLocation(reportDAO.getReportsGroupedByCountry());
    }
    public List<Astronaut> getAstronauts(){
        return astronautDAO.getAstronauts();
    }
    public boolean craftExist(String craft){
        return astronautDAO.craftExist(craft);
    }
}
