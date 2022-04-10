package com.issproject.dao;
import com.issproject.config.AppConfig;
import com.issproject.entity.Astronaut;
import org.hibernate.Session;
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
        List <Astronaut> astronauts = session.createQuery("FROM Astronaut",Astronaut.class).getResultList();
        return astronauts;
    }

    @Override
    public void displayAstronauts() {
        Session session = AppConfig.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        for (Astronaut a: getAstronauts()){
            System.out.println("Name: "+a.getName() + "Craft: "+a.getCraft());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void displayAstronautsByCraft(String craftName) {
        Session session = AppConfig.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for (Astronaut a: getAstronauts()){
            if (a.getCraft().equalsIgnoreCase(craftName)){
                System.out.println("Craft: "+a.getCraft() +
                        "\t Name: "+ a.getName());
            }
        }
        session.getTransaction().commit();
        session.close();
    }
}
