package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.ChequeDetailsRepository;
import com.indianeagle.internal.dto.ChequeDetails;
import com.indianeagle.internal.form.ChequeDetailsForm;
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
            todate = new Date();
            //todate = new Date();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        System.out.println(todate);
        List<ChequeDetails> chequeDetailsList = chequeDetailsRepository.findByChequeDateBetweenAndAmountOrderByChequeDate(date, todate, bigDecimal);
        System.out.println(chequeDetailsList.size());
        for (ChequeDetails c : chequeDetailsList
        ) {
            System.out.println(c);
        }
    }

    @Test
    public void getById() {
        System.out.println(chequeDetailsRepository.findById(1l).get());
    }

    @Test
    public void Insert() {
        ChequeDetails chequeDetails = new ChequeDetails();
        chequeDetails.setAmount(new BigDecimal(5000));
        chequeDetails.setBank("HDFC");
        chequeDetails.setNameOfPay("NAGENDRA");
        try {
            chequeDetails.setChequeDate(DateUtilForTest.getDate("2020/01/25"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        chequeDetails.setChequeNo(25645L);
        chequeDetailsRepository.save(chequeDetails);
    }

    @Test
    public void postChequeDetailsTest() throws ParseException {
        // List<ChequeDetails> chequeDetailsList = chequeDetailsRepository.postChequeDetails();
        List<ChequeDetails> chequeDetailsList = chequeDetailsRepository.findByChequeDate(DateUtilForTest.getDate("2020/01/25"));
        System.out.println(chequeDetailsList.size());
        System.out.println(chequeDetailsList.get(0));
    }

    /**
     *
     */
    @Test
    public void searchChequeDetails() {
        ChequeDetailsForm chequeDetailsForm = new ChequeDetailsForm();
        chequeDetailsForm.setAmount(new BigDecimal(5000));
        try {
            chequeDetailsForm.setFromDate(DateUtilForTest.getDate("2011/08/03"));
            chequeDetailsForm.setToDate(new Date());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<ChequeDetails> chequeDetailsList = null; //chequeDetailsRepository.searchChequeDetails(chequeDetailsForm);

        for (ChequeDetails c : chequeDetailsList
        ) {
            System.out.println(c);
        }
    }

    @Test
    public void delete() {
        /*ChequeDetails c1=chequeDetailsRepository.findById(7l).get();
        ChequeDetails c2=chequeDetailsRepository.findById(8l).get();
        ChequeDetails c3=chequeDetailsRepository.findById(9l).get();
        ChequeDetails c4=chequeDetailsRepository.findById(10l).get();*/
        chequeDetailsRepository.deleteById(7l);
        chequeDetailsRepository.deleteById(8l);
        chequeDetailsRepository.deleteById(9l);
        chequeDetailsRepository.deleteById(10l);
    }
}
