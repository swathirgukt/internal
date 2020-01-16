package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * DTO for IncomeTaxSlab
 * <p>
 * User: kalesha
 * Date: 7/31/2017
 */
@Entity
@Table(name = "INCOME_TAX_SLABS")
public class IncomeTaxSlab implements Comparable<IncomeTaxSlab> {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "MINIMUM_INCOME")
    private BigDecimal minIncome;
    @Column(name = "MAXIMUM_INCOME")
    private BigDecimal maxIncome;
    @Column(name = "TAX_RATE")
    private BigDecimal taxRate;
    @Column(name = "ACTIVE")
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
