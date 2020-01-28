package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Incentives;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Form object  for incentives
 *
 * @author kiran.paluvadi
 */
public class IncentiveForm {

    private Date incentiveDate;
    private List<Employee> employeeList;
    private List<Incentives> incentivesList;
    private BigDecimal totalSal = BigDecimal.ZERO;

    public String getTotalSalInWords() {
        return SimpleUtils.numberToWord(getTotalSal());
    }

    /**
     * @return the totalSal
     */
    public BigDecimal getTotalSal() {
        return totalSal;
    }

    /**
     * @param totalSal the totalSal to set
     */
    public void setTotalSal(BigDecimal totalSal) {
        this.totalSal = totalSal;
    }

    /**
     * @return the employeeList
     */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * @return the incentiveDate
     */
    public Date getIncentiveDate() {
        return incentiveDate;
    }

    /**
     * @param incentiveDate the incentiveDate to set
     */
    public void setIncentiveDate(Date incentiveDate) {
        this.incentiveDate = incentiveDate;
    }

    /**
     * @return the incentivesList
     */
    public List<Incentives> getIncentivesList() {
        return incentivesList;
    }

    /**
     * @param incentivesList the incentivesList to set
     */
    public void setIncentivesList(List<Incentives> incentivesList) {
        this.incentivesList = incentivesList;
    }

}
