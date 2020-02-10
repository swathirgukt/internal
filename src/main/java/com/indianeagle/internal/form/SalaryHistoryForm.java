package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.form.vo.SalaryHistoryVO;

import java.util.Date;
import java.util.List;

/**
 * @author Satya.Neelam
 * SalaryHistory Form.
 */
public class SalaryHistoryForm {

    private List<EmployeeVO> employeeVOList;
    private String employeeId;

    private Date salaryDate;
    private List<SalaryHistoryVO> salaryHistoryVOList;

    /**
     * @return the employeeList
     */
    public List<EmployeeVO> getEmployeeVOList() {
        return employeeVOList;
    }

    /**
     * @param employeeVOList the employeeList to set
     */
    public void setEmployeeVOList(List<EmployeeVO> employeeVOList) {
        this.employeeVOList = employeeVOList;
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
    public List<SalaryHistoryVO> getSalaryHistoryVOList() {
        return salaryHistoryVOList;
    }

    /**
     * @param salaryHistoryVOList the salaryHistory to set
     */
    public void setSalaryHistoryVOList(List<SalaryHistoryVO> salaryHistoryVOList) {
        this.salaryHistoryVOList = salaryHistoryVOList;
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
