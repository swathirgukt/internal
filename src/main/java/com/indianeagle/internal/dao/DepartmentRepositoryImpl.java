package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Department;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom Department Repository implementation
 *
 * @author Praveen
 * @Date 22/01/2020
 */
@Repository
@Transactional(readOnly = true)
public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;


    public List<Department> findDepartments(Department department) {
        Query query = null;
        String deptName = "";
        if ((department != null) && (department.getDepartment() != null)) {
            deptName = department.getDepartment().trim();
            query = entityManager.createNativeQuery("select d from Department d where d.department LIKE '" + deptName + "%'");
        }
        //todo: change retruned value based on calling method logic
        return query != null ? query.getResultList() : new ArrayList<>();
    }

}
