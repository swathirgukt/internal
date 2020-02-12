package com.indianeagle.internal.form.vo;

import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * DTO to represent the employee nominee details
 * User: praveen
 * Date: 1/23/13
 * Time: 10:46 AM
 */
public class NomineeVO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String relation;
    private String contactNumber;
    private EmployeeVO employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public EmployeeVO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeVO employee) {
        this.employee = employee;
    }

    /**
	 * @return the Nominee fullName
	 */
	public String getFullName() {
		StringBuffer name = new StringBuffer();
        name.append(StringUtils.capitalize(getFirstName()));
        if (StringUtils.isNotBlank(getMiddleName()))
            name.append(" ").append(getMiddleName());
        return name.append(" ").append(StringUtils.capitalize(getLastName())).toString();
	}
}
