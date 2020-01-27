package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.LeavesRepository;
import com.indianeagle.internal.dto.Leaves;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LeaveRepositoryTest {
    @Autowired
    LeavesRepository leavesRepository;

    @Test
    public void findLeavesByEmployeeId() {
        List<Leaves> leaves = leavesRepository.findLeavesByEmployeeId("YSPL1006");
        System.out.println(leaves.size());
    }

    @Test
    public void findAll() {
        List<Leaves> leaves = leavesRepository.findAllEmployeeLeaves();
        System.out.println(leaves.size());
    }
}
