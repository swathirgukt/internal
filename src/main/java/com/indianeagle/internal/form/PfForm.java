package com.indianeagle.internal.form;

import java.math.BigDecimal;

public class PfForm {
    private BigDecimal orgContribution = BigDecimal.ZERO;
    private BigDecimal empContribution = BigDecimal.ZERO;
    private BigDecimal admCharges = BigDecimal.ZERO;
    private BigDecimal inspCharges = BigDecimal.ZERO;
    private BigDecimal penalDamages = BigDecimal.ZERO;
    private BigDecimal miscPayment = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;

    /**
     * @return the orgContribution
     */
    public BigDecimal getOrgContribution() {
        return orgContribution;
    }

    /**
     * @param orgContribution the orgContribution to set
     */
    public void setOrgContribution(BigDecimal orgContribution) {
        this.orgContribution = orgContribution;
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
     * @return the admCharges
     */
    public BigDecimal getAdmCharges() {
        return admCharges;
    }

    /**
     * @param admCharges the admCharges to set
     */
    public void setAdmCharges(BigDecimal admCharges) {
        this.admCharges = admCharges;
    }

    /**
     * @return the inspCharges
     */
    public BigDecimal getInspCharges() {
        return inspCharges;
    }

    /**
     * @param inspCharges the inspCharges to set
     */
    public void setInspCharges(BigDecimal inspCharges) {
        this.inspCharges = inspCharges;
    }

    /**
     * @return the penalDamages
     */
    public BigDecimal getPenalDamages() {
        return penalDamages;
    }

    /**
     * @param penalDamages the penalDamages to set
     */
    public void setPenalDamages(BigDecimal penalDamages) {
        this.penalDamages = penalDamages;
    }

    /**
     * @return the miscPayment
     */
    public BigDecimal getMiscPayment() {
        return miscPayment;
    }

    /**
     * @param miscPayment the miscPayment to set
     */
    public void setMiscPayment(BigDecimal miscPayment) {
        this.miscPayment = miscPayment;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
