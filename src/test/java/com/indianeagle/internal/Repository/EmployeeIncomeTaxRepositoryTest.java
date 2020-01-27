package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.EmployeeIncomeTaxRepository;
import com.indianeagle.internal.dto.EmployeeIncomeTax;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeIncomeTaxRepositoryTest {
    @Autowired
    EmployeeIncomeTaxRepository employeeIncomeTaxRepository;

    @Test
    public void findById() {
        List<EmployeeIncomeTax> employeeIncomeTaxes = employeeIncomeTaxRepository.findByEmpId("YSPL1034");
        System.out.println(employeeIncomeTaxes.size());
    }

    @Test
    public void findBidAndMonth() {
        List<EmployeeIncomeTax> employeeIncomeTaxes = employeeIncomeTaxRepository.findEmployeeIncomeTaxWithEmpIdAndFinancialYear("YSPL1034", "APR", "2017", "MAR", "2018");
        System.out.println(employeeIncomeTaxes.size());
    }
}

