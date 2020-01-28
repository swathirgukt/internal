package com.indianeagle.internal.dto;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

/**
 * DTO for EmployeeFinancialYear
 * <p>
 * User: kalesha
 * Date: 8/9/2017
 */
@Entity
@Table(name = "EMPLOYEE_FINANCIAL_YEAR")
public class EmployeeFinancialYear extends BaseDto {

    @Column(name = "EMP_ID")
    private String empId;
    @ManyToOne()
    @JoinColumn(name = "FINANCIAL_YEAR_ID", nullable = false)
    private FinancialYear financialYear;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_FINANCIAL_YEAR_ID", nullable = false)

    private Set<EmployeeTaxSection> employeeTaxSections;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public FinancialYear getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
    }

    public Set<EmployeeTaxSection> getEmployeeTaxSections() {
        return employeeTaxSections;
    }

    public void setEmployeeTaxSections(Set<EmployeeTaxSection> employeeTaxSections) {
        this.employeeTaxSections = employeeTaxSections;
    }

    @Override
    public String toString() {
        return "EmployeeFinancialYear{" +
                "empId='" + empId + '\'' +
                ", financialYear=" + financialYear +
                ", employeeTaxSections=" + employeeTaxSections +
                '}';
    }
}
