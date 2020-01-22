package com.indianeagle.internal.form;

import com.indianeagle.internal.dto.Department;
import com.indianeagle.internal.dto.Role;

import java.util.List;

/**
 * This class represents the user
 *
 * @author Prabhakar reddy Komatreddy
 * @since 29th, May, 2012
 */
public class UserForm {

    private String empId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<Role> rolesList;
    private String roleName;
    private List<Department> departments;
    private long department;

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the userName to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the roles
     */
    public List<Role> getRolesList() {
        return rolesList;
    }

    /**
     * @param rolesList the roles to set
     */
    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the departments
     */
    public List<Department> getDepartments() {
        return departments;
    }

    /**
     * @param departments the departments to set
     */
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    /**
     * @return the department
     */
    public long getDepartment() {
        return department;
    }

    /**
     * @param department the department id to set
     */
    public void setDepartment(long department) {
        this.department = department;
    }
}
