package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.DepartmentRepository;
import com.indianeagle.internal.dto.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DepartmentRepositoryTest {
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void loadAll() {
        List<Department> departmentList = departmentRepository.findAll();
        for (Department d : departmentList
        ) {
            System.out.println(d);
        }
    }

    /*@Test
    public void getDepartmentsLike(){
        Department department=new Department();
        department.setDepartment("CC");
        List<Department> departmentList=departmentRepository.findDepartments(department);
        System.out.println(departmentList.size());
    }*/
    @Test
    public void findByDepartmentLike() {
        List<Department> departmentList = departmentRepository.findByDepartmentStartingWith("CC");
        System.out.println(departmentList.size());
    }
}
