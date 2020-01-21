package com.indianeagle.internal.facade;

import com.yana.internal.dto.LeaveDetails;
import com.yana.internal.dto.Leaves;
import com.yana.internal.form.LeaveApproveForm;
import java.util.List;

public interface LeaveDetailsService {

    void saveOrUpdate(LeaveDetails leaveDetails);

    List<LeaveDetails> searchLeaveDetails(LeaveDetails leaveDetails);

    LeaveDetails getLeaveDetailsById(long id);

    /**
     * Method to find the leaves by employee id
     *
     * @param employeeId the employeeId
     * @return leaves
     */
    Leaves findLeaveByEmployeeId(String employeeId);

    /**
     * Method to find all working employeeIds list
     *
     * @return employeeIds list
     */
    List<String> findAllEmployeeIds();

    /**
     * Method to save the approved leave
     *
     * @param leaveApproveForm, the leaveApproveForm
     */
    void approveLeave(LeaveApproveForm leaveApproveForm);
}
