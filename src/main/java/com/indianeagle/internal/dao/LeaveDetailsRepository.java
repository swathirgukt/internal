package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.LeaveDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Jpa Respository for LeaveDetails
 *
 * @author : Praveen
 * Date: 22/01/2020
 */
@Repository
public interface LeaveDetailsRepository extends JpaRepository<LeaveDetails, Long> {

    //void saveOrUpdate(LeaveDetails leaveDetails);
    /* unchecked*/
    //List<LeaveDetails> searchLeaveDetails(LeaveDetails leaveDetails);

    /**
     * method to fetch leaveDetails based on emp id
     *
     * @param empId
     * @return
     */
    //todo: if it gives error change it qith query annotation
    @SuppressWarnings("unchecked")
    List<LeaveDetails> findByEmpIdLike(String empId);

    //LeaveDetails getLeaveDetailsById(long id);

}
