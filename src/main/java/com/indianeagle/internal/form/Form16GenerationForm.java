package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.Rebate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Form of Form16 Generation
 * <p>
 * User: kalesha
 * Date: 8/15/2017
 */

@Component
public class Form16GenerationForm {

    private Long id;
    private BigDecimal grossSalary;
    private BigDecimal incentives;
    private BigDecimal plb;
    private BigDecimal reimbursement;
    private BigDecimal bonus;
    private BigDecimal others;
    private BigDecimal previousCompanyIncome;
    private BigDecimal incomeEarnedByEmployee;
    private BigDecimal taxableIncome;
    private BigDecimal basic40per = BigDecimal.ZERO;
    private BigDecimal hraWithBasic10per = BigDecimal.ZERO;
    private BigDecimal totalActualHRA = BigDecimal.ZERO;
    private BigDecimal hra;
    private List<Rebate> rebates;
    private BigDecimal taxOnIncome=BigDecimal.ZERO;
    private BigDecimal eduCess=BigDecimal.ZERO;
    private BigDecimal totalTaxOnIncome=BigDecimal.ZERO;
    private BigDecimal tdsDeducted=BigDecimal.ZERO;
    private BigDecimal taxTobeDeducted=BigDecimal.ZERO;
    private BigDecimal perMonthTax=BigDecimal.ZERO;
    private List<TaxSectionForm> taxSectionForms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public BigDecimal getIncentives() {
        return incentives;
    }

    public void setIncentives(BigDecimal incentives) {
        this.incentives = incentives;
    }

    public BigDecimal getPlb() {
        return plb;
    }

    public void setPlb(BigDecimal plb) {
        this.plb = plb;
    }

    public BigDecimal getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(BigDecimal reimbursement) {
        this.reimbursement = reimbursement;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getOthers() {
        return others;
    }

    public void setOthers(BigDecimal others) {
        this.others = others;
    }

    public BigDecimal getPreviousCompanyIncome() {
        return previousCompanyIncome;
    }

    public void setPreviousCompanyIncome(BigDecimal previousCompanyIncome) {
        this.previousCompanyIncome = previousCompanyIncome;
    }

    public BigDecimal getIncomeEarnedByEmployee() {
        return incomeEarnedByEmployee;
    }

    public void setIncomeEarnedByEmployee(BigDecimal incomeEarnedByEmployee) {
        this.incomeEarnedByEmployee = incomeEarnedByEmployee;
    }

    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public List<Rebate> getRebates() {
        return rebates;
    }

    public void setRebates(List<Rebate> rebates) {
        this.rebates = rebates;
    }

    public BigDecimal getTaxOnIncome() {
        return taxOnIncome;
    }

    public void setTaxOnIncome(BigDecimal taxOnIncome) {
        this.taxOnIncome = taxOnIncome;
    }

    public BigDecimal getEduCess() {
        return eduCess;
    }

    public void setEduCess(BigDecimal eduCess) {
        this.eduCess = eduCess;
    }

    public BigDecimal getTotalTaxOnIncome() {
        return totalTaxOnIncome;
    }

    public void setTotalTaxOnIncome(BigDecimal totalTaxOnIncome) {
        this.totalTaxOnIncome = totalTaxOnIncome;
    }

    public BigDecimal getTdsDeducted() {
        return tdsDeducted;
    }

    public void setTdsDeducted(BigDecimal tdsDeducted) {
        this.tdsDeducted = tdsDeducted;
    }

    public BigDecimal getTaxTobeDeducted() {
        return taxTobeDeducted;
    }

    public void setTaxTobeDeducted(BigDecimal taxTobeDeducted) {
        this.taxTobeDeducted = taxTobeDeducted;
    }

    public BigDecimal getPerMonthTax() {
        return perMonthTax;
    }

    public void setPerMonthTax(BigDecimal perMonthTax) {
        this.perMonthTax = perMonthTax;
    }

    public List<TaxSectionForm> getTaxSectionForms() {
        return taxSectionForms;
    }

    public void setTaxSectionForms(List<TaxSectionForm> taxSectionForms) {
        this.taxSectionForms = taxSectionForms;
    }

    public BigDecimal getHra() {
        return hra;
    }

    public void setHra(BigDecimal hra) {
        this.hra = hra;
    }

    public BigDecimal getBasic40per() {
        return basic40per;
    }

    public void setBasic40per(BigDecimal basic40per) {
        this.basic40per = basic40per;
    }

    public BigDecimal getHraWithBasic10per() {
        return hraWithBasic10per;
    }

    public void setHraWithBasic10per(BigDecimal hraWithBasic10per) {
        this.hraWithBasic10per = hraWithBasic10per;
    }

    public BigDecimal getTotalActualHRA() {
        return totalActualHRA;
    }

    public void setTotalActualHRA(BigDecimal totalActualHRA) {
        this.totalActualHRA = totalActualHRA;
    }

    @Override
    public String toString() {
        return "Form16GenerationForm{" +
                "id=" + id +
                ", grossSalary=" + grossSalary +
                ", incentives=" + incentives +
                ", plb=" + plb +
                ", reimbursement=" + reimbursement +
                ", bonus=" + bonus +
                ", others=" + others +
                ", previousCompanyIncome=" + previousCompanyIncome +
                ", incomeEarnedByEmployee=" + incomeEarnedByEmployee +
                ", taxableIncome=" + taxableIncome +
                ", basic40per=" + basic40per +
                ", hraWithBasic10per=" + hraWithBasic10per +
                ", totalActualHRA=" + totalActualHRA +
                ", hra=" + hra +
                ", rebates=" + rebates +
                ", taxOnIncome=" + taxOnIncome +
                ", eduCess=" + eduCess +
                ", totalTaxOnIncome=" + totalTaxOnIncome +
                ", tdsDeducted=" + tdsDeducted +
                ", taxTobeDeducted=" + taxTobeDeducted +
                ", perMonthTax=" + perMonthTax +
                ", taxSectionForms=" + taxSectionForms +
                '}';
    }
}
