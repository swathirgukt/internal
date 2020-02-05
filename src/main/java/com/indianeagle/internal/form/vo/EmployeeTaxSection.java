package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for EmployeeTaxSection
 *
 * User: kalesha
 * Date: 8/9/2017
 */
public class EmployeeTaxSection implements Comparable<EmployeeTaxSection> {

    private Long id;
    private String sectionName;
    private BigDecimal sectionLimit;
    private boolean active = true;
    private Set<EmployeeTaxSectionDeclaration> employeeTaxSectionDeclarations;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<EmployeeTaxSectionDeclaration> getEmployeeTaxSectionDeclarations() {
        return employeeTaxSectionDeclarations;
    }

    public void setEmployeeTaxSectionDeclarations(Set<EmployeeTaxSectionDeclaration> employeeTaxSectionDeclarations) {
        this.employeeTaxSectionDeclarations = employeeTaxSectionDeclarations;
    }

    public BigDecimal getSectionLimit() {
        return sectionLimit;
    }

    public void setSectionLimit(BigDecimal sectionLimit) {
        this.sectionLimit = sectionLimit;
    }

    @Override
    public int compareTo(EmployeeTaxSection employeeTaxSection) {
        return this.sectionName.compareTo(employeeTaxSection.getSectionName());
    }
}
