package com.indianeagle.internal.form;

import java.math.BigDecimal;

/**
 * class for ESIC form
 *
 * @author kiran.paluvadi
 */
public class FormESIC {

    private String rupeesInWords;
    private BigDecimal totalWages;
    private BigDecimal totalAmount;
    private int noOfEmployees;
    private BigDecimal empContribution;
    private BigDecimal employersContribution;
    private String employerCode;
    private String address;
    private String forMonth;

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
     * @return the rupeesInWords
     */
    public String getRupeesInWords() {
        return rupeesInWords;
    }

    /**
     * @param rupeesInWords the rupeesInWords to set
     */
    public void setRupeesInWords(String rupeesInWords) {
        this.rupeesInWords = rupeesInWords;
    }

    /**
     * @return the totalWages
     */
    public BigDecimal getTotalWages() {
        return totalWages;
    }

    /**
     * @param totalWages the totalWages to set
     */
    public void setTotalWages(BigDecimal totalWages) {
        this.totalWages = totalWages;
    }

    /**
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the noOfEmployees
     */
    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    /**
     * @param noOfEmployees the noOfEmployees to set
     */
    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    /**
     * @return the empContribution
     */
    public BigDecimal getEmpContribution() {
        return empContribution;
    }

    /**
     * @param empContribution the empContribution to set
     */
    public void setEmpContribution(BigDecimal empContribution) {
        this.empContribution = empContribution;
    }

    /**
     * @return the employersContribution
     */
    public BigDecimal getEmployersContribution() {
        return employersContribution;
    }

    /**
     * @param employersContribution the employersContribution to set
     */
    public void setEmployersContribution(BigDecimal employersContribution) {
        this.employersContribution = employersContribution;
    }

    /**
     * @return the employerCodeNo
     */
    public String getEmployerCode() {
        return employerCode;
    }

    /**
     * @param employerCode the employerCodeNo to set
     */
    public void setEmployerCode(String employerCode) {
        this.employerCode = employerCode;
    }

    /**
     * @return the forMonth
     */
    public String getForMonth() {
        return forMonth;
    }

    /**
     * @param forMonth the forMonth to set
     */
    public void setForMonth(String forMonth) {
        this.forMonth = forMonth;
    }
}
