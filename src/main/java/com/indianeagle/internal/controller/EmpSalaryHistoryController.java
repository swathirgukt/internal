package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.SalaryHistoryForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.form.vo.SalaryHistoryVO;
import com.indianeagle.internal.service.ApplicationSession;
import com.indianeagle.internal.service.SalaryHistoryService;
import com.indianeagle.internal.service.SalaryService;
import com.indianeagle.internal.util.SimpleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Satya.Neelam
 */

@Controller
@RequestMapping("salaryHistory")
public class EmpSalaryHistoryController {

    @Autowired
    private SalaryService salaryService;
    private SalaryHistoryForm salaryHistoryForm;
    @Autowired
    private SalaryHistoryService salHistoryService;
    @Autowired
    private ApplicationSession applicationSession;
    private List<SalaryHistory> empSalaryHistory;
    private InputStream inputStream;
    private Map session;
    private Long id;
    HttpServletRequest servletRequest;
    private static Logger LOGGER = LogManager.getLogger(EmpSalaryHistoryController.class);

    @PostConstruct
    public void loadData() {
        salaryHistoryForm = new SalaryHistoryForm();
        List<EmployeeVO> employeeVOList = new ArrayList<>();
       /* if (applicationSession.isAdminRoleUser()) {

        } else {
            String loginUser = applicationSession.loginUserName();
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(salaryService.loadEmployee(loginUser), employeeVO);
            employeeVOList.add(employeeVO);
        }*/

        for (Employee employee : salaryService.loadAllEmployees()) {
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(employee, employeeVO);
            employeeVOList.add(employeeVO);
        }
        salaryHistoryForm.setEmployeeVOList(employeeVOList);
    }

    @ModelAttribute
    public void prepare(Model model){
        model.addAttribute("salaryHistoryForm", salaryHistoryForm);
    }

    @GetMapping
    public String salaryHistory() {
        return "html/salaryHistory";
    }

    @GetMapping("/search")
    public String getSalaryHistory(ModelMap modelMap, @RequestParam String employeeId) {
        List<SalaryHistory> salaryHistories = salHistoryService.searchSalaryHistoryByEmployeeId(employeeId);
        List<SalaryHistoryVO> salaryHistoryVOS = new ArrayList<>();
        for (SalaryHistory salaryHistory : salaryHistories){
            SalaryHistoryVO salaryHistoryVO = new SalaryHistoryVO();
            BeanUtils.copyProperties(salaryHistory, salaryHistoryVO);
            salaryHistoryVOS.add(salaryHistoryVO);
        }
        modelMap.addAttribute("salaryHistories", salaryHistoryVOS);
        return "html/fragment/employeeSalaryHistoryResult";
    }


    public boolean validateSalaryHistory() {
        if ("".equals(salaryHistoryForm.getEmployeeId()))
            //addActionError("Please select Employee ID");
            return false;
        if ("getActionErrors".isEmpty())
            return false;
        else
            return true;

    }


    @GetMapping("/mail")
    public String sendPaySlip() {
        LOGGER.warn("######### IN sendPaySlip()");
        salHistoryService.sendPaySlip(id, SimpleUtils.getContextPath(servletRequest));
        //addActionMessage("Mail send SuccessFully");
        empSalaryHistory = (List<SalaryHistory>) session.get("History");
        LOGGER.warn("######### IN sendPaySlip() DONE..");
        return "SUCCESS";
    }


    @GetMapping("/print")
    public String printPaySlip() {
        inputStream = salHistoryService.printPaySlip(id, SimpleUtils.getContextPath(servletRequest));
        return "SUCCESS";

    }


    /**
     * @param salaryService the salaryService to set
     */
    public void setSalaryService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }


    /**
     * @param salHistoryService the salHistoryService to set
     */
    public void setSalHistoryService(SalaryHistoryService salHistoryService) {
        this.salHistoryService = salHistoryService;
    }

    /**
     * @return the salHistory
     */
    public SalaryHistoryForm getSalaryHistoryForm() {
        return salaryHistoryForm;
    }

    /**
     * @param salaryHistoryForm the salHistory to set
     */
    public void setSalaryHistoryForm(SalaryHistoryForm salaryHistoryForm) {
        this.salaryHistoryForm = salaryHistoryForm;
    }

    /**
     * @return the empSalaryHistory
     */
    public List<SalaryHistory> getEmpSalaryHistory() {
        return empSalaryHistory;
    }

    /**
     * @param empSalaryHistory the empSalaryHistory to set
     */
    public void setEmpSalaryHistory(List<SalaryHistory> empSalaryHistory) {
        this.empSalaryHistory = empSalaryHistory;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the session
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Map session) {
        this.session = session;
    }

    /**
     * @return the inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * @param inputStream the inputStream to set
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return the applicationSession
     */
    public ApplicationSession getApplicationSession() {
        return applicationSession;
    }

    /**
     * @param applicationSession the applicationSession to set
     */
    public void setApplicationSession(ApplicationSession applicationSession) {
        this.applicationSession = applicationSession;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }


}
