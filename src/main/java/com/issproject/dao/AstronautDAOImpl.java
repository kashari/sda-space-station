package com.issproject.dao;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.issproject.config.AppConfig;
import com.issproject.entity.Astronaut;
import com.opencsv.CSVWriter;
import org.hibernate.Session;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AstronautDAOImpl implements AstronautDAO {
    @Override
    public void saveAstronauts(List<Astronaut> astronauts) {
        Session session = AppConfig.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        for (Astronaut a: astronauts){
            if (!astronautExist(a)){
                session.save(a);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean astronautExist(Astronaut astronaut) {
        for (Astronaut a: getAstronauts()){
            if (a.getName().equalsIgnoreCase(astronaut.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Astronaut> getAstronauts() {
        Session session = AppConfig.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List <Astronaut> astronauts = session.createQuery("FROM Astronaut",Astronaut.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return astronauts;
    }

    @Override
    public void displayAstronauts() {
        for (Astronaut a: getAstronauts()){
            System.out.println("Name: "+a.getName() + "Craft: "+a.getCraft());
        }
    }

    @Override
    public void displayAstronautsByCraft(String craftName) {
        for (Astronaut a: getAstronauts()){
            if (a.getCraft().equalsIgnoreCase(craftName)){
                System.out.println("Craft: "+a.getCraft() +
                        "\t Name: "+ a.getName());
            }
        }

    }

    @Override
    public void createCsvFileForAllAstronauts(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        File file = new File("/Users/user/Desktop/csvReports/all-astronauts"+formatDateTime+".csv");
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            String []header = {"Astronaut ID","Name","Craft"};
            writer.writeNext(header);
            for (Astronaut a: getAstronauts()){
                String []data = {String.valueOf(a.getId()), a.getName(),a.getCraft()};
                writer.writeNext(data);
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean craftExist(String craft){
        boolean exist = false;
        for (Astronaut a: getAstronauts()){
            if (a.getCraft().equalsIgnoreCase(craft)){
                exist = true;
            }
        }
        return exist;
    }
}
