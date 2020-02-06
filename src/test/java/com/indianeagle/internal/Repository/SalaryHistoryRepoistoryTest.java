package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.SalaryHistoryRepository;
import com.indianeagle.internal.dto.SalaryHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class SalaryHistoryRepoistoryTest {
    static Date date;
    static Date date1;

    static {
        try {
            date = DateUtilForTest.getDate("2009/07/01");
            date1 = DateUtilForTest.getDate("2009/12/04");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    SalaryHistoryRepository salaryHistoryRepository;

    @Test
    public void findbyidaAndDate() {
        List<SalaryHistory> salaryHistories = salaryHistoryRepository.findByEmpIdAndSalaryDateLessThanEqual("YSPL1008", date);
        System.out.println(salaryHistories.size());
    }

    @Test
    public void salaryReport() {
        salaryHistoryRepository.salaryReport(date);
    }

    @Test
    public void salaryReportWithEndDate() {
        salaryHistoryRepository.salaryReport(date, date1);
    }

    @Test
    public void getMonthlyEsiReport() {
        salaryHistoryRepository.getMonthlyESIReport(date);
    }

    @Test
    public void findSalaryHistoryByEmpId() {
        salaryHistoryRepository.findSalaryHistoryByEmpId("YSPL1006", date);
    }

    @Test
    public void findSalaryHistoryByEmpIdAndEndDate() {
        salaryHistoryRepository.findSalaryHistoryByEmpId("YSPL1006", date, date1);
    }

    @Test
    public void findByEmpIdAndSalaryEndDateBetween() {
        salaryHistoryRepository.findByEmpIdAndSalaryEndDateBetween("YSPL1006", date, date1);
    }

    @Test
    public void getMonthlyRtReport() {
        salaryHistoryRepository.getMonthlyRtReport(date);
    }

    @Test
    public void getMonthlypFReport() {
        salaryHistoryRepository.getMonthlyPfReport(date, new BigDecimal("5000"));
    }
    @Test
    public void salaryReport2(){
        List<SalaryHistory> salaryHistories=salaryHistoryRepository.salaryReport(date,date1);
        System.out.println(salaryHistories.size());
    }
}
