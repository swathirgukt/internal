package com.indianeagle.internal.service.impl;


import com.indianeagle.internal.dao.repository.*;
import com.indianeagle.internal.dto.*;
import com.indianeagle.internal.enums.TemplateNames;
import com.indianeagle.internal.mail.MailingEngine;
import com.indianeagle.internal.service.DepartmentService;
import com.indianeagle.internal.util.Form16PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
@Autowired
	private DepartmentRepository departmentRepository;
@Autowired
	private FinancialYearRepository financialYearRepository;
@Autowired
	private EmployeeRepository employeeRepository;
@Autowired
	private EmployeeFinancialYearRepository employeeFinancialYearRepository;
@Autowired
	private SalaryHistoryRepository salaryHistoryRepository;
@Autowired
	private EmployeeIncomeTaxRepository employeeIncomeTaxRepository;
@Autowired
	private TemplateEngine velocityEngine;
@Autowired
	private MailingEngine mailingEngine;
	@Autowired
	private GeneratedForm16Repository generatedForm16Repository;

	public void saveOrUpdate(Department department){
		departmentRepository.save(department);
	}

	@SuppressWarnings("unchecked")
	public List<Department> findDepartments(Department department){
		return	departmentRepository.findByDepartmentStartingWith(department.getDepartment());
	}

	@SuppressWarnings("unchecked")
	public List<Department> loadAllDepartments(){
		return	departmentRepository.findAll();
	}
	
	public Department findById(long department){
		return	departmentRepository.findById(department).get();
	}

	public void saveOrUpdateFinancialYear(FinancialYear financialYear) {
		financialYearRepository.save(financialYear);
	}

	public Employee findEmployeeByEmployeeId(String employeeId) {
		return employeeRepository.findByEmpId(employeeId);
	}

	public List<FinancialYear> findFinancialYearSectionsByFinancialYear(String fromMonth, String fromYear, String toMonth, String toYear) {
		return financialYearRepository.findFinancialYearSectionsByFinancialYear(fromMonth, fromYear, toMonth, toYear);
	}

	public List<FinancialYear> findAllFinancialYearSections() {
		return financialYearRepository.findAllFinancialYearSections();
	}

	public void saveOrUpdateEmployeeFinancialYear(EmployeeFinancialYear employeeFinancialYear) {
		employeeFinancialYearRepository.save(employeeFinancialYear);
	}

	public List<EmployeeFinancialYear> findEmployeeFinancialYearByEmpId(String empId) {
		return employeeFinancialYearRepository.findEmployeeFinancialYearByEmpId(empId);
	}

	public List<SalaryHistory> findSalaryHistoriesWithInFinancialYear(String empId, Date fromDate, Date toDate) {
		return salaryHistoryRepository.findByEmpIdAndSalaryEndDateBetween(empId, fromDate, toDate);
	}

	public List<EmployeeFinancialYear> findEmployeeFinancialYearWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear) {
		return employeeFinancialYearRepository.findEmployeeFinancialYearWithEmpIdAndMonthAndYear(empId, fromMonth, fromYear, toMonth, toYear);
	}

	public InputStream saveForm16PDF(String empId, FinancialYear financialYear, List<SalaryHistory> salaryHistories, EmployeeIncomeTax employeeIncomeTax, EmployeeFinancialYear employeeFinancialYear, String contextPath) {
		Employee employee = employeeRepository.findByEmpId(empId);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		Form16PdfUtils.generateForm16Pdf(velocityEngine, arrayOutputStream, TemplateNames.MAIL_TEMPLATE_PATH.getPath() + TemplateNames.form16Pdf.name() + ".html", contextPath, employee, salaryHistories, employeeIncomeTax, employeeFinancialYear, financialYear);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
		return byteArrayInputStream;
	}

	public void saveOrUpdateEmployeeIncomeTax(EmployeeIncomeTax employeeIncomeTax) {
		employeeIncomeTaxRepository.save(employeeIncomeTax);
	}

	public List<EmployeeIncomeTax> findEmployeeIncomeTaxWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear) {
		return employeeIncomeTaxRepository.findEmployeeIncomeTaxWithEmpIdAndFinancialYear(empId, fromMonth, fromYear, toMonth, toYear);
	}

	public void saveOrUpdateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public List<Employee> findEmployeeByName(String name) {
		return employeeRepository.findEmployeeByName(name);
	}

	public List<EmployeeIncomeTax> findEmployeeIncomeTaxWithEmpId(String empId) {
		return employeeIncomeTaxRepository.findByEmpId(empId);
	}

	public void sendMailEmployeeIncomeTax(String empId, FinancialYear financialYear, List<SalaryHistory> salaryHistories, EmployeeIncomeTax employeeIncomeTax, EmployeeFinancialYear employeeFinancialYear, String contextPath) {
		Employee employee = employeeRepository.findByEmpId(empId);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		Form16PdfUtils.generateForm16Pdf(velocityEngine, arrayOutputStream, TemplateNames.MAIL_TEMPLATE_PATH.getPath() + TemplateNames.form16Pdf.name() + ".html", contextPath, employee, salaryHistories, employeeIncomeTax, employeeFinancialYear, financialYear);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
		mailingEngine.sendEmployeeIncomeTax(employee, financialYear, (InputStreamSource)new ByteArrayResource(arrayOutputStream.toByteArray()));
	}

	public void saveOrUpdateGeneratedForm16(GeneratedForm16 generatedForm16) {
		generatedForm16Repository.save(generatedForm16);
	}

	public GeneratedForm16 findPdfByEmpIdAndFinancialYear(String emId, FinancialYear financialYear) {
		return generatedForm16Repository.findPdfByEmpIdAndFinancialYear(emId, financialYear.getFromMonth(), financialYear.getFromYear(), financialYear.getToMonth(), financialYear.getToYear());
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(DepartmentRepository dao) {
		this.departmentRepository = dao;
	}

	public void setEmployeeDAO(EmployeeRepository employeeDAO) {
		this.employeeRepository = employeeDAO;
	}

	public void setFinancialYearDao(FinancialYearRepository financialYearDao) {
		this.financialYearRepository = financialYearDao;
	}

	public void setEmployeeFinancialYearDAO(EmployeeFinancialYearRepository employeeFinancialYearDAO) {
		this.employeeFinancialYearRepository = employeeFinancialYearDAO;
	}

	public void setSalaryHistoryDAO(SalaryHistoryRepository salaryHistoryDAO) {
		this.salaryHistoryRepository = salaryHistoryDAO;
	}

	public EmployeeIncomeTaxRepository getEmployeeIncomeTaxDAO() {
		return employeeIncomeTaxRepository;
	}

	public void setEmployeeIncomeTaxDAO(EmployeeIncomeTaxRepository employeeIncomeTaxDAO) {
		this.employeeIncomeTaxRepository = employeeIncomeTaxDAO;
	}

	public TemplateEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(TemplateEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public MailingEngine getMailingEngine() {
		return mailingEngine;
	}

	public void setMailingEngine(MailingEngine mailingEngine) {
		this.mailingEngine = mailingEngine;
	}

	public GeneratedForm16Repository getGeneratedForm16DAO() {
		return generatedForm16Repository;
	}

	public void setGeneratedForm16DAO(GeneratedForm16Repository generatedForm16DAO) {
		this.generatedForm16Repository = generatedForm16DAO;
	}
}
