package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.MonthlySalaryReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
public class MonthlySalaryReportRepositoryTest {
    @Autowired
    MonthlySalaryReportRepository monthlySalaryReportRepository;

    @Test
    public void getMonthlySalaryReport() {
        try {
            monthlySalaryReportRepository.getMonthlySalaryReport(DateUtilForTest.getDate("2018/02/03"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBankSalariesReport() {
        try {
            monthlySalaryReportRepository.getBankSalariesReport(DateUtilForTest.getDate("2018/02/03"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getITReport() {
        try {
            monthlySalaryReportRepository.getITReport(DateUtilForTest.getDate("2018/02/03"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
