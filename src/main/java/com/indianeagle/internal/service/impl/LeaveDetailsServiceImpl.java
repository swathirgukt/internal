package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.ApprovedLeavesRepository;
import com.indianeagle.internal.dao.repository.EmployeeRepository;
import com.indianeagle.internal.dao.repository.LeaveDetailsRepository;
import com.indianeagle.internal.dao.repository.LeavesRepository;
import com.indianeagle.internal.dto.ApprovedLeaves;
import com.indianeagle.internal.dto.LeaveDetails;
import com.indianeagle.internal.dto.Leaves;
import com.indianeagle.internal.form.LeaveApproveForm;
import com.indianeagle.internal.service.LeaveDetailsService;
import com.indianeagle.internal.util.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LeaveDetailsServiceImpl implements LeaveDetailsService {
@Autowired
    private LeaveDetailsRepository leaveDetailsRepository;
@Autowired
    private EmployeeRepository employeeRepository;
@Autowired
    private ApprovedLeavesRepository approvedLeavesRepository;
@Autowired
    private LeavesRepository leavesRepository;

    public void saveOrUpdate(LeaveDetails leaveDetails) {
        leaveDetailsRepository.save(leaveDetails);
    }

    @SuppressWarnings("unchecked")
    public List<LeaveDetails> searchLeaveDetails(LeaveDetails leaveDetails) {

        return leaveDetailsRepository.findByEmpIdLike(leaveDetails.getEmpId());
    }

    public LeaveDetails getLeaveDetailsById(long id) {
        return leaveDetailsRepository.findById(id).get();
    }

    /**
     * Method to find the leaves by employee id
     *
     * @param employeeId the employeeId
     * @return leaves
     */
    public Leaves findLeaveByEmployeeId(String employeeId) {

        if (leavesRepository.findLeavesByEmployeeId(employeeId).size() > 0) {
            return leavesRepository.findLeavesByEmployeeId(employeeId).get(0);
        } else {
            return new Leaves();
        }
    }

    /**
     * Method to find all working employeeIds list
     *
     * @return employeeIds list
     */
    public List<String> findAllEmployeeIds() {

        return employeeRepository.findAllEmpIds();
    }


    /**
     * Method to save the approved leave
     *
     * @param leaveApproveForm, the leaveApproveForm
     */
    public void approveLeave(LeaveApproveForm leaveApproveForm) {

        Leaves leaves = findLeaveByEmployeeId(leaveApproveForm.getEmpId());
        Double totalRemainingLeaves = leaves.getCasualLeaves() + leaves.getCompensatoryLeaves() + leaves.getSickLeaves() + leaves.getPreviousYearLeaves();
        createAndSaveLeaves(leaveApproveForm, leaves);

        int monthsDifference = 0;
        if (leaveApproveForm.getToDate() != null) {
            monthsDifference = DateUtils.findMonthsDifference(leaveApproveForm.getFromDate(), leaveApproveForm.getToDate());
        }
        if (monthsDifference > 0) {

            splitAndSaveApprovedLeaves(leaveApproveForm, monthsDifference, totalRemainingLeaves);
        } else {
            ApprovedLeaves approvedLeave = new ApprovedLeaves();
            BeanUtils.copyProperties(leaveApproveForm, approvedLeave);
            approvedLeave.setApprovedDate(new Date());
            approvedLeave.setLeaveBalance(totalRemainingLeaves - leaveApproveForm.getTotalNumberOfAbsentDays()+leaveApproveForm.getLop());
            approvedLeavesRepository.save(approvedLeave);
        }
    }

    /**
     * Method to create and persist yhe leaves
     *
     * @param leaveApproveForm the leaveApproveForm
     * @param leaves the leaves for the employee
     */
    private void createAndSaveLeaves(LeaveApproveForm leaveApproveForm, Leaves leaves) {

        double casualLeaves = leaves.getCasualLeaves();
        if (leaveApproveForm.getCasualLeave() > casualLeaves) {
            leaves.setCasualLeaves(0d);
            leaves.setPreviousYearLeaves(leaves.getPreviousYearLeaves() - (leaveApproveForm.getCasualLeave() - casualLeaves));
        } else {
            leaves.setCasualLeaves(casualLeaves - leaveApproveForm.getCasualLeave());
        }
        leaves.setSickLeaves(leaveApproveForm.getRemainingSL() - leaveApproveForm.getSickLeave());
        leaves.setCompensatoryLeaves(leaveApproveForm.getRemainingCompOff() - leaveApproveForm.getCompensatoryLeave());
        leavesRepository.save(leaves);
    }

    /**
     * Method to save leave approve data by splitting dates by monthly
     * @param leaveApproveForm the leaveApproveForm
     * @param monthsDifference the no of months difference between dates
     * @param totalRemainingLeaves the total remaining leave balance
     */
    private void splitAndSaveApprovedLeaves(LeaveApproveForm leaveApproveForm, int monthsDifference, Double totalRemainingLeaves) {

        Calendar fromDate = Calendar.getInstance();
        fromDate.setTime(leaveApproveForm.getFromDate());
        Calendar toDate = Calendar.getInstance();
        toDate.setTime(leaveApproveForm.getToDate());

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(fromDate.get(Calendar.YEAR), fromDate.get(Calendar.MONTH), fromDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        int noOfAbsentDays = DateUtils.findAbsentDates(fromDate.getTime(), endCalendar.getTime(), leaveApproveForm.getLeaveType()).size();
        totalRemainingLeaves -= noOfAbsentDays;
        saveApproveLeave(fromDate.getTime(), endCalendar.getTime(), noOfAbsentDays, leaveApproveForm, totalRemainingLeaves);

        int index = 1;
        for (; index <= monthsDifference; index++) {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.set(fromDate.get(Calendar.YEAR), fromDate.get(Calendar.MONTH) + index, 1);
            if (index < monthsDifference - 1) {
                endCalendar.set(fromDate.get(Calendar.YEAR), fromDate.get(Calendar.MONTH) + index, 1);
                endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                endCalendar = toDate;
            }
            noOfAbsentDays = DateUtils.findAbsentDates(startCalendar.getTime(), endCalendar.getTime(), leaveApproveForm.getLeaveType()).size();
            totalRemainingLeaves -= noOfAbsentDays;
            saveApproveLeave(startCalendar.getTime(), endCalendar.getTime(), noOfAbsentDays, leaveApproveForm, totalRemainingLeaves);

        }
    }

    /**
     * Method to save approve leave data
     * @param fromDate the from leave date
     * @param toDate the to leave date
     * @param noOfAbsentDays for a month
     * @param leaveApproveForm the leave approve form
     * @param totalRemainingLeaves the total remaining days
     */
    private void saveApproveLeave(Date fromDate, Date toDate, int noOfAbsentDays, LeaveApproveForm leaveApproveForm, Double totalRemainingLeaves) {

        ApprovedLeaves approvedLeaves = new ApprovedLeaves();
        approvedLeaves.setApprovedDate(new Date());
        approvedLeaves.setEmpId(leaveApproveForm.getEmpId());
        approvedLeaves.setFromDate(fromDate);
        approvedLeaves.setToDate(toDate);
        approvedLeaves.setTotalNumberOfAbsentDays(noOfAbsentDays);
        approvedLeaves.setLeaveType(leaveApproveForm.getLeaveType());
        approvedLeaves.setNote(leaveApproveForm.getNote());
        if (leaveApproveForm.getCasualLeave() >= noOfAbsentDays) {
            leaveApproveForm.setCasualLeave(leaveApproveForm.getCasualLeave() - noOfAbsentDays);
            approvedLeaves.setCasualLeave(noOfAbsentDays);
        } else {

            approvedLeaves.setCasualLeave(leaveApproveForm.getCasualLeave());
            leaveApproveForm.setCasualLeave(0);
            if (leaveApproveForm.getSickLeave() >= (noOfAbsentDays - approvedLeaves.getCasualLeave())) {
                double sickLeaves = (noOfAbsentDays - approvedLeaves.getCasualLeave());
                approvedLeaves.setSickLeave(sickLeaves);
                leaveApproveForm.setSickLeave(leaveApproveForm.getSickLeave() - sickLeaves);
            } else {

                approvedLeaves.setSickLeave(leaveApproveForm.getSickLeave());
                leaveApproveForm.setSickLeave(0);
                if (leaveApproveForm.getCompensatoryLeave() >= (noOfAbsentDays - approvedLeaves.getCasualLeave() - approvedLeaves.getSickLeave())) {

                    double compOffs = (noOfAbsentDays - approvedLeaves.getCasualLeave() - approvedLeaves.getSickLeave());
                    leaveApproveForm.setCompensatoryLeave(compOffs);
                    approvedLeaves.setCompensatoryLeave(compOffs);
                } else {

                    approvedLeaves.setCompensatoryLeave(leaveApproveForm.getCompensatoryLeave());
                    leaveApproveForm.setCompensatoryLeave(0);
                    double lops = (noOfAbsentDays - approvedLeaves.getCasualLeave() - approvedLeaves.getSickLeave() - approvedLeaves.getCompensatoryLeave());
                    leaveApproveForm.setLop(lops);
                    approvedLeaves.setLop(lops);
                }
            }
        }
        approvedLeaves.setLeaveBalance(totalRemainingLeaves + approvedLeaves.getLop());
        approvedLeavesRepository.save(approvedLeaves);
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(LeaveDetailsRepository dao) {
        this.leaveDetailsRepository = dao;
    }

    /**
     * @param employeeDAO to set the employeeDao
     */
    public void setEmployeeDAO(EmployeeRepository employeeDAO) {
        this.employeeRepository = employeeDAO;
    }

    /**
     * @param approvedLeavesDao, to set approvedLeavesDao
     */
    public void setApprovedLeavesDao(ApprovedLeavesRepository approvedLeavesDao) {
        this.approvedLeavesRepository = approvedLeavesDao;
    }

    /**
     * @param leavesDAO, to set the leavesDAO
     */
    public void setLeavesDAO(LeavesRepository leavesDAO) {
        this.leavesRepository = leavesDAO;
    }
}
