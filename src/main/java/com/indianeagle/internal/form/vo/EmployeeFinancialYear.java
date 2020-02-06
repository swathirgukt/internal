package com.indianeagle.internal.form.vo;

import java.util.Set;

/**
 * DTO for EmployeeFinancialYear
 *
 * User: kalesha
 * Date: 8/9/2017
 */
public class EmployeeFinancialYear {

    private Long Id;
    private String empId;
    private FinancialYear financialYear;
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
