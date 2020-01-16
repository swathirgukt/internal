package com.indianeagle.internal.dto;

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
public class EmployeeFinancialYear {
    @javax.persistence.Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "EMP_ID")

    private String empId;
    @ManyToOne
    @JoinColumn(name = "FINANCIAL_YEAR_ID", nullable = false)
    private FinancialYear financialYear;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_FINANCIAL_YEAR_ID", nullable = false)
    private Set<EmployeeTaxSection> employeeTaxSections;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

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
}
