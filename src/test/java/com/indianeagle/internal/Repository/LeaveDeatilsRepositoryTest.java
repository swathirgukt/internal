package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.LeaveDetailsRepository;
import com.indianeagle.internal.dto.LeaveDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LeaveDeatilsRepositoryTest {
    @Autowired
    LeaveDetailsRepository leaveDetailsRepository;

    @Test
    public void findByEmpIdLike() {
        List<LeaveDetails> leaveDetails = leaveDetailsRepository.findByEmpIdLike("YSPL10");
        System.out.println(leaveDetails.size());
    }
}
