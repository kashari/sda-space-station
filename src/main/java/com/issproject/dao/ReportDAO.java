package com.issproject.dao;

import com.issproject.dto.Result;
import com.issproject.entity.Report;
import java.util.List;

public interface ReportDAO {
    void save (Report report);
    void displayReport(Report report);
    List<Report> getAllReports();
    void displayAllReports();
    void createCsvFileForAllReports();
    void createCsvFileGroupedByLocation(List<Result>resultList);
    List<Result> getReportsGroupedByCountry();
}
