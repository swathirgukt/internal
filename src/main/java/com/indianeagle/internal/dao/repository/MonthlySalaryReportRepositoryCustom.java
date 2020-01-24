package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.MonthlySalaryReport;

import java.util.Date;
import java.util.List;

/**
 * Custom  Repository to interact with view "view_monthly_salary_report"
 *
 * @author Praveen
 */
public interface MonthlySalaryReportRepositoryCustom {
    /**
     * Method to get IT Report of all employees based on date
     *
     * @param range
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<MonthlySalaryReport> getITReport(Date range);
}
