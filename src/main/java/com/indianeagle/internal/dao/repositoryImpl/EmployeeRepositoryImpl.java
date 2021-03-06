package com.indianeagle.internal.dao.repositoryImpl;

import com.indianeagle.internal.dao.repository.EmployeeRepositoryCustom;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.enums.EmployeeStatusEnum;
import com.indianeagle.internal.form.EmployeeForm;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
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
        Query query = entityManager.createQuery(generateQueryForSearchEmployee(employeeForm), Employee.class);
        return query.getResultList();
        // org.springframework.data.jpa.repository.Query query1=
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
        String QUERY = "select * from EMPLOYEE e ";
        queryString.append(QUERY);

        if (EmployeeStatusEnum.RESIGNED.name().equals(empStatus)) {
            queryString.append(" where (RESIGN_DATE is not null) AND (RELIEVE_DATE is null) ");
        } else if (EmployeeStatusEnum.WORKING.name().equals(empStatus)) {
            queryString.append(" where RELIEVE_DATE is null ");
        } else if (EmployeeStatusEnum.RELIEVED.name().equals(empStatus)) {
            queryString.append(" where RELIEVE_DATE is not null ");
        }
        queryString.append(" order by EMP_ID ");
        query = entityManager.createNativeQuery(queryString.toString(), Employee.class);
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
        StringBuffer queryEmpStatus = new StringBuffer("SELECT * from EMPLOYEE e where ");

        if (!SimpleUtils.isEmpty(employeeForm.getType()) && "Joined".equals(employeeForm.getType().trim())) {
            queryEmpStatus.append(" JOIN_DATE >= '").append(SimpleUtils.YYYY_MM_DD.format(employeeForm.getFromDate())).append("'");
            if (!SimpleUtils.isEmpty(employeeForm.getToDate())) {
                queryEmpStatus.append(" and JOIN_DATE <= '").append(SimpleUtils.YYYY_MM_DD.format(employeeForm.getToDate())).append("'");
            }
        } else {
            queryEmpStatus.append(" RELIEVE_DATE >= '").append(SimpleUtils.YYYY_MM_DD.format(employeeForm.getFromDate())).append("'");
            if (!SimpleUtils.isEmpty(employeeForm.getToDate())) {
                queryEmpStatus.append(" and RELIEVE_DATE <= '").append(SimpleUtils.YYYY_MM_DD.format(employeeForm.getToDate())).append("'");
            }
        }
        queryEmpStatus.append(" ORDER BY EMP_ID");
        query = entityManager.createNativeQuery(queryEmpStatus.toString(),Employee.class);
        return query.getResultList();
    }


    /**
     * Generate query to search for employees
     */
/*    private String generateQueryForSearchEmployee(EmployeeForm employeeForm) {

        StringBuilder queryString = new StringBuilder("select e from Employee e where ");

        if (!SimpleUtils.isEmpty(employeeForm.getEmpId())) {
            return "select e from Employee e right join fetch Department d  where EMP_ID = '".concat(employeeForm.getEmpId()).concat("'");
        }
        if (EmployeeStatusEnum.WORKING.name().equalsIgnoreCase(employeeForm.getStatus())) {
            queryString.append(" RELIEVE_DATE is null");
        } else {
            queryString.append("RELIEVE_DATE is not null");
        }
        if (!SimpleUtils.isEmpty(employeeForm.getFirstName()) && !SimpleUtils.isEmpty(employeeForm.getLastName())) {
            queryString.append(" AND ( FIRST_NAME LIKE '").append(employeeForm.getFirstName().trim()).append("%'");
            queryString.append(" OR LAST_NAME LIKE '").append(employeeForm.getLastName().trim()).append("%'").append(")");
        } else if (!SimpleUtils.isEmpty(employeeForm.getFirstName())) {
            queryString.append(" AND  FIRST_NAME LIKE '").append(employeeForm.getFirstName().trim()).append("%'");
        } else if (!SimpleUtils.isEmpty(employeeForm.getLastName())) {
            queryString.append(" AND LAST_NAME LIKE '").append(employeeForm.getLastName().trim()).append("%'");
        }
        if (!SimpleUtils.isEmpty(employeeForm.getDepartment())) {
            queryString.append(" AND department.deptNo = '").append(employeeForm.getDepartment()).append("'");
        }
        queryString.append(" ORDER BY EMP_ID");
        return queryString.toString();

    }*/
    private String generateQueryForSearchEmployee(EmployeeForm employeeForm) {

        StringBuilder queryString = new StringBuilder("select e from Employee e where ");

        if (!SimpleUtils.isEmpty(employeeForm.getEmpId())) {
            return "select e from Employee e left  join fetch e.department d  where e.empId ='"+employeeForm.getEmpId()+"'";
        }
        if (EmployeeStatusEnum.WORKING.name().equalsIgnoreCase(employeeForm.getStatus())) {
            queryString.append(" RELIEVE_DATE is null");
        } else {
            queryString.append("RELIEVE_DATE is not null");
        }
        if (!SimpleUtils.isEmpty(employeeForm.getFirstName()) && !SimpleUtils.isEmpty(employeeForm.getLastName())) {
            queryString.append(" AND ( FIRST_NAME LIKE '").append(employeeForm.getFirstName().trim()).append("%'");
            queryString.append(" OR LAST_NAME LIKE '").append(employeeForm.getLastName().trim()).append("%'").append(")");
        } else if (!SimpleUtils.isEmpty(employeeForm.getFirstName())) {
            queryString.append(" AND  FIRST_NAME LIKE '").append(employeeForm.getFirstName().trim()).append("%'");
        } else if (!SimpleUtils.isEmpty(employeeForm.getLastName())) {
            queryString.append(" AND LAST_NAME LIKE '").append(employeeForm.getLastName().trim()).append("%'");
        }
        if (!SimpleUtils.isEmpty(employeeForm.getDepartment())) {
            queryString.append(" AND department.department = '").append(employeeForm.getDepartment()).append("'");
        }
        queryString.append(" ORDER BY EMP_ID");
        return queryString.toString();

    }

    /**
     * This method to find the list of employees based on date of birth
     *
     * @return list of employees
     */
    public List<Employee> findByDob() {
        Query query = entityManager.createQuery("select e FROM Employee e WHERE DAY(e.dob) = DAY(CURRENT_DATE)+1 ");
        return query.getResultList();

    }

    /**
     * This method to find the employees based on date of joining
     *
     * @return
     */

    public List<Employee> findByJoinDate() {
        Query query = entityManager.createNativeQuery("select * FROM EMPLOYEE  WHERE DAY(JOIN_DATE) = DAY(CURRENT_DATE) AND MONTH(JOIN_DATE) = MONTH(CURRENT_DATE)+1");
        return query.getResultList();
    }




}
