package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.ApprovedLeaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Dao for ApprovedLeaves
 *
 * @author Praveen
 * @Date 22/01/2020
 */
@Repository
public interface ApprovedLeavesRepository extends JpaRepository<ApprovedLeaves, Long> {


    /**
     * Method to save or update approvedLeaves
     *
     * @param approvedLeaves to persist
     */
    // void saveOrUpdateApprovedLeaves(ApprovedLeaves approvedLeaves);


    /**
     * This method to get employee approvedLeaves from database
     *
     * @param empId
     * @param fromDate
     * @param toDate
     * @return
     */
    // List<ApprovedLeaves> getApprovedLeaves(String empId, Date fromDate, Date toDate);
    List<ApprovedLeaves> findByEmpIdAndFromDateGreaterThanEqualAndToDateLessThanEqualOrderByEmpId(String empId, Date fromDate, Date toDate);

    /**
     * This method to get all employees approvedLeaves from database
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    //IF it fails then use query annotation here
    //List<ApprovedLeaves> getApprovedLeaves(Date fromDate, Date toDate);
    List<ApprovedLeaves> findByFromDateGreaterThanEqualAndToDateLessThanEqualOrderByEmpId(Date fromDate, Date toDate);
}
