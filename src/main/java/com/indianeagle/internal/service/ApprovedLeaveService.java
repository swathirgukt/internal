package com.indianeagle.internal.facade;

import com.indianeagle.internal.form.LeaveApproveForm;
import java.util.Date;
import java.util.List;

/**
 * This interface provide approvedLeaves related Service
 * User: nageswaramma
 * Date: 5/29/12
 * Time: 2:47 PM
 */
public interface ApprovedLeaveService {

    /**
     * This method to get approval Leaves of employee
     *
     * @param empId
     * @param fromDate
     * @param toDate
     * @return list the list of approvedLeaves of the employee
     */
    List<LeaveApproveForm> getApprovedLeaves(String empId, Date fromDate, Date toDate);

    /**
     * This method to get all employee approval Leaves
     *
     * @param fromDate
     * @param toDate
     * @return list the list of approvedLeaves of the employee
     */
    List<LeaveApproveForm> getApprovedLeaves(Date fromDate, Date toDate);


}