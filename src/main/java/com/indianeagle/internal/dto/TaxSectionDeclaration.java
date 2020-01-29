package com.indianeagle.internal.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * DTO for TaxSectionDeclaration
 * <p>
 * User: kalesha
 * Date: 7/31/2017
 */

@Component
@Entity
@Table(name = "TAX_SECTION_DECLARATIONS")
public class TaxSectionDeclaration extends BaseDto{

    @Column(name = "SUB_SECTION_NAME")
    private String subSectionName;
    @Column(name = "ACTIVE")
    private boolean active = true;

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
