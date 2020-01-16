package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.TaxSectionDeclaration;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: kalesha
 * Date: 8/11/2017
 */
public class TaxSectionForm {

    private Long id;
    private String sectionName;
    private BigDecimal sectionLimit;
    private List<TaxSectionDeclaration> taxSectionDeclarations;
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

    public List<TaxSectionDeclaration> getTaxSectionDeclarations() {
        return taxSectionDeclarations;
    }

    public void setTaxSectionDeclarations(List<TaxSectionDeclaration> taxSectionDeclarations) {
        this.taxSectionDeclarations = taxSectionDeclarations;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
