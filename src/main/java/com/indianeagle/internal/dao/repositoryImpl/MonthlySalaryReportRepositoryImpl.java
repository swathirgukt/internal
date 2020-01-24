package com.indianeagle.internal.dao.repositoryImpl;

import com.indianeagle.internal.dao.repository.MonthlySalaryReportRepositoryCustom;
import com.indianeagle.internal.dto.MonthlySalaryReport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Custom  Repository implementation to interact with view "view_monthly_salary_report"
 *
 * @author Praveen
 */
@Repository
@Transactional(readOnly = true)
public class MonthlySalaryReportRepositoryImpl implements MonthlySalaryReportRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Method to get IT Report of all employees based on date
     *
     * @param range
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<MonthlySalaryReport> getITReport(Date range) {
        Query query = entityManager.createNativeQuery("select ITReport from MonthlySalaryReport ITReport where ITReport.salaryDate >=? AND  ITReport.salaryDate <= ? GROUP BY MONTH(ITReport.salaryDate),ITReport.employeeId  ORDER BY ITReport.employeeId,ITReport.salaryDate");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(range);
        calendar.add(Calendar.MONTH, 11);
        query.setParameter(1, range);
        query.setParameter(2, calendar.getTime());
        return query.getResultList();
    }
}
