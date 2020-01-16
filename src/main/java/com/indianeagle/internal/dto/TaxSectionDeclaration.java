package com.indianeagle.internal.dto;

import javax.persistence.*;

/**
 * DTO for TaxSectionDeclaration
 * <p>
 * User: kalesha
 * Date: 7/31/2017
 */
@Entity
@Table(name = "TAX_SECTION_DECLARATIONS")
public class TaxSectionDeclaration {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "SUB_SECTION_NAME")
    private String subSectionName;
    @Column(name = "ACTIVE")
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
