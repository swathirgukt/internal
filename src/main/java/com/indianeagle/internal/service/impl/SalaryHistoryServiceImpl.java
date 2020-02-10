package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.SalaryHistoryRepository;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.enums.TemplateNames;
import com.indianeagle.internal.mail.MailingEngine;
import com.indianeagle.internal.service.SalaryHistoryService;
import com.indianeagle.internal.util.PaySlipPdfUtils;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.FontFactoryImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author SVK
 */
@Service
public class SalaryHistoryServiceImpl implements SalaryHistoryService, MessageSourceAware {
	
	private static final Logger log = LogManager.getLogger(SalaryHistoryServiceImpl.class);
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private SalaryHistoryRepository salaryHistoryRepository;
	@Autowired
	private MailingEngine mailingEngine;
	@Autowired
    private SalaryHistory salaryHistory;

    private List<Employee> employees;
	@Autowired
    private TemplateEngine templateEngine;

	private FontFactoryImp factoryImp;

	public List<SalaryHistory> searchSalaryHistory(String empId, Date periodDate) {
		log.info("search SalaryHistory for empId::"+empId+" salaryDate::"+periodDate);
		return salaryHistoryRepository.findByEmpIdAndSalaryDateLessThanEqual(empId, periodDate);
	}

    /**
     * Method to find the salary history for an employee
     * @param employeeId the employee Id
     * @return salaryHistory list
     */
    public List<SalaryHistory> searchSalaryHistoryByEmployeeId(String employeeId) {
		log.info("search SalaryHistory for empId::"+employeeId);
		return salaryHistoryRepository.findByEmpId(employeeId);
	}

    /**
     * Method to find the salary historty based on emplyee id and given date
     * @param empId
     * @param salaryDate
     * @return
     */
	public List<SalaryHistory> findSalaryHistoryByEmpId(String empId, Date salaryDate) {
		return salaryHistoryRepository.findSalaryHistoryByEmpId(empId, salaryDate);
	}

	public List<SalaryHistory> findSalaryHistoryByEmpId(String empId, Date salaryDate, Date salaryEndDate) {
		return salaryHistoryRepository.findSalaryHistoryByEmpId(empId, salaryDate,salaryEndDate);
	}

	public List<SalaryHistory> salaryReport(Date salaryDate) {
		log.info("Salary Report for salaryDate ::"+salaryDate);
		return salaryHistoryRepository.salaryReport(salaryDate);
	}

	public List<SalaryHistory> salaryReport(Date salaryDate, Date salaryEndDate) {
		log.info("Salary Report for salaryDate :" + salaryDate);
		log.info("salary Report for salaryEndDate:" + salaryEndDate);
		return this.salaryHistoryRepository.salaryReport(salaryDate, salaryEndDate);
	}

	public SalaryHistory sendPaySlip(Long id,String contextPath) {
		salaryHistory = salaryHistoryRepository.findById(id).get();
		employees = salaryHistoryRepository.findEmployeeByEmpId(salaryHistory.getEmpId());
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		//PDFUtil.generatePdf(employees.get(0), salaryHistory, arrayOutputStream,contextPath,messageSource);
        /*YP-4: added vm file for paySlip*/
        PaySlipPdfUtils.generatePaySlipPdf(templateEngine, arrayOutputStream, TemplateNames.MAIL_TEMPLATE_PATH.getPath() + TemplateNames.IE_Payslip.name() +".html", contextPath, employees.get(0), salaryHistory);
		mailingEngine.sendMail(employees.get(0), salaryHistory ,new ByteArrayResource(arrayOutputStream.toByteArray()),true);
		return salaryHistory;
	}
	
	public InputStream printPaySlip(Long id , String contextPath) {
		salaryHistory = salaryHistoryRepository.findById(id).get();
		employees = salaryHistoryRepository.findEmployeeByEmpId(salaryHistory.getEmpId());
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        /*PDFUtil.generatePdf(employees.get(0), salaryHistory, arrayOutputStream,contextPath,messageSource);*/
        /*YP-4: added vm file for paySlip*/
        PaySlipPdfUtils.generatePaySlipPdf(templateEngine, arrayOutputStream, TemplateNames.MAIL_TEMPLATE_PATH.getPath() + TemplateNames.IE_Payslip.name() + ".html", contextPath, employees.get(0), salaryHistory);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
		return byteArrayInputStream;
	}

    @PostConstruct
    public void registerFonts() {
    	/* iText register fonts from Operating System default font folder */
    	factoryImp = new FontFactoryImp();
		factoryImp.registerDirectories();
		FontFactory.setFontImp(factoryImp);
    }

	/**
	 * @param salaryHistoryDAO the salaryHistoryDAO to set
	 */
	public void setSalaryHistoryDAO(SalaryHistoryRepository salaryHistoryDAO) {
		this.salaryHistoryRepository = salaryHistoryDAO;
	}
	
	/**
	 * @param mailingEngine the mailingEngine to set
	 */
	public void setMailingEngine(MailingEngine mailingEngine) {
		this.mailingEngine = mailingEngine;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

    /**
	 * @param templateEngine the velocityEngine to set
	 */
	public void setVelocityEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
}
