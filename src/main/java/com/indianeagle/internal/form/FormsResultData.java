package com.indianeagle.internal.form;

import com.indianeagle.internal.util.CalculationRules;
import com.indianeagle.internal.util.SimpleUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author kiran.paluvadi
 * used to represent forms(LIKE ESI,Form V,PF ) result
 */

public class FormsResultData {
    private List<PfForm> pfFormList;
    private int noOfSubscribers;
    private BigDecimal totalWage;
    private Date salaryDate;
    private Long noEmployees;

    private FormESIC formESIC;
    private PtForm ptForm;
    private String address;
    private String nameOfEstablish;
    private BankSalariesForm bankSalariesForm;


    /**
     * @return the bankSalariesForm
     */
    public BankSalariesForm getBankSalariesForm() {
        return bankSalariesForm;
    }

    /**
     * @param bankSalariesForm the bankSalariesForm to set
     */
    public void setBankSalariesForm(BankSalariesForm bankSalariesForm) {
        this.bankSalariesForm = bankSalariesForm;
    }

    /**
     * @return the pfFormList
     */
    public List<PfForm> getPfFormList() {
        return pfFormList;
    }

    /**
     * @param pfFormList the pfFormList to set
     */
    public void setPfFormList(List<PfForm> pfFormList) {
        this.pfFormList = pfFormList;
    }

    /**
     * @return the noOfSubscribers
     */
    public int getNoOfSubscribers() {
        return noOfSubscribers;
    }

    /**
     * @param noOfSubscribers the noOfSubscribers to set
     */
    public void setNoOfSubscribers(int noOfSubscribers) {
        this.noOfSubscribers = noOfSubscribers;
    }

    /**
     * @return the totalWage
     */
    public BigDecimal getTotalWage() {
        return totalWage;
    }

    /**
     * @param totalWage the totalWage to set
     */
    public void setTotalWage(BigDecimal totalWage) {
        this.totalWage = totalWage;
    }

    /**
     * @return the salaryDate
     */
    public Date getSalaryDate() {
        return salaryDate;
    }

    /**
     * @param salaryDate the salaryDate to set
     */
    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    /**
     * @return the noEmployees
     */
    public Long getNoEmployees() {
        return noEmployees;
    }

    /**
     * @param noEmployees the noEmployees to set
     */
    public void setNoEmployees(Long noEmployees) {
        this.noEmployees = noEmployees;
    }


    public String getTotalInWords() {
        return SimpleUtils.numberToWord(this.pfFormList.get(CalculationRules.PF_CALC.size() - 1).getTotal());
    }

    /**
     * @return the ptForm
     */
    public PtForm getPtForm() {
        return ptForm;
    }

    /**
     * @param ptForm the ptForm to set
     */
    public void setPtForm(PtForm ptForm) {
        this.ptForm = ptForm;
    }

    /**
     * @return the formESIC
     */
    public FormESIC getFormESIC() {
        return formESIC;
    }

    /**
     * @param formESIC the formESIC to set
     */
    public void setFormESIC(FormESIC formESIC) {
        this.formESIC = formESIC;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the nameOfEstablish
     */
    public String getNameOfEstablish() {
        return nameOfEstablish;
    }

    /**
     * @param nameOfEstablish the nameOfEstablish to set
     */
    public void setNameOfEstablish(String nameOfEstablish) {
        this.nameOfEstablish = nameOfEstablish;
    }
}
