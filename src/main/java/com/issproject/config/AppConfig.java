package com.issproject.config;

import com.issproject.entity.Astronaut;
import com.issproject.entity.Satelite;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppConfig {
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Astronaut.class)
            .addAnnotatedClass(Satelite.class)
            .buildSessionFactory();

    public Session getSession(){
        return factory.getCurrentSession();
    }
}
