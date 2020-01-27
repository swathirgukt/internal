package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.IncentiveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;

@SpringBootTest
public class IncesntiveRepositoryTest {
    @Autowired
    IncentiveRepository incentiveRepository;

    @Test
    public void findByIncentiveDate() {
        Date date = null;
        try {
            date = DateUtilForTest.getDate("2013/02/03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(incentiveRepository.findByIncentiveDate(date).size());
    }
}
