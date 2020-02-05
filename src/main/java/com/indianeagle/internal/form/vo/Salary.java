package com.indianeagle.internal.form.vo;

import java.math.BigDecimal;

public class Salary {

	private Long id;
	private BigDecimal basic = BigDecimal.ZERO;
	private BigDecimal hra = BigDecimal.ZERO;
	private BigDecimal conveyence = BigDecimal.ZERO;
	private BigDecimal pfEmp = BigDecimal.ZERO;
	private BigDecimal pfCom = BigDecimal.ZERO;
	private BigDecimal medicalAllowance = BigDecimal.ZERO;
	private BigDecimal specialAllowance = BigDecimal.ZERO;
	private BigDecimal otherAllowance = BigDecimal.ZERO;
	private BigDecimal performanceIncentives = BigDecimal.ZERO;
	private BigDecimal esi = BigDecimal.ZERO;
	private BigDecimal incomeTax = BigDecimal.ZERO;
	private BigDecimal loanAmount = BigDecimal.ZERO;
	private BigDecimal medicalInsurance = BigDecimal.ZERO;
	private BigDecimal telephoneAllowance = BigDecimal.ZERO;
	private BigDecimal internetAllowance  = BigDecimal.ZERO;
	private BigDecimal uniformAllowance = BigDecimal.ZERO;
	private BigDecimal performanceLinkedPay = BigDecimal.ZERO;
	private BigDecimal bonus = BigDecimal.ZERO;

    private boolean incomeTaxEligible;
	private boolean esiEligible;
	private boolean pfRequired;

	/**
	 * returns the Gross Salary
	 * @return grossSal as a BigDecimal value
	 */
	public BigDecimal getGrossSal(){
		return basic.add(hra).add(conveyence).add(medicalAllowance).add(specialAllowance).add(otherAllowance).add(telephoneAllowance).add(internetAllowance).add(uniformAllowance).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the basic
	 */
	public BigDecimal getBasic() {
		return basic;
	}
	/**
	 * @param basic the basic to set
	 */
	public void setBasic(BigDecimal basic) {
		this.basic = basic;
	}

	/**
	 * @return the conveyence
	 */
	public BigDecimal getConveyence() {
		return conveyence;
	}
	/**
	 * @param conveyence the conveyence to set
	 */
	public void setConveyence(BigDecimal conveyence) {
		this.conveyence = conveyence;
	}
	/**
	 * @return the pfEmp
	 */
	public BigDecimal getPfEmp() {
		return pfEmp;
	}
	/**
	 * @param pfEmp the pfEmp to set
	 */
	public void setPfEmp(BigDecimal pfEmp) {
		this.pfEmp = pfEmp;
	}
	/**
	 * @return the pfCom
	 */
	public BigDecimal getPfCom() {
		return pfCom;
	}
	/**
	 * @param pfCom the pfCom to set
	 */
	public void setPfCom(BigDecimal pfCom) {
		this.pfCom = pfCom;
	}
	/**
	 * @return the medicalAllowance
	 */
	public BigDecimal getMedicalAllowance() {
		return medicalAllowance;
	}
	/**
	 * @param medicalAllowance the medicalAllowance to set
	 */
	public void setMedicalAllowance(BigDecimal medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}
	/**
	 * @return the specialAllowance
	 */
	public BigDecimal getSpecialAllowance() {
		return specialAllowance;
	}
	/**
	 * @param specialAllowance the specialAllowance to set
	 */
	public void setSpecialAllowance(BigDecimal specialAllowance) {
		this.specialAllowance = specialAllowance;
	}
	/**
	 * @return the otherAllowance
	 */
	public BigDecimal getOtherAllowance() {
		return otherAllowance;
	}
	/**
	 * @param otherAllowance the otherAllowance to set
	 */
	public void setOtherAllowance(BigDecimal otherAllowance) {
		this.otherAllowance = otherAllowance;
	}
	/**
	 * @return the hra
	 */
	public BigDecimal getHra() {
		return hra;
	}
	/**
	 * @param hra the hra to set
	 */
	public void setHra(BigDecimal hra) {
		this.hra = hra;
	}
	/**
	 * @return the performanceIncentives
	 */
	public BigDecimal getPerformanceIncentives() {
		return performanceIncentives;
	}
	/**
	 * @param performanceIncentives the performanceIncentives to set
	 */
	public void setPerformanceIncentives(BigDecimal performanceIncentives) {
		this.performanceIncentives = performanceIncentives;
	}
	/**
	 * @return the esi
	 */
	public BigDecimal getEsi() {
		return esi;
	}
	/**
	 * @param esi the esi to set
	 */
	public void setEsi(BigDecimal esi) {
		this.esi = esi;
	}
	/**
	 * @return the esiEligible
	 */
	public boolean getEsiEligible() {
		return esiEligible;
	}
	/**
	 * @param esiEligible the esiEligible to set
	 */
	public void setEsiEligible(boolean esiEligible) {
		this.esiEligible = esiEligible;
	}
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}
	/**
	 * @return the loanAmount
	 */
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	/**
	 * @return the medicalInsurance
	 */
	public BigDecimal getMedicalInsurance() {
		return medicalInsurance;
	}
	/**
	 * @param medicalInsurance the medicalInsurance to set
	 */
	public void setMedicalInsurance(BigDecimal medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

    public boolean isPfRequired() {
        return pfRequired;
    }

    public void setPfRequired(boolean pfRequired) {
        this.pfRequired = pfRequired;
    }

    /**
     * @return the incomeTaxEligible
     */
    public boolean isIncomeTaxEligible() {
        return incomeTaxEligible;
    }

    /**
     * method to set incomeTaxEligible
     * @param incomeTaxEligible
     */
    public void setIncomeTaxEligible(boolean incomeTaxEligible) {
        this.incomeTaxEligible = incomeTaxEligible;
    }

    /**
     * @return internetAllowance
     */
    public BigDecimal getInternetAllowance() {
        return internetAllowance;
}

    public void setInternetAllowance(BigDecimal internetAllowance) {
        this.internetAllowance = internetAllowance;
    }

    /**
     * @return telephoneAllowance
     */
    public BigDecimal getTelephoneAllowance() {
        return telephoneAllowance;
    }

    public void setTelephoneAllowance(BigDecimal telephoneAllowance) {
        this.telephoneAllowance = telephoneAllowance;
    }

    /**
     * @return uniformAllowance
     */
    public BigDecimal getUniformAllowance() {
        return uniformAllowance;
    }

    /**
     * method to set uniformAllowance
     *
     * @param uniformAllowance
     */
    public void setUniformAllowance(BigDecimal uniformAllowance) {
        this.uniformAllowance = uniformAllowance;
    }

	public BigDecimal getPerformanceLinkedPay() {
		return performanceLinkedPay;
	}

	public void setPerformanceLinkedPay(BigDecimal performanceLinkedPay) {
		this.performanceLinkedPay = performanceLinkedPay;
	}


	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
}
