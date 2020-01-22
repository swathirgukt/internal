package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.enums.EmployeeStatusEnum;
import com.indianeagle.internal.form.EmployeeForm;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

/**
 * Custom Employee Repository implementation
 *
 * @author Praveen
 * @Date 22/01/2020
 */
@Repository
@Transactional(readOnly = true)
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @PersistenceUnit
    EntityManager entityManager;

    /**
     * Seraches for employee on the basis of information
     * provided in the form
     *
     * @param employeeForm
     * @return list of employee objects
     */
    @Override
    public List<Employee> searchEmployees(EmployeeForm employeeForm) {
        Query query = entityManager.createNativeQuery(generateQueryForSearchEmployee(employeeForm));
        return query.getResultList();
    }

    /**
     * gives the list of employees based on their status
     *
     * @param empStatus
     * @return List<Employee>
     */

    @Override
    public List<Employee> getBasicSalaryDetails(String empStatus) {
        Query query = null;
        StringBuffer queryString = new StringBuffer();
        String QUERY = "from Employee e ";
        queryString.append(QUERY);

        if (EmployeeStatusEnum.RESIGNED.name().equals(empStatus)) {
            queryString.append(" where (e.resignDate is not null) AND (e.relieveDate is null) ");
        } else if (EmployeeStatusEnum.WORKING.name().equals(empStatus)) {
            queryString.append(" where e.relieveDate is null ");
        } else if (EmployeeStatusEnum.RELIEVED.name().equals(empStatus)) {
            queryString.append(" where e.relieveDate is not null ");
        }
        queryString.append(" order by e.empId ");
        query = entityManager.createNativeQuery(queryString.toString());
        return query.getResultList();

    }

    /**
     * To get the Employees based on Status
     *
     * @param employeeForm
     * @return List<Employee>
     */
    @Override
    public List<Employee> searchBasedOnEmpStatus(EmployeeForm employeeForm) {
        Query query = null;
        StringBuffer queryEmpStatus = new StringBuffer("from Employee e where ");

        if (!SimpleUtils.isEmpty(employeeForm.getType()) && "Joined".equals(employeeForm.getType().trim())) {
            queryEmpStatus.append(" e.joinDate >= '").append(SimpleUtils.YYYY_MM_DD.format(employeeForm.getFromDate())).append("'");
            if (!SimpleUtils.isEmpty(employeeForm.getToDate())) {
                queryEmpStatus.append(" and e.joinDate <= '").append(SimpleUtils.YYYY_MM_DD.format(employeeForm.getToDate())).append("'");
            }
        } else {
            queryEmpStatus.append(" e.relieveDate >= '").append(SimpleUtils.YYYY_MM_DD.format(employeeForm.getFromDate())).append("'");
            if (!SimpleUtils.isEmpty(employeeForm.getToDate())) {
                queryEmpStatus.append(" and e.relieveDate <= '").append(SimpleUtils.YYYY_MM_DD.format(employeeForm.getToDate())).append("'");
            }
        }
        queryEmpStatus.append(" ORDER BY e.empId");
        query = entityManager.createNativeQuery(queryEmpStatus.toString());
        return query.getResultList();
    }


    /**
     * Generate query to search for employees
     */
    private String generateQueryForSearchEmployee(EmployeeForm employeeForm) {

        StringBuilder queryString = new StringBuilder("from Employee e where ");

        if (!SimpleUtils.isEmpty(employeeForm.getEmpId())) {
            return "from Employee e where e.empId = '".concat(employeeForm.getEmpId()).concat("'");
        }
        if (EmployeeStatusEnum.WORKING.name().equalsIgnoreCase(employeeForm.getStatus())) {
            queryString.append(" e.relieveDate is null");
        } else {
            queryString.append("e.relieveDate is not null");
        }
        if (!SimpleUtils.isEmpty(employeeForm.getFirstName()) && !SimpleUtils.isEmpty(employeeForm.getLastName())) {
            queryString.append(" AND ( e.firstName LIKE '").append(employeeForm.getFirstName().trim()).append("%'");
            queryString.append(" OR e.lastName LIKE '").append(employeeForm.getLastName().trim()).append("%'").append(")");
        } else if (!SimpleUtils.isEmpty(employeeForm.getFirstName())) {
            queryString.append(" AND  e.firstName LIKE '").append(employeeForm.getFirstName().trim()).append("%'");
        } else if (!SimpleUtils.isEmpty(employeeForm.getLastName())) {
            queryString.append(" AND e.lastName LIKE '").append(employeeForm.getLastName().trim()).append("%'");
        }
        if (!SimpleUtils.isEmpty(employeeForm.getDepartment())) {
            queryString.append(" AND e.department.deptNo = '").append(employeeForm.getDepartment()).append("'");
        }
        queryString.append(" ORDER BY e.empId");
        return queryString.toString();

    }


}
