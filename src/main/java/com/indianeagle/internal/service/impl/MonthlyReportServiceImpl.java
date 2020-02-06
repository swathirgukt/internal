package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.EmployeeSettlementRepository;
import com.indianeagle.internal.dao.repository.MonthlySalaryReportRepository;
import com.indianeagle.internal.dto.EmployeeSettlement;
import com.indianeagle.internal.dto.MonthlySalaryReport;
import com.indianeagle.internal.form.BankSalariesForm;
import com.indianeagle.internal.form.ITForm;
import com.indianeagle.internal.service.MonthlyReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Service to get monthly salary report details
 * @author appala.sambangi
 *
 */
@Service
public class MonthlyReportServiceImpl implements MonthlyReportService {
	@Autowired
	private MonthlySalaryReportRepository monthlySalaryReportRepository;
	@Autowired
    private EmployeeSettlementRepository employeeSettlementRepository;
    private static final String CHEQUE = "CHEQUE";
	
	/**
	 * Method to get monthly salary report
	 * @param salaryDate
	 * @return List
	 */
	public List<MonthlySalaryReport> getMonthlySalaryReport(Date salaryDate) {
		 List<MonthlySalaryReport> monthlySalariesList = monthlySalaryReportRepository.getMonthlySalaryReport(salaryDate);
         monthlySalariesList.addAll(prepareMonthlySettlementEmployeeSalaryReport(salaryDate));
		 MonthlySalaryReport monthlySalaryReports = new MonthlySalaryReport();
		 monthlySalaryReports.setTodefault();
		 monthlySalaryReports.setGrossSalary(BigDecimal.ZERO);
		 for(MonthlySalaryReport monthlySalaryReport : monthlySalariesList){
			 monthlySalaryReports.setPfEmp(monthlySalaryReport.getPfEmp().add(monthlySalaryReports.getPfEmp()));
			 monthlySalaryReports.setPTax(monthlySalaryReport.getPTax().add(monthlySalaryReports.getPTax()));
			 monthlySalaryReports.setEsi(monthlySalaryReport.getEsi().add(monthlySalaryReports.getEsi()));
			 monthlySalaryReports.setIncomeTax(monthlySalaryReport.getIncomeTax().add(monthlySalaryReports.getIncomeTax()));
			 monthlySalaryReports.setSalaryInAdvance(monthlySalaryReport.getSalaryInAdvance().add(monthlySalaryReports.getSalaryInAdvance()));
			 monthlySalaryReports.setMedicalInsurance(monthlySalaryReport.getMedicalInsurance().add(monthlySalaryReports.getMedicalInsurance()));
			 monthlySalaryReports.setLoanAmount(monthlySalaryReport.getLoanAmount().add(monthlySalaryReports.getLoanAmount()));
			 monthlySalaryReports.setNetSalary(monthlySalaryReport.getNetSalary().add(monthlySalaryReports.getNetSalary()));
			 monthlySalaryReports.setTotalDeductions(monthlySalaryReport.getTotalDeductions().add(monthlySalaryReports.getTotalDeductions()));
			 monthlySalaryReports.setOtherAllowance(monthlySalaryReport.getOtherAllowance().add(monthlySalaryReports.getOtherAllowance()));
			 monthlySalaryReports.setGrossSalary(monthlySalaryReport.getGrossSalary().add(monthlySalaryReports.getGrossSalary()));
		 }
		 monthlySalaryReports.setEmployeeId(Integer.toString(monthlySalariesList.size()));
		 monthlySalariesList.add(monthlySalaryReports);
        prepareMonthlySettlementEmployeeSalaryReport(salaryDate);
		return monthlySalariesList;
	}

    /**
     * Method to prepare monthly salary report for settlement employee
     * @param settlementDate the settlement Date
     * @return MonthlySalaryReport list
     */
    private List<MonthlySalaryReport> prepareMonthlySettlementEmployeeSalaryReport(Date settlementDate) {
        List<EmployeeSettlement> employeeSettlementList = employeeSettlementRepository.findMonthlyEmployeeSettlement(settlementDate);
        List<MonthlySalaryReport> monthlySalariesList = new ArrayList<MonthlySalaryReport>();
        for (EmployeeSettlement employeeSettlement : employeeSettlementList) {
            MonthlySalaryReport monthlySalaryReport = new MonthlySalaryReport();
            monthlySalaryReport.setEmployeeId(employeeSettlement.getEmployee().getEmpId());
            monthlySalaryReport.setEmployeeName(employeeSettlement.getEmployee().getFullName());
            monthlySalaryReport.setDepartment(employeeSettlement.getEmployee().getDepartment().getDepartment());
            monthlySalaryReport.setBankAc(CHEQUE);
            monthlySalaryReport.setDoj(employeeSettlement.getEmployee().getJoinDate());
            BeanUtils.copyProperties(employeeSettlement, monthlySalaryReport);
            monthlySalariesList.add(monthlySalaryReport);
        }
        return monthlySalariesList;
    }

	/**
	 * returns bankSalriesReport data
	 * @param salaryDate
	 * @return BankSalariesForm
	 */
	public BankSalariesForm getBankSalariesReport(Date salaryDate) {
		BankSalariesForm bankSalariesForm =new BankSalariesForm() ;
		List<BankSalariesForm.BankSalariesInfo> bankSalariesInfoList = new ArrayList<BankSalariesForm.BankSalariesInfo>();
		BankSalariesForm.BankSalariesInfo bankSalariesInfo;
		
		List<Object[]> objArrayList = monthlySalaryReportRepository.getBankSalariesReport(salaryDate);
		Object[] objArray;
		BigDecimal totalSal = BigDecimal.ZERO;
		for (int i = 0; i < objArrayList.size() ; i++) {
			bankSalariesInfo = bankSalariesForm.new BankSalariesInfo();
			objArray = objArrayList.get(i);
				bankSalariesInfo.setSlNo(i+1);				
				bankSalariesInfo.setEmpFullName((String)objArray[0]);
				bankSalariesInfo.setBankAcNo((String)objArray[1]);
				bankSalariesInfo.setSalary((BigDecimal)objArray[2]);
			bankSalariesInfoList.add(bankSalariesInfo) ;
			totalSal = totalSal.add(bankSalariesInfo.getSalary());
		}
		bankSalariesForm.setSalaryDate(salaryDate);
		bankSalariesForm.setBankSalariesInfoList(bankSalariesInfoList) ;
		bankSalariesForm.setTotalSal(totalSal) ;
		return bankSalariesForm;
	}
	/**
	 * Method to get ITReport for all employees
	 * @param range
	 */
	public List <ITForm>  getITReport(Date range){
		List<MonthlySalaryReport> empList = monthlySalaryReportRepository.getITReport(range);
		if(empList.isEmpty())
			return null;
		Map<String,List<BigDecimal>> itDetails = new LinkedHashMap<String, List<BigDecimal>>();
		for (MonthlySalaryReport employee : empList) {
			/** calculation IT of employee for monthly wise */
			if(itDetails.containsKey(employee.getEmployeeId())){
				List<BigDecimal>monthlyIT =  itDetails.get(employee.getEmployeeId());
				monthlyIT.add(employee.getIncomeTax());
				itDetails.put(employee.getEmployeeId(), monthlyIT);
			}else{
				List<BigDecimal> monthlyIT = new ArrayList<BigDecimal>();
				monthlyIT.add(employee.getIncomeTax());
				itDetails.put(employee.getEmployeeId(),monthlyIT);
			}
			
		}
	    
	    List<String> empIds =new ArrayList<String>(itDetails.keySet());
	    List<ITForm> itFormsList = new ArrayList<ITForm>();
	    /** create ITForm object  */
		for (String empId : empIds) {
			ITForm itForm =  new ITForm();
			itForm.setEmpNo(empId);
			itForm.setMonthlyIT(itDetails.get(empId));
			if( itForm.getTotal().compareTo(BigDecimal.ZERO) == 1){
				itFormsList.add(itForm);
			}
		}
		return itFormsList;
		
	}

	/**
	 * @param monthlySalaryReportDAO the monthlySalaryReportDAO to set
	 */
	public void setMonthlySalaryReportDAO(MonthlySalaryReportRepository monthlySalaryReportDAO) {
		this.monthlySalaryReportRepository = monthlySalaryReportDAO;
	}

    /**
     * @param employeeSettlementDAO to set
     */
    public void setEmployeeSettlementDAO(EmployeeSettlementRepository employeeSettlementDAO) {
        this.employeeSettlementRepository = employeeSettlementDAO;
    }
}
