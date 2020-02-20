package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;

/**
 * Rebate Class DTO
 * <p/>
 * User: kalesha
 * Date: 8/16/2017
 */
public class RebateVO {

    private Long id;
    private String rebateName;
    private BigDecimal rebateApplySalary;
    private BigDecimal rebateAmount;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
