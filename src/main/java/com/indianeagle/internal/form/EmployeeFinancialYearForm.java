package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.FinancialYear;

import java.util.List;

/**
 * Form class of EmployeeFinancialYear
 *
 * User: kalesha
 * Date: 8/13/2017
 */
public class EmployeeFinancialYearForm {

    private Long Id;
    private String empId;
    private FinancialYear financialYear;
    private List<EmployeeTaxSectionForm> employeeTaxSectionForms;

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

    public List<EmployeeTaxSectionForm> getEmployeeTaxSectionForms() {
        return employeeTaxSectionForms;
    }

    public void setEmployeeTaxSectionForms(List<EmployeeTaxSectionForm> employeeTaxSectionForms) {
        this.employeeTaxSectionForms = employeeTaxSectionForms;
    }
}
