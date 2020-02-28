package com.indianeagle.internal.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * DTO for EmployeeTaxSectionDeclaration
 * <p>
 * User: kalesha
 * Date: 8/9/2017
 */

@Component
@Entity
@Table(name = "EMPLOYEE_TAX_SECTION_DECLARATIONS")
public class EmployeeTaxSectionDeclaration extends BaseDto {

    @Column(name = "SUB_SECTION_NAME")
    private String subSectionName;
    @Column(name = "SAVE_AMOUNT")
    private BigDecimal saveAmount;
    @Column(name = "ACTIVE")
    private boolean active = true;

    public String getSubSectionName() {
        return subSectionName;
    }

    public void setSubSectionName(String subSectionName) {
        this.subSectionName = subSectionName;
    }

    public BigDecimal getSaveAmount() {
        if(saveAmount==null){
            saveAmount = BigDecimal.ZERO;
        }
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
