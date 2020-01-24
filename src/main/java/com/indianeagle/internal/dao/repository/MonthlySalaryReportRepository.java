package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.MonthlySalaryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Jpa Repository to interact with view "view_monthly_salary_report"
 *
 * @author Praveen
 */
@Repository
public interface MonthlySalaryReportRepository extends JpaRepository<MonthlySalaryReport, Long>, MonthlySalaryReportRepositoryCustom {
    /**
     * Method to get monthly salary report based on salaryDate
     *
     * @param salaryDate
     * @return List
     */
    @Query("from MonthlySalaryReport report where MONTH(report.salaryDate) =  MONTH(:salaryDate) AND YEAR(report.salaryDate) = YEAR(:salaryDate)")
    List<MonthlySalaryReport> getMonthlySalaryReport(@Param("salaryDate") Date salaryDate);

    /**
     * returns bankSalriesReport data
     *
     * @param salaryDate
     * @return List<Object [ ]>
     */
    @Query("SELECT  msr.employeeName, msr.bankAc, msr.netSalary  FROM MonthlySalaryReport as msr WHERE MONTH(msr.salaryDate)=MONTH(:salaryDate) AND YEAR(msr.salaryDate)=YEAR(:salaryDate)")
    public List<Object[]> getBankSalariesReport(@Param("salaryDate") Date salaryDate);


}
