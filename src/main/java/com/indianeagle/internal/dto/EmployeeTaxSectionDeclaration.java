package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * DTO for EmployeeTaxSectionDeclaration
 * <p>
 * User: kalesha
 * Date: 8/9/2017
 */
@Entity
@Table(name = "EMPLOYEE_TAX_SECTION_DECLARATIONS")
public class EmployeeTaxSectionDeclaration {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "SUB_SECTION_NAME")
    private String subSectionName;
    @Column(name = "SAVE_AMOUNT")
    private BigDecimal saveAmount;
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
