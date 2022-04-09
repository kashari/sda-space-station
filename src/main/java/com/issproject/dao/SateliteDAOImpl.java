package com.issproject.dao;

import com.issproject.config.AppConfig;
import com.issproject.entity.Satelite;
import org.hibernate.Session;

public class SateliteDAOImpl implements SateliteDAO{
    @Override
    public void save(Satelite satelite) {
        Session session = new AppConfig().getSession();
        session.beginTransaction();
        session.save(satelite);
        session.getTransaction().commit();
        session.close();
    }
}
