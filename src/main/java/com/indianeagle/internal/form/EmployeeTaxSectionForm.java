package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.EmployeeTaxSectionDeclaration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: kalesha
 * Date: 8/13/2017
 */

@Component
public class EmployeeTaxSectionForm {

    private Long id;
    private String sectionName;
    private BigDecimal sectionLimit;
    private boolean active = true;
    private List<EmployeeTaxSectionDeclaration> employeeTaxSectionDeclarations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public BigDecimal getSectionLimit() {
        return sectionLimit;
    }

    public void setSectionLimit(BigDecimal sectionLimit) {
        this.sectionLimit = sectionLimit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<EmployeeTaxSectionDeclaration> getEmployeeTaxSectionDeclarations() {
        return employeeTaxSectionDeclarations;
    }

    public void setEmployeeTaxSectionDeclarations(List<EmployeeTaxSectionDeclaration> employeeTaxSectionDeclarations) {
        this.employeeTaxSectionDeclarations = employeeTaxSectionDeclarations;
    }
}
