package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Department Repository
 *
 * @author Praveen
 * @Date 22/01/2020
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, DepartmentRepositoryCustom {

}
