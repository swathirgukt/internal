package com.indianeagle.internal.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO class for Incentives
 *
 * @author kiran.paluvadi
 */

@Component
@Entity
@Table(name = "INCENTIVES")
public class Incentives extends BaseDto {

    @Column(name = "AMOUNT")
    private BigDecimal incentiveAmount;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    private Date incentiveDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMP_REF", nullable = false)
    private Employee employee;
    private String empName;

    /**
     * @return the incentiveAmount
     */
    public BigDecimal getIncentiveAmount() {
        return incentiveAmount;
    }

    /**
     * @param incentiveAmount the incentiveAmount to set
     */
    public void setIncentiveAmount(BigDecimal incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
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
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

}
