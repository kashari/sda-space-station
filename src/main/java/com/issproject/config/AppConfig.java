package com.issproject.config;

import com.issproject.entity.Astronaut;
import com.issproject.entity.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppConfig {

    private static AppConfig instance;
    private AppConfig(){}
    public static AppConfig getInstance(){
        if (instance==null){
            instance = new AppConfig();
        }
        return instance;
    }

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Astronaut.class)
            .addAnnotatedClass(Report.class)
            .buildSessionFactory();


    public SessionFactory getSessionFactory(){
        return factory;
    }
}
