package com.indianeagle.internal;

import com.indianeagle.internal.constants.StringConstants;
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
    MailContentBuilder mailContentBuilder;

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
        String html = MailContentBuilder.build(templateEngine, "/mail-templates/passwordReset", messageModel);
        System.out.println(html);
    }
/*
    @Test
    void tempTemplateTest() {
        Map<String, Object> messageModel = new HashMap<String, Object>();
        messageModel.put("StringConstants", StringConstants.class);
        String html = MailContentBuilder.build(templateEngine, "/mail-templates/temp", messageModel);
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

        String html = MailContentBuilder.build(templateEngine, "/mail-templates/IE_Payslip", model);
        System.out.println(html);
    }

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

        String html = MailContentBuilder.build(templateEngine, "/mail-templates/accountInfo", messageModel);
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
        String html = MailContentBuilder.build(templateEngine, "/mail-templates/chequeDetails", messageModel);
        System.out.println(html);
    }

}
