package com.issproject.dao;

import com.issproject.config.AppConfig;
import com.issproject.entity.Report;
import org.hibernate.Session;

public class ReportDAOImpl implements ReportDAO {
    @Override
    public void save(Report report) {
        Session session = AppConfig.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(report);
        session.getTransaction().commit();;
        session.close();
    }

    @Override
    public void displayReport(Report report) {
        System.out.println("Report nr "+report.getId() +": \t\tTime: "+report.getTimeStamp()+
                "\tLatitude: "+report.getLatitude()+
                "\tLongitude: "+report.getLongitude());
    }
}
