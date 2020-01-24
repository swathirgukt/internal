package com.indianeagle.internal.service.impl.impl;

import com.indianeagle.internal.dao.repository.ApprovedLeavesRepository;
import com.indianeagle.internal.dto.ApprovedLeaves;
import com.indianeagle.internal.form.LeaveApproveForm;
import com.indianeagle.internal.service.ApprovedLeaveService;
import com.indianeagle.internal.util.DateUtils;
import com.indianeagle.internal.util.SimpleUtils;
import java.util.*;

/**
 * This class implements the ApprovedLeaveService
 * User: nageswaramma
 * Date: 5/29/12
 * Time: 2:53 PM
 */
public class ApprovedLeaveServiceImpl implements ApprovedLeaveService {

    private ApprovedLeavesRepository approvedLeavesRepository;

    /**
     * This method to get approval Leaves of employee
     * @param empId
     * @param fromDate
     * @param toDate
     * @return list the list of approvedLeaves of the employee
     */
    public List<LeaveApproveForm> getApprovedLeaves(String empId, Date fromDate, Date toDate) {
        List<ApprovedLeaves> approvedLeaveses = approvedLeavesRepository.findByEmpIdAndFromDateGreaterThanEqualAndToDateLessThanEqualOrderByEmpId(empId, fromDate, toDate);
        List<LeaveApproveForm> listOfLeaveApproves = new ArrayList<LeaveApproveForm>();
        Map<String, LeaveApproveForm> employeeLeaves = new LinkedHashMap<String, LeaveApproveForm>();
        for (ApprovedLeaves approvedLeaves : approvedLeaveses) {
            String key = SimpleUtils.dateInWord(approvedLeaves.getFromDate());
            LeaveApproveForm leaveApproveForm = employeeLeaves.get(key);
            if (leaveApproveForm == null) {
                leaveApproveForm = new LeaveApproveForm();
            }
            createApprovedLeave(leaveApproveForm, approvedLeaves);
            leaveApproveForm.setLeaveApprovalMonth(key);
            employeeLeaves.put(key, leaveApproveForm);
        }
        listOfLeaveApproves.addAll(employeeLeaves.values());
        return listOfLeaveApproves;
    }


    /**
     * This method to get all employees approval Leaves
     * @param fromDate
     * @param toDate
     * @return list the list of approvedLeaves of the all employees
     */
    public List<LeaveApproveForm> getApprovedLeaves(Date fromDate, Date toDate) {
        List<ApprovedLeaves> approvedLeaveses = approvedLeavesRepository.findByFromDateGreaterThanEqualAndToDateLessThanEqualOrderByEmpId(fromDate, toDate);
        Map<String, LeaveApproveForm> employeeLeaves = new LinkedHashMap<String, LeaveApproveForm>();
        List<LeaveApproveForm> listOfLeaveApproves = new ArrayList<LeaveApproveForm>();
        for (ApprovedLeaves approvedLeaves : approvedLeaveses) {
            LeaveApproveForm leaveApproveForm = employeeLeaves.get(approvedLeaves.getEmpId());
            if (leaveApproveForm == null) {
                leaveApproveForm = new LeaveApproveForm();
            }
            createApprovedLeave(leaveApproveForm, approvedLeaves);
            employeeLeaves.put(approvedLeaves.getEmpId(), leaveApproveForm);
        }
        listOfLeaveApproves.addAll(employeeLeaves.values());
        return listOfLeaveApproves;
    }

    /**
     * This method to create approved leaves of employee
     * @param leaveApproveForm
     * @param approvedLeaves
     * @return leaveApproveForm
     */
    private void createApprovedLeave(LeaveApproveForm leaveApproveForm, ApprovedLeaves approvedLeaves) {
        leaveApproveForm.setCasualLeave(leaveApproveForm.getCasualLeave() + approvedLeaves.getCasualLeave());
        leaveApproveForm.setSickLeave(leaveApproveForm.getSickLeave() + approvedLeaves.getSickLeave());
        leaveApproveForm.setCompensatoryLeave(leaveApproveForm.getCompensatoryLeave() + approvedLeaves.getCompensatoryLeave());
        leaveApproveForm.setLop(leaveApproveForm.getLop() + approvedLeaves.getLop());
        leaveApproveForm.setTotalNumberOfAbsentDays(leaveApproveForm.getTotalNumberOfAbsentDays() + approvedLeaves.getTotalNumberOfAbsentDays());
        leaveApproveForm.setEmpId(approvedLeaves.getEmpId());
        leaveApproveForm.setLeaveBalance(approvedLeaves.getLeaveBalance());

        /**add absent dates to leaveApproveForm **/
        if (null != leaveApproveForm.getAbsentDates()) {
            leaveApproveForm.getAbsentDates().addAll((DateUtils.findAbsentDates(approvedLeaves.getFromDate(), approvedLeaves.getToDate(), approvedLeaves.getLeaveType())));
        } else {
            leaveApproveForm.setAbsentDates((DateUtils.findAbsentDates(approvedLeaves.getFromDate(), approvedLeaves.getToDate(), approvedLeaves.getLeaveType())));
        }
    }

    /**
     * @param approvedLeavesRepository to set the approvedLeavesDao
     */
    public void setApprovedLeavesRepository(ApprovedLeavesRepository approvedLeavesRepository) {
        this.approvedLeavesRepository = approvedLeavesRepository;
    }
}
