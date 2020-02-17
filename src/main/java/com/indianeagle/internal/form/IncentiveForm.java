package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.Incentives;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.form.vo.IncentivesVO;
import com.indianeagle.internal.util.SimpleUtils;

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
    private List<EmployeeVO> employeeVOList;
    private List<IncentivesVO> incentivesVOList;
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
    public List<EmployeeVO> getEmployeeVOList() {
        return employeeVOList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeVOList(List<EmployeeVO> employeeList) {
        this.employeeVOList = employeeList;
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
    public List<IncentivesVO> getIncentivesVOList() {
        return incentivesVOList;
    }

    /**
     * @param incentivesVOList the incentivesList to set
     */
    public void setIncentivesVOList(List<IncentivesVO> incentivesVOList) {
        this.incentivesVOList = incentivesVOList;
    }

}
