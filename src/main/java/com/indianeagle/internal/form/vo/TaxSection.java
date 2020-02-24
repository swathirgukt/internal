package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for TaxSection
 *
 * User: kalesha
 * Date: 7/31/2017
 */
public class TaxSection implements Comparable<TaxSection> {

    private Long id;
    private String sectionName;
    private BigDecimal sectionLimit;
    private Set<TaxSectionDeclarationVO> taxSectionDeclarationVOS;
    private boolean active = true;

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

    public Set<TaxSectionDeclarationVO> getTaxSectionDeclarationVOS() {
        return taxSectionDeclarationVOS;
    }

    public void setTaxSectionDeclarationVOS(Set<TaxSectionDeclarationVO> taxSectionDeclarationVOS) {
        this.taxSectionDeclarationVOS = taxSectionDeclarationVOS;
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
