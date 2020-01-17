package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Rebate Class DTO
 * <p/>
 * User: kalesha
 * Date: 8/16/2017
 */
@Entity
@Table(name = "REBATES")
public class Rebate  extends BaseDto{

    @Column(name = "REBATE_NAME")
    private String rebateName;
    @Column(name = "REBATE_APPLY_SALARY")
    private BigDecimal rebateApplySalary;
    @Column(name = "REBATE_AMOUNT")
    private BigDecimal rebateAmount;
    @Column(name = "ACTIVE", nullable = false)
    private boolean active = true;

    public String getRebateName() {
        return rebateName;
    }

    public void setRebateName(String rebateName) {
        this.rebateName = rebateName;
    }

    public BigDecimal getRebateApplySalary() {
        return rebateApplySalary;
    }

    public void setRebateApplySalary(BigDecimal rebateApplySalary) {
        this.rebateApplySalary = rebateApplySalary;
    }

    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
