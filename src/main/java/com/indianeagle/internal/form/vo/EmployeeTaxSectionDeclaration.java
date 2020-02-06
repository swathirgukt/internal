package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;

/**
 * DTO for EmployeeTaxSectionDeclaration
 *
 * User: kalesha
 * Date: 8/9/2017
 */
public class EmployeeTaxSectionDeclaration {

    private Long id;
    private String subSectionName;
    private BigDecimal saveAmount;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubSectionName() {
        return subSectionName;
    }

    public void setSubSectionName(String subSectionName) {
        this.subSectionName = subSectionName;
    }

    public BigDecimal getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(BigDecimal saveAmount) {
        this.saveAmount = saveAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
