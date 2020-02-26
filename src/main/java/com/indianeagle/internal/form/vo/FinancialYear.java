package com.indianeagle.internal.form.vo;

import java.util.Set;

/**
 * DTO for FinancialYear
 *
 * User: kalesha
 * Date: 7/31/2017
 */
public class FinancialYear {

    private Long id;
    private String fromMonth;
    private String fromYear;
    private String toMonth;
    private String toYear;
    private Set<IncomeTaxSlabVO> incomeTaxSlabVOS;
    private Set<RebateVO> rebateVOS;
    private Set<TaxSection> taxSections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public Set<IncomeTaxSlabVO> getIncomeTaxSlabVOS() {
        return incomeTaxSlabVOS;
    }

    public void setIncomeTaxSlabVOS(Set<IncomeTaxSlabVO> incomeTaxSlabVOS) {
        this.incomeTaxSlabVOS = incomeTaxSlabVOS;
    }

    public Set<RebateVO> getRebateVOS() {
        return rebateVOS;
    }

    public void setRebateVOS(Set<RebateVO> rebateVOS) {
        this.rebateVOS = rebateVOS;
    }

    public Set<TaxSection> getTaxSections() {
        return taxSections;
    }

    public void setTaxSections(Set<TaxSection> taxSections) {
        this.taxSections = taxSections;
    }
}
