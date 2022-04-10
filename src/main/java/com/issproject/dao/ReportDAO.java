package com.issproject.dao;

import com.issproject.entity.Report;

public interface ReportDAO {
    void save (Report report);
    void displayReport(Report report);
}
