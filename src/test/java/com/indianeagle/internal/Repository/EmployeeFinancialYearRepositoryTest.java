package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.EmployeeFinancialYearRepository;
import com.indianeagle.internal.dto.EmployeeFinancialYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeFinancialYearRepositoryTest {
    @Autowired
    EmployeeFinancialYearRepository employeeFinancialYearRepository;

    @Test
    public void findEmployeeFinancialYearByEmpId() {
        List<EmployeeFinancialYear> employeeFinancialYears = employeeFinancialYearRepository.findEmployeeFinancialYearByEmpId("YSPL1022");
        System.out.println(employeeFinancialYears.size());
        for (EmployeeFinancialYear e : employeeFinancialYears
        ) {
            System.out.println(e);
        }
    }

    @Test
    public void findEmpyear() {
        List<EmployeeFinancialYear> employeeFinancialYears = employeeFinancialYearRepository.findEmployeeFinancialYearWithEmpIdAndMonthAndYear("YSPL1034", "APR", "2017", "MAR", "2018");
        System.out.println(employeeFinancialYears.size());

    }
}
