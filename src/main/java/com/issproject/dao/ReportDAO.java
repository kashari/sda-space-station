package com.issproject.dao;

import com.issproject.entity.Report;

import java.util.List;

public interface ReportDAO {
    void save (Report report);
    void displayReport(Report report);
    List<Report> getAllReports();
    void displayAllReports();
    void displayGroupedReports();
}
