package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Department Repository
 *
 * @author Praveen
 * @Date 22/01/2020
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    /**
     * Return department object whose department's name starts
     * with given argument
     *
     * @param department
     * @return collection of Department Objects
     */
    List<Department> findByDepartmentStartingWith(String department);
}
