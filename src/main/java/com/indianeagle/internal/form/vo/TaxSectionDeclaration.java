package com.indianeagle.internal.form.vo;

/**
 * DTO for TaxSectionDeclaration
 *
 * User: kalesha
 * Date: 7/31/2017
 */
public class TaxSectionDeclaration {

    private Long id;
    private String subSectionName;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
