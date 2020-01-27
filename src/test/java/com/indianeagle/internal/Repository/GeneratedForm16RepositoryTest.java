package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.GeneratedForm16Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeneratedForm16RepositoryTest {
    @Autowired
    GeneratedForm16Repository generatedForm16Repository;

    @Test
    public void getPdf() {
        generatedForm16Repository.findPdfByEmpIdAndFinancialYear("YSPL1006", "APR", "2017", "MAR", "2018");
    }
}
