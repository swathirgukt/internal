package com.indianeagle.internal.dto;

import org.apache.commons.lang.StringUtils;
import javax.persistence.*;
import java.util.Date;

/**
 * DTO to represent the employee nominee details
 * User: praveen
 * Date: 1/23/13
 * Time: 10:46 AM
 */
@Entity
@Table(name = "EMPLOYEE_NOMINEE")
public class Nominee {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DOB")
    private Date dateOfBirth;
    @Column(name = "RELATION")
    private String relation;
    @Column(name = "CONTACT_NO")
    private String contactNumber;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "EMP_ID", unique = true)
    private Employee employee;


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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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
