package com.issproject.dao;
import com.issproject.config.AppConfig;
import com.issproject.dto.Result;
import com.issproject.entity.Report;
import com.issproject.service.SynchronizeService;
import com.issproject.utils.Utils;
import com.opencsv.CSVWriter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @Override
    public List<Result> getReportsGroupedByCountry(){
        final String SELECT_GROUPED_REPORTS = "SELECT r.location as location,COUNT(r.id) as Occurrencies" +
                "                FROM Report r\n" +
                "                GROUP BY location" +
                "                ORDER BY Occurrencies DESC";
        Session session = AppConfig.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery(SELECT_GROUPED_REPORTS);
        query.setResultTransformer(Transformers.aliasToBean(Result.class));
        List<Result> resultList = query.getResultList();
        for (Result r: resultList){
            System.out.println(r.getLocation()+"\t\t"+String.format("%.0f",r.getOccurrencies()));
        }
       // createCsvFileGroupedByLocation(resultList);
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public void createCsvFileGroupedByLocation(List<Result>resultList){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        File file = new File("/Users/user/Desktop/csvReports/report"+formatDateTime+".csv");
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            String []header = {"Location","Occurrencies"};
            writer.writeNext(header);
            for (Result r: resultList){
                String []data = {r.getLocation(),String.format("%.0f",r.getOccurrencies())};
                writer.writeNext(data);
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void createCsvFileForAllReports(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
        File file = new File("/Users/user/Desktop/csvReports/all-reports"+formatDateTime+".csv");
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            String []header = {"Report ID","Time","Location"};
            writer.writeNext(header);
            for (Report r: getAllReports()){
                String []data = {String.valueOf(r.getId()), Utils.timeStampToDate(r.getTimeStamp()).toString(),r.getLocation()};
                writer.writeNext(data);
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
