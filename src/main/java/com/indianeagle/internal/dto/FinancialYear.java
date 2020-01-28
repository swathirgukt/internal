package com.indianeagle.internal.dto;

import javax.persistence.*;
import java.util.Set;

/**
 * DTO for FinancialYear
 * <p>
 * User: kalesha
 * Date: 7/31/2017
 */
@Entity
@Table(name = "INCOME_TAX_FINANCIAL_YEAR")
public class FinancialYear extends BaseDto{

    @Column(name = "FROM_MONTH")
    private String fromMonth;
    @Column(name = "FROM_YEAR")
    private String fromYear;
    @Column(name = "TO_MONTH")
    private String toMonth;
    @Column(name = "TO_YEAR")
    private String toYear;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINANCIAL_YEAR_ID", nullable = false)
    private Set<IncomeTaxSlab> incomeTaxSlabs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINANCIAL_YEAR_ID", nullable = false)
    private Set<Rebate> rebates;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINANCIAL_YEAR_ID", nullable = false)
    private Set<TaxSection> taxSections;

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

    public Set<IncomeTaxSlab> getIncomeTaxSlabs() {
        return incomeTaxSlabs;
    }

    public void setIncomeTaxSlabs(Set<IncomeTaxSlab> incomeTaxSlabs) {
        this.incomeTaxSlabs = incomeTaxSlabs;
    }

    public Set<Rebate> getRebates() {
        return rebates;
    }

    public void setRebates(Set<Rebate> rebates) {
        this.rebates = rebates;
    }

    public Set<TaxSection> getTaxSections() {
        return taxSections;
    }

    public void setTaxSections(Set<TaxSection> taxSections) {
        this.taxSections = taxSections;
    }

    @Override
    public String toString() {
        return "FinancialYear{" +
                "fromMonth='" + fromMonth + '\'' +
                ", fromYear='" + fromYear + '\'' +
                ", toMonth='" + toMonth + '\'' +
                ", toYear='" + toYear + '\'' +
                ", incomeTaxSlabs=" + incomeTaxSlabs +
                ", rebates=" + rebates +
                ", taxSections=" + taxSections +
                '}';
    }
}
