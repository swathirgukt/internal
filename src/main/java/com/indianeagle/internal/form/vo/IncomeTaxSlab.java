package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;

/**
 * DTO for IncomeTaxSlab
 *
 * User: kalesha
 * Date: 7/31/2017
 */
public class IncomeTaxSlab implements Comparable<IncomeTaxSlab> {

    private Long id;
    private BigDecimal minIncome;
    private BigDecimal maxIncome;
    private BigDecimal taxRate;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMinIncome() {
        return minIncome;
    }

    public void setMinIncome(BigDecimal minIncome) {
        this.minIncome = minIncome;
    }

    public BigDecimal getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(BigDecimal maxIncome) {
        this.maxIncome = maxIncome;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int compareTo(IncomeTaxSlab incomeTaxSlab) {
        return this.minIncome.compareTo(incomeTaxSlab.getMinIncome());
    }
}
