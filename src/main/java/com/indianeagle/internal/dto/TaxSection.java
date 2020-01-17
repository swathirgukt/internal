package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for TaxSection
 * <p>
 * User: kalesha
 * Date: 7/31/2017
 */
@Entity
@Table(name = "TAX_SECTIONS")
public class TaxSection  extends BaseDto implements Comparable<TaxSection> {

    @Column(name = "SECTION_NAME")
    private String sectionName;
    @Column(name = "SECTION_LIMIT")
    private BigDecimal sectionLimit;
    @OneToMany
    @JoinColumn(name = "TAX_SECTION_ID", nullable = false)
    private Set<TaxSectionDeclaration> taxSectionDeclarations;
    @Column(name = "ACTIVE")
    private boolean active = true;

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

    public Set<TaxSectionDeclaration> getTaxSectionDeclarations() {
        return taxSectionDeclarations;
    }

    public void setTaxSectionDeclarations(Set<TaxSectionDeclaration> taxSectionDeclarations) {
        this.taxSectionDeclarations = taxSectionDeclarations;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int compareTo(TaxSection taxSection) {
        return this.sectionName.compareTo(taxSection.getSectionName());
    }
}
