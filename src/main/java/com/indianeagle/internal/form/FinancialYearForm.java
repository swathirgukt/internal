package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.IncomeTaxSlab;
import com.indianeagle.internal.dto.Rebate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Form for FinancialYear
 * <p>
 * User: kalesha
 * Date: 7/31/2017
 */

@Component
public class FinancialYearForm {

    private Long id;
    private String fromMonth;
    private String fromYear;
    private String toMonth;
    private String toYear;
    private List<IncomeTaxSlab> incomeTaxSlabs;
    private List<TaxSectionForm> taxSectionForms;
    private List<Rebate> rebates;
    private String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    private String[] years = {"2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};

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

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public List<IncomeTaxSlab> getIncomeTaxSlabs() {
        return incomeTaxSlabs;
    }

    public void setIncomeTaxSlabs(List<IncomeTaxSlab> incomeTaxSlabs) {
        this.incomeTaxSlabs = incomeTaxSlabs;
    }

    public List<TaxSectionForm> getTaxSectionForms() {
        return taxSectionForms;
    }

    public void setTaxSectionForms(List<TaxSectionForm> taxSectionForms) {
        this.taxSectionForms = taxSectionForms;
    }

    public List<Rebate> getRebates() {
        return rebates;
    }

    public void setRebates(List<Rebate> rebates) {
        this.rebates = rebates;
    }

    public String[] getMonths() {
        return months;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }

    public String[] getYears() {
        return years;
    }

    public void setYears(String[] years) {
        this.years = years;
    }

}
