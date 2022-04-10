package com.issproject.dao;

import com.issproject.config.AppConfig;
import com.issproject.entity.Report;
import com.issproject.service.SynchronizeService;
import com.issproject.utils.Utils;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

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
        System.out.println("Report nr "+report.getId() +": \t\t"+ Utils.timeStampToDate(report.getTimeStamp()) +
                "\t\tLatitude: "+report.getLatitude()+
                "\t\tLongitude: "+report.getLongitude());
        if (SynchronizeService.getOpenStreetMap(report.getLatitude(),report.getLongitude()).getAddress() == null){
            System.out.println("Craft is above ocean");
        }
        else{
            System.out.println("Craft is above: "+SynchronizeService.getOpenStreetMap(report.getLatitude(),report.getLongitude()).getAddress().getCountry());
        }
    }

    @Override
    public List<Report> getAllReports() {
        Session session = AppConfig.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Report>reportList = session.createQuery("FROM Report",Report.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return reportList;
    }

    @Override
    public void displayAllReports() {
        for (Report r: getAllReports()){
            System.out.println("Report nr "+r.getId() +": \t\t"+ Utils.timeStampToDate(r.getTimeStamp()) +
                    "\t\tLatitude: "+r.getLatitude()+
                    "\t\tLongitude: "+r.getLongitude());
            if (SynchronizeService.getOpenStreetMap(r.getLatitude(),r.getLongitude()).getAddress() == null){
                System.out.println("Craft is above ocean");
            }
            else{
                System.out.println("Craft is above: "+SynchronizeService.getOpenStreetMap(r.getLatitude(),r.getLongitude()).getAddress().getCountry());
            }
        }
    }


}
