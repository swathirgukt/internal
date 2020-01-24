package com.indianeagle.internal;

import com.indianeagle.internal.dao.repository.ChequeDetailsRepository;
import com.indianeagle.internal.dto.ChequeDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ChequeDetailsTest {
    @Autowired
    ChequeDetailsRepository chequeDetailsRepository;

    @Test
    void getChequeDetail() {
        ChequeDetails chequeDetails = chequeDetailsRepository.findById(1l).get();
        System.out.println(chequeDetails
        );
    }

    /**
     * test for findByIdAndChequeDateBetweenAndAmount(long id, Date startDate, Date endDate, BigDecimal amount);
     */
    @Test
    void findByIdAndDateAndAmout() {
        Date date = null;
        Date todate = null;
        BigDecimal bigDecimal = new BigDecimal("5000");
        System.out.println(bigDecimal.doubleValue());
        try {
            date = DateUtilForTest.getDate("2011/08/03");
            todate = DateUtilForTest.getDate("2012/08/03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        System.out.println(todate);
        List<ChequeDetails> chequeDetailsList = chequeDetailsRepository.findByChequeDateAndChequeDateBetweenAndAmountOrderByChequeDate(date, date, todate, bigDecimal);
        System.out.println(chequeDetailsList.size());
        System.out.println(chequeDetailsList.get(0));
    }
}
