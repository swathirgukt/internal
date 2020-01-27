package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.FinancialYearRepository;
import com.indianeagle.internal.dto.FinancialYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FinancialYesrRepositoryTest {
    @Autowired
    FinancialYearRepository financialYearRepository;

    @Test
    public void findAllFinancialYearSections() {
        List<FinancialYear> financialYears = financialYearRepository.findAllFinancialYearSections();
        System.out.println(financialYears.size());
    }

    @Test
    public void finacialYearbyMonth() {
        List<FinancialYear> financialYears = financialYearRepository.findFinancialYearSectionsByFinancialYear("APR", "2017", "MAR", "2018");
        System.out.println(financialYears.size());
    }
}
