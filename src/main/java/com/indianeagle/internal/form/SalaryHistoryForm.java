package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;

import java.util.Date;
import java.util.List;

/**
 * @author Satya.Neelam
 * SalaryHistory Form.
 */
public class SalaryHistoryForm {

    private List<Employee> employeeList;
    private String employeeId;

    private Date salaryDate;
    private List<SalaryHistory> salaryHistory;

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
     * @return the salaryDate
     */
    public Date getSalaryDate() {
        return salaryDate;
    }

    /**
     * @param salaryDate the salaryDate to set
     */
    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    /**
     * @return the salaryHistory
     */
    public List<SalaryHistory> getSalaryHistory() {
        return salaryHistory;
    }

    /**
     * @param salaryHistory the salaryHistory to set
     */
    public void setSalaryHistory(List<SalaryHistory> salaryHistory) {
        this.salaryHistory = salaryHistory;
    }

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

}
