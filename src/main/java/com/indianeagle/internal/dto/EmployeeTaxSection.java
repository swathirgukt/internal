package com.indianeagle.internal.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for EmployeeTaxSection
 * <p>
 * User: kalesha
 * Date: 8/9/2017
 */

@Component
@Entity
@Table(name = "EMPLOYEE_TAX_SECTIONS")
public class EmployeeTaxSection extends BaseDto implements Comparable<EmployeeTaxSection> {

    @Column(name = "SECTION_NAME")
    private String sectionName;
    @Column(name = "SECTION_LIMIT")
    private BigDecimal sectionLimit;
    @Column(name = "ACTIVE")
    private boolean active = true;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_TAX_SECTION_ID", nullable = false)

    private Set<EmployeeTaxSectionDeclaration> employeeTaxSectionDeclarations;

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

    @Override
    public String toString() {
        return "EmployeeTaxSection{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionLimit=" + sectionLimit +
                ", active=" + active +
                ", employeeTaxSectionDeclarations=" + employeeTaxSectionDeclarations +
                '}';
    }
}
