package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.ApprovedLeavesRepository;
import com.indianeagle.internal.dto.ApprovedLeaves;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JpaQueriesTest {
    @Autowired
    ApprovedLeavesRepository approvedLeavesRepository;

    @Test
    void checkQuery() {
        Optional<ApprovedLeaves> approvedLeaves = approvedLeavesRepository.findById(1L);
        if (approvedLeaves.isPresent()) {
            System.out.println(approvedLeaves.get());
        } else {
            System.out.println("not prsent");
        }

    }

    @Test
    void getAllApprovedLeaves() {
        Date fromDate = null;
        Date toDate = null;
        List<ApprovedLeaves> approvedLeaves;
        List<ApprovedLeaves> approvedLeavesWithEmpId;
        /*ApprovedLeaves approvedl= new ApprovedLeaves();
        approvedl.setApprovedDate(LocalDate.now());
        approvedl.setCasualLeave(6);
        approvedLeavesRepository.save(approvedl);*/
       /* List<ApprovedLeaves>approvedLeaves= approvedLeavesRepository.findAll();
        System.out.println(approvedLeaves.get(0).getApprovedDate());*/
        try {
            fromDate = DateUtilForTest.getDate("2012/06/20");
            toDate = DateUtilForTest.getDate("2012/06/30");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        approvedLeaves = approvedLeavesRepository.findByFromDateGreaterThanEqualAndToDateLessThanEqualOrderByEmpId(fromDate, toDate);
        ApprovedLeaves ap = approvedLeaves.get(0);
        System.out.println(ap);
        System.out.println(" ================================================");
        approvedLeavesWithEmpId = approvedLeavesRepository.findByEmpIdAndFromDateGreaterThanEqualAndToDateLessThanEqualOrderByEmpId(ap.getEmpId(), fromDate, toDate);
        System.out.println(approvedLeavesWithEmpId.get(0));
    }
}
