package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Department;

import java.util.List;

/**
 * Custom Department Repository
 *
 * @author Praveen
 * @Date 22/01/2020
 */
public interface DepartmentRepositoryCustom {
    @SuppressWarnings("unchecked")
    List<Department> findDepartments(Department department);
}
