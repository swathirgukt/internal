package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.EmployeeRepository;
import com.indianeagle.internal.dao.repository.EmployeeSettlementRepository;
import com.indianeagle.internal.dao.repository.SalaryHistoryRepository;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.EmployeeSettlement;
import com.indianeagle.internal.dto.Nominee;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.form.FormESIC;
import com.indianeagle.internal.form.FormsResultData;
import com.indianeagle.internal.form.PFResultData;
import com.indianeagle.internal.form.PtForm;
import com.indianeagle.internal.service.FormsService;
import com.indianeagle.internal.util.CalculationRules;
import com.indianeagle.internal.util.DateUtils;
import com.indianeagle.internal.util.MessageKeys;
import com.indianeagle.internal.util.SimpleUtils;
import com.indianeagle.internal.enums.Relation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Service class implementation for various forms
 * @author kiran.paluvadi
 */
@Service
public class FormsServiceImpl implements FormsService, MessageSourceAware {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private SalaryHistoryRepository salaryHistoryRepository;

	private FormsResultData formsResultData;
	@Autowired
    private EmployeeSettlementRepository employeeSettlementRepository;
	@Autowired
    private EmployeeRepository employeeRepository;

    private static final String CESSATION_CODE = "C";
    private static final SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat("dd/MM/yyyy");
    private static final String SPACE = " ";
    private static final String SLASH = "/";

    /**
     * prepares FormV's form result
     * @param date
     * @return prepares FormsResultData
     */
    public PtForm getMonthlyRtReport(Date date) {
        PtForm ptForm = new PtForm();
        PtForm.PtCalculator ptCalculator;
        List<PtForm.PtCalculator> ptCalculatorList = new ArrayList<PtForm.PtCalculator>();
        List<Integer> salaryRangeList = new ArrayList<Integer>(CalculationRules.PT_RANGE.keySet());
        List<Long> empsCountList = salaryHistoryRepository.getMonthlyRtReport(date);
        List<Long> settlementEmployeesByPTRange = employeeSettlementRepository.findMonthlySettlementReport(date);
        if (!isPtReportExist(empsCountList)) {
            return null;
        }
        Long totalAmountPaid = 0L;
        ptForm.setReturnPayMonth(date);
        ptForm.setEmployerName(messageSource.getMessage(MessageKeys.NAME_EMPLOYER, null, null));
        ptForm.setOrgAddress(messageSource.getMessage(MessageKeys.ADDRESS, null, null));
        ptForm.setRegiCertificateNo(messageSource.getMessage(MessageKeys.REGISTRATION_CERTIFICATE_NO, null, null));
        ptForm.setAuthorizedPerson(messageSource.getMessage(MessageKeys.AUTHORIZED_PERSON, null, null));
        ptForm.setPlace(messageSource.getMessage(MessageKeys.PLACE, null, null));

        for (int i = 0; i < salaryRangeList.size(); i++) {
            ptCalculator = ptForm.new PtCalculator();
            String romanNo = SimpleUtils.binaryToRoman((i + 1));
            if (i == 0) {
                ptCalculator.setSalaryRange(" " + romanNo + ") Upto Rs. " + salaryRangeList.get(i + 1) + "/-");
            } else if (i == salaryRangeList.size() - 1) {
                ptCalculator.setSalaryRange(" " + romanNo + ") Range above Rs. " + (salaryRangeList.get(i) + 1) + "/-");
            } else {
                ptCalculator.setSalaryRange(" " + romanNo + ") Range from Rs. " + (salaryRangeList.get(i) + 1) + "/- to " + salaryRangeList.get(i + 1) + "/-");
            }
            ptCalculator.setNoofEmps((empsCountList.get(i) + settlementEmployeesByPTRange.get(i)));
            ptCalculator.setTaxPerMonth(CalculationRules.PT_RANGE.get(salaryRangeList.get(i)));
            totalAmountPaid += ptCalculator.getDeductedTax();

			 ptCalculatorList.add(ptCalculator);
        }

        ptForm.setTotalAmountPaid(totalAmountPaid);
        ptForm.setPtCalculatorList(ptCalculatorList);
        return ptForm;
    }
	private boolean isPtReportExist(List<Long> empsCountList){
		boolean exist = false;
			for (Long l : empsCountList) {
				if(l!=0) 
					exist = true;
			}
		return exist ;
	}
	/**
	 * method to generate Monthly ESIC report
	 * @param date
	 * @return formESIC
	 */
	public FormESIC getMonthlyESIReport(Date date){

		FormESIC formESIC = new FormESIC();
		String[] monthNames = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};
		List<Object> list = salaryHistoryRepository.getMonthlyESIReport(date);
        List<Object> employeeSettlementList = employeeSettlementRepository.getMonthlyESIReport(date);
		Object[] object =  (Object[]) list.get(0);
		Object[] employeeSettlementObject =  (Object[]) employeeSettlementList.get(0);
		Calendar calInstance = Calendar.getInstance();
		calInstance.setTime(date);
		String forMonth = monthNames[calInstance.get(Calendar.MONTH)]+"-"+calInstance.get(Calendar.YEAR);
		if(object[0].toString().equals("0") && employeeSettlementObject[0].toString().equals("0")){
			return formESIC = null;
		}else{
			formESIC.setForMonth(forMonth);
            int totalNoOfEmployee = !object[0].toString().equals("0") ? Integer.parseInt(object[0].toString()) : 0;
            BigDecimal empContribution = null != object[1]? new BigDecimal(object[1].toString()) : new BigDecimal(BigInteger.ZERO);
            BigDecimal totalWages = null != object[2] ? new BigDecimal(object[2].toString()) : new BigDecimal(BigInteger.ZERO);
            totalWages = null != employeeSettlementObject[2] ? totalWages.add(new BigDecimal(employeeSettlementObject[2].toString())).setScale(0, BigDecimal.ROUND_UP) : totalWages.setScale(0, BigDecimal.ROUND_UP);
            formESIC.setNoOfEmployees(!employeeSettlementObject[0].toString().equals("0") ? totalNoOfEmployee + Integer.parseInt(employeeSettlementObject[0].toString()) : totalNoOfEmployee);
            formESIC.setEmpContribution(null != employeeSettlementObject[1] ? empContribution.add(new BigDecimal(employeeSettlementObject[1].toString())).setScale(0, BigDecimal.ROUND_UP) : empContribution.setScale(0, BigDecimal.ROUND_UP));
			formESIC.setTotalWages(totalWages);
            formESIC.setEmployersContribution((totalWages.multiply(CalculationRules.PER_ORG_ESI).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_UP));
			formESIC.setTotalAmount(formESIC.getEmpContribution().add(formESIC.getEmployersContribution()));
			formESIC.setRupeesInWords(SimpleUtils.numberToWord(formESIC.getTotalAmount()));
			formESIC.setAddress(messageSource.getMessage(MessageKeys.NAME_EMPLOYER,null,null)+", "+messageSource.getMessage(MessageKeys.ADDRESS,null,null)+", "+messageSource.getMessage(MessageKeys.PHONE_NO,null,null));
			formESIC.setEmployerCode(messageSource.getMessage(MessageKeys.ESI_CODE_NO,null,null));
			return formESIC;
		}
	}

    /**
     * Method to prepare monthly pf report
     * @param date the pf report date
     * @return inputStream containing the pf report
     */
    public InputStream getMonthlyPfReport(Date date) {
        StringBuffer pfReportData = new StringBuffer();
        preparePfResultDataFromSalaryHistory(date, pfReportData);
        preparePfResultDataFromSettlementEmployees(date, pfReportData);
        if (StringUtils.isNotEmpty(pfReportData.toString())) {
            return new ByteArrayInputStream(pfReportData.toString().getBytes());
        }
        return null;
    }

    /**
     * Method to prepare PfResultData from SalaryHistory
     * @param date the pf Report date
     * @param pfReportData to add pfReport
     */
    private void preparePfResultDataFromSalaryHistory(Date date, StringBuffer pfReportData) {
        List<SalaryHistory> salaryHistoryList = salaryHistoryRepository.salaryReport(date);
        List<Employee> employeeList = employeeRepository.findAll();
        for (SalaryHistory salaryHistory : salaryHistoryList) {
            PFResultData pfResultData = new PFResultData();
            Employee pfEmployee = null;
            for (Employee employee : employeeList) {
                if (employee.getEmpId().equalsIgnoreCase(salaryHistory.getEmpId())) {
                    pfEmployee = employee;
                    pfResultData.setMemberId(StringUtils.substringAfterLast(employee.getPfNo(), SLASH));
                    pfResultData.setName(getEmployeeFullName(employee));
                    /* To update the nominee details for new employee */
                    if (salaryHistoryRepository.findByEmpId(employee.getEmpId()).size() == 1 && employee.getNominee() != null) {
                       updateEmployeeNomineeDetails(pfResultData, employee.getNominee(), date);
                    }
                    break;
                }
            }
            if (pfEmployee != null && pfEmployee.getSalary().isPfRequired()) {
                BigDecimal basic = (salaryHistory.getBasic().setScale(0, BigDecimal.ROUND_HALF_UP).compareTo(CalculationRules.ELIGIBLE_PF_AMOUNT) < 0 ? salaryHistory.getBasic().setScale(0, BigDecimal.ROUND_HALF_UP) : CalculationRules.ELIGIBLE_PF_AMOUNT);
                pfResultData.setEpf(basic);
                pfResultData.setEps(pfResultData.getEpf());
                pfResultData.setEpfDue(pfResultData.getEpf().multiply(CalculationRules.PER_PF).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_HALF_UP));
                pfResultData.setEpfRemitted(pfResultData.getEpfDue());
                pfResultData.setEpsDue(pfResultData.getEps().multiply(CalculationRules.PER_AC_10_ORG_CONTRIBUTION).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_HALF_UP));
                pfResultData.setEpsRemitted(pfResultData.getEpsDue());
                pfResultData.setEpfEpsDueDifference(pfResultData.getEpfDue().subtract(pfResultData.getEpsDue()));
                pfResultData.setEpfEpsRemittedDifference(pfResultData.getEpfRemitted().subtract(pfResultData.getEpsRemitted()));
                pfResultData.setLop(salaryHistory.getLopDays().setScale(0, BigDecimal.ROUND_HALF_UP).longValue());
                if (StringUtils.isNotEmpty(pfReportData.toString())) {
                    pfReportData.append("\r\n");
                }
                pfReportData.append(pfResultData.toString());
            }
        }
    }

    /**
     * Method to prepare the PfResultData from SettlementEmployees
     * @param date the pf report date
     * @param pfReportData to add the pf report result
     */
    private void preparePfResultDataFromSettlementEmployees(Date date, StringBuffer pfReportData) {
        List<EmployeeSettlement> employeeSettlementList = employeeSettlementRepository.findEmployeeSettlementByDate(date);
        for (EmployeeSettlement employeeSettlement : employeeSettlementList) {
            if (employeeSettlement.getEmployee().getSalary().isPfRequired()) {
                PFResultData pfResultData = new PFResultData();
                pfResultData.setMemberId(StringUtils.substringAfterLast(employeeSettlement.getEmployee().getPfNo(), SLASH));
                pfResultData.setName(getEmployeeFullName(employeeSettlement.getEmployee()));
                /* For given date settlement employees */
                if (DateUtils.findMonthsDifference(date, employeeSettlement.getSettlementDate()) == 0) {
                    BigDecimal basic = (employeeSettlement.getBasic().setScale(0, BigDecimal.ROUND_HALF_UP).compareTo(CalculationRules.ELIGIBLE_PF_AMOUNT) < 0 ? employeeSettlement.getBasic().setScale(0, BigDecimal.ROUND_HALF_UP) : CalculationRules.ELIGIBLE_PF_AMOUNT);
                    pfResultData.setEpf(basic);
                    pfResultData.setEps(pfResultData.getEpf());
                    pfResultData.setEpfDue(pfResultData.getEpf().multiply(CalculationRules.PER_PF).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_HALF_UP));
                    pfResultData.setEpfRemitted(pfResultData.getEpfDue());
                    pfResultData.setEpsDue(pfResultData.getEps().multiply(CalculationRules.PER_AC_10_ORG_CONTRIBUTION).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_HALF_UP));
                    pfResultData.setEpsRemitted(pfResultData.getEpsDue());
                    pfResultData.setEpfEpsDueDifference(pfResultData.getEpfDue().subtract(pfResultData.getEpsDue()));
                    pfResultData.setEpfEpsRemittedDifference(pfResultData.getEpfRemitted().subtract(pfResultData.getEpsRemitted()));
                    /* For previous date settlement employees */
                } else {
                    pfResultData.setReasonForLeaving(CESSATION_CODE);
                    Calendar dateOfExit = Calendar.getInstance();
                    dateOfExit.setTime(date);
                    dateOfExit.set(Calendar.DAY_OF_MONTH, 1);
                    pfResultData.setDateOfExitEpf(DD_MM_YYYY.format(dateOfExit.getTime()));
                    pfResultData.setDateOfExitEps(DD_MM_YYYY.format(dateOfExit.getTime()));
                }
                if (StringUtils.isNotEmpty(pfReportData.toString())) {
                    pfReportData.append("\r\n");
                }
                pfReportData.append(pfResultData.toString());
            }
        }
    }

    /**
     * Method to get employee full name
     * @param employee the employee
     * @return employee full name
     */
    private static String getEmployeeFullName(Employee employee) {
        String fullName = employee.getFirstName().concat(SPACE);
        if (StringUtils.isNotBlank(employee.getMiddleName())) {
            fullName = fullName.concat(employee.getMiddleName()).concat(SPACE);
        }
        fullName = fullName.concat(employee.getLastName());
        return fullName.toUpperCase();
    }

    /**
     * Method to update employee nominee details
     * @param pfResultData contains pf form data
     * @param nominee the employee nominee details
     * @param date the pf form date
     */
    private void updateEmployeeNomineeDetails(PFResultData pfResultData, Nominee nominee, Date date) {
        pfResultData.setFatherOrHusbandName(nominee.getFullName());
        pfResultData.setRelationWithMember(findPFNomineeRelationCode(nominee.getRelation()));
        pfResultData.setDateOfBirth(DD_MM_YYYY.format(nominee.getDateOfBirth()));
        String gender = (Relation.FATHER.getCode().equalsIgnoreCase(nominee.getRelation()) || Relation.HUSBAND.getCode().equalsIgnoreCase(nominee.getRelation()) ? "M" : "F");
        pfResultData.setGender(gender);
        Calendar dateOfJoin = Calendar.getInstance();
        dateOfJoin.setTime(date);
        dateOfJoin.set(Calendar.DAY_OF_MONTH, 1);
        pfResultData.setDateOfJoiningEpf(DD_MM_YYYY.format(dateOfJoin.getTime()));
        pfResultData.setDateOfJoiningEps(pfResultData.getDateOfJoiningEpf());
    }

    /**
     * Method to find the relation ship code for pf form
     * @param relation of nominee with employee
     * @return relation ship code
     */
    private String findPFNomineeRelationCode(String relation) {
        if (Relation.FATHER.getCode().equalsIgnoreCase(relation) || Relation.MOTHER.getCode().equalsIgnoreCase(relation)) {
            return "F";
        } else if (Relation.HUSBAND.getCode().equalsIgnoreCase(relation) || Relation.WIFE.getCode().equalsIgnoreCase(relation)) {
            return "S";
        }
        return null;
    }

	private static BigDecimal calculate(BigDecimal total,BigDecimal percentage){
        return (total.multiply(percentage).divide(new BigDecimal(100))).setScale(0,BigDecimal.ROUND_HALF_DOWN);
	}
	/**
	 * @return the salaryHistoryDAO
	 */
	public SalaryHistoryRepository getSalaryHistoryDAO() {
		return salaryHistoryRepository;
	}

	/**
	 * @param salaryHistoryDAO the salaryHistoryDAO to set
	 */
	public void setSalaryHistoryDAO(SalaryHistoryRepository salaryHistoryDAO) {
		this.salaryHistoryRepository = salaryHistoryDAO;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

    /**
     * @param employeeSettlementDAO to set
     */
    public void setEmployeeSettlementDAO(EmployeeSettlementRepository employeeSettlementDAO) {
        this.employeeSettlementRepository = employeeSettlementDAO;
    }

    /**
     * @param employeeDAO to set
     */
    public void setEmployeeDAO(EmployeeRepository employeeDAO) {
        this.employeeRepository = employeeDAO;
    }
}
