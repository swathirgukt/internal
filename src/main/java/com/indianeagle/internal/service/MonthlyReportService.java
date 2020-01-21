package com.indianeagle.internal.facade;

import com.yana.internal.dto.MonthlySalaryReport;
import com.yana.internal.form.BankSalariesForm;
import com.yana.internal.form.ITForm;
import java.util.Date;
import java.util.List;

/**
 * Service which is for get monthly salary report details
 *
 * @author appala.sambangi
 */
public interface MonthlyReportService {
    /**
     * Method to get monthly salary report
     *
     * @param salaryDate
     * @return List
     */
    List<MonthlySalaryReport> getMonthlySalaryReport(Date salaryDate);

    /**
     * Method to get IT Report for all Employees
     *
     * @param range
     */
    List<ITForm> getITReport(Date range);

    /**
     * returns bankSalriesReport data
     *
     * @param salaryDate
     * @return BankSalariesForm
     */
    BankSalariesForm getBankSalariesReport(Date salaryDate);


}
