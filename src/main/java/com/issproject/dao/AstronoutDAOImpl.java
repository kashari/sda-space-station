package com.issproject.dao;

import com.issproject.config.AppConfig;
import com.issproject.entity.Astronaut;
import org.hibernate.Session;

import java.util.List;

public class AstronoutDAOImpl implements AstronoutDAO{
    @Override
    public void saveAstronauts(List<Astronaut> astronauts) {
        Session session = new AppConfig().getSession();

        for (Astronaut a: astronauts){
            if (!astronautExist(a)){
                session.beginTransaction();
                session.save(a);
                session.getTransaction().commit();
                session.close();
            }
        }
    }

    @Override
    public boolean astronautExist(Astronaut astronaut) {
        for (Astronaut a:getAstronauts()){
            if (a.getName().equalsIgnoreCase(astronaut.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Astronaut> getAstronauts() {
        Session session = new AppConfig().getSession();
        session.beginTransaction();
        List <Astronaut> astronauts = session.createQuery("FROM Astronaut",Astronaut.class).getResultList();
        return astronauts;
    }
}
