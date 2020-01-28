package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.EmployeeSettlementRepository;
import com.indianeagle.internal.dto.EmployeeSettlement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
public class EmployeeSettlementRepositoryTest {
    @Autowired
    EmployeeSettlementRepository employeeSettlementRepository;

    @Test
    public void getMonthlyESIReport() {
        try {
            List<Object> objects = employeeSettlementRepository.getMonthlyESIReport(DateUtilForTest.getDate("2012/12/23"));
            System.out.println(objects.size());
            System.out.println(objects.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findResignedEmployeeSettlementByEmployeeId() {
        EmployeeSettlement e = employeeSettlementRepository.findResignedEmployeeSettlementByEmployeeId("YSPL1014");
        // System.out.println(e.toString());
    }

    @Test
    public void findMonthlyEmployeeSettlement() {
        List<EmployeeSettlement> employeeSettlement = null;
        try {
            employeeSettlement = employeeSettlementRepository.findMonthlyEmployeeSettlement(DateUtilForTest.getDate("2012/12/23"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(employeeSettlement.size());
    }

    @Test
    public void findMonthlySettlementReport() {
        List<Long> l = null;
        try {
            l = employeeSettlementRepository.findMonthlySettlementReport(DateUtilForTest.getDate("2012/12/23"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(l.size());
    }

    @Test
    public void findEmployeeSettlementByDate() {
        List<EmployeeSettlement> employeeSettlements = null;
        try {
            employeeSettlements = employeeSettlementRepository.findEmployeeSettlementByDate(DateUtilForTest.getDate("2012/12/23"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(employeeSettlements.size());
    }
}
