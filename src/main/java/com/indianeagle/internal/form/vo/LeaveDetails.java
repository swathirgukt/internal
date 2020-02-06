package com.indianeagle.internal.form.vo;

public class LeaveDetails {

	private Long id;
	private String empId;
	private Long totalLeaves;
	private Long earnLeaves;
	private Long personalLeaves;
	private Long compensatoryLeaves;
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
	 * @return the totalLeaves
	 */
	public Long getTotalLeaves() {
		return totalLeaves;
	}
	/**
	 * @param totalLeaves the totalLeaves to set
	 */
	public void setTotalLeaves(Long totalLeaves) {
		this.totalLeaves = totalLeaves;
	}
	/**
	 * @return the earnLeaves
	 */
	public Long getEarnLeaves() {
		return earnLeaves;
	}
	/**
	 * @param earnLeaves the earnLeaves to set
	 */
	public void setEarnLeaves(Long earnLeaves) {
		this.earnLeaves = earnLeaves;
	}
	/**
	 * @return the personalLeaves
	 */
	public Long getPersonalLeaves() {
		return personalLeaves;
	}
	/**
	 * @param personalLeaves the personalLeaves to set
	 */
	public void setPersonalLeaves(Long personalLeaves) {
		this.personalLeaves = personalLeaves;
	}
	/**
	 * @return the compensatoryLeaves
	 */
	public Long getCompensatoryLeaves() {
		return compensatoryLeaves;
	}
	/**
	 * @param compensatoryLeaves the compensatoryLeaves to set
	 */
	public void setCompensatoryLeaves(Long compensatoryLeaves) {
		this.compensatoryLeaves = compensatoryLeaves;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}


}
