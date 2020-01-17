package com.indianeagle.internal;

import com.indianeagle.internal.dto.ChequeDetails;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.util.MailContentBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
class MailTemplateTests {

    @Autowired
    TemplateEngine templateEngine;

    @Test
    void passworResetTemplateTest() {
        User user = new User();
        user.setUserName("IEPL0500");
        user.setEmail("taymur.s@indianeagle.com");

        Map<String, Object> messageModel = new HashMap<String, Object>();
        messageModel.put("user", user);
        messageModel.put("newPassword", "yana123");
        messageModel.put("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        String html = MailContentBuilder.build(templateEngine, "/mail/passwordReset", messageModel);
        System.out.println(html);
    }
/*
    @Test
    void tempTemplateTest() {
        Map<String, Object> messageModel = new HashMap<String, Object>();
        messageModel.put("StringConstants", StringConstants.class);
        String html = MailContentBuilder.build(templateEngine, "/mail/temp", messageModel);
        System.out.println(html);
    }
*/

    @Test
    void paySlipPDFTest() throws IOException {

        SalaryHistory currentSalary = new SalaryHistory();
        currentSalary.setSalaryEndDate(new Date());
        currentSalary.setBasic(new BigDecimal(6000));

        Employee employee = new Employee();
        employee.setEmpId("IEPL0500");
        employee.setFullName("Taymur");
        employee.setUanNumber("1020304050");
        employee.setPanNo("213456");
        employee.setDesignation("Developer");

        Map<String, Object> model = new HashMap<>();
        model.put("employee", employee);
        model.put("salaryHistory", currentSalary);

        String html = MailContentBuilder.build(templateEngine, "/mail/IE_Payslip", model);
        System.out.println(html);
    }

/*
    @Test
    void form16PDFTest() throws IOException {

        SalaryHistory currentSalary = new SalaryHistory();
        currentSalary.setSalaryEndDate(new Date(2019));
        currentSalary.setBasic(new BigDecimal(40000));

        Employee employee = new Employee();
        employee.setEmpId("IEPL0500");
        employee.setFullName("Taymur");
        employee.setUanNumber("1020304050");
        employee.setPanNo("213456");
        employee.setDesignation("Developer");
        employee.setDob(new Date());
        employee.setPerAddress("Nanded");

        FinancialYear financialYear=new FinancialYear();
        financialYear.setFromYear("2019");
        financialYear.setToYear("2020");
        Set<Rebate> rebates = new HashSet<>();
        Rebate rebate = new Rebate();
        rebate.setRebateAmount(new BigDecimal(12345));
        rebate.setActive(true);
        rebate.setRebateName("Reb1");
        rebates.add(rebate);
        financialYear.setRebates(rebates);

        EmployeeIncomeTax employeeIncomeTax= new EmployeeIncomeTax();
        employeeIncomeTax.setEduCess(new BigDecimal(1200));
        employeeIncomeTax.setEmpId("IEPL0500");
        employeeIncomeTax.setBonus(new BigDecimal(10000));
        employeeIncomeTax.setFinancialYear(financialYear);
        employeeIncomeTax.setTotalTaxOnIncome(new BigDecimal(25000));
        employeeIncomeTax.setTdsDeducted(new BigDecimal(2300));
        employeeIncomeTax.setTaxTobeDeducted(new BigDecimal(10000));
        employeeIncomeTax.setPerMonthTax(new BigDecimal(1050));
        employeeIncomeTax.setTaxableIncome(new BigDecimal(50000));

        EmployeeFinancialYear employeeFinancialYear = new EmployeeFinancialYear();
        employeeFinancialYear.setFinancialYear(financialYear);

        List<SalaryHistory> salaryHistoryList = new ArrayList<>();
        salaryHistoryList.add(currentSalary);

        Map<String, Object> model = new HashMap<>();
        model.put("employee", employee);
        model.put("arrayList", salaryHistoryList);
        model.put("financialYear", financialYear);
        model.put("employeeIncomeTax", employeeIncomeTax);
        model.put("employeeFinancialYear", employeeFinancialYear);


        String html = MailContentBuilder.build(templateEngine, "/mail/form16Pdf", model);
        System.out.println(html);
    }
*/

    @Test
    void accountInfoTemplateTest() {
        User user = new User();
        user.setUserName("IEPL0500");
        user.setEmail("taymur.s@indianeagle.com");

        Map<String, Object> messageModel = new HashMap<String, Object>();
        messageModel.put("user", user);
        messageModel.put("password", "yana123");
        messageModel.put("empId", "IEPL0500");
        messageModel.put("currentYear", Calendar.getInstance().get(Calendar.YEAR));

        String html = MailContentBuilder.build(templateEngine, "/mail/accountInfo", messageModel);
        System.out.println(html);
    }

    @Test
    void chequeTemplateTest() {
        User user = new User();
        user.setUserName("IEPL0500");
        user.setEmail("taymur.s@indianeagle.com");

        ChequeDetails chequeDetails = new ChequeDetails();
        chequeDetails.setAmount(new BigDecimal(1000));
        chequeDetails.setBank("SBI");
        chequeDetails.setChequeDate(new Date());
        chequeDetails.setChequeNo(123456L);
        chequeDetails.setNameOfPay("Mine");

        List<ChequeDetails> list = new ArrayList();
        for (int i = 0; i < 10; i++)
            list.add(chequeDetails);

        Map<String, Object> messageModel = new HashMap<String, Object>();
        messageModel.put("chequeDetailsList", list);
        String html = MailContentBuilder.build(templateEngine, "/mail/chequeDetails", messageModel);
        System.out.println(html);
    }

}
