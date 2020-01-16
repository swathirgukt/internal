package com.indianeagle.internal.service.mail;


import com.indianeagle.internal.constants.StringConstants;
import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.FinancialYear;
import com.indianeagle.internal.dto.SalaryHistory;
import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.util.DateFormatter;
import com.indianeagle.internal.util.MailContentBuilder;
import com.indianeagle.internal.util.SimpleUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class handling all core mailing functionalities.
 *
 * @author Durga Prasad M
 * @date Nov 22, 2009
 */

@Component
public class MailingEngine {

	@Autowired
	private static final Logger log = Logger.getLogger(MailingEngine.class);
	private static final String BCC_MAIL_ID = "accounts-india@indianeagle.com";
	private static final String FROM_MAIL_ID = "accounts-india@indianeagle.com";
	private static final String DEAR = "Dear ";
	private static final String PLEASE_FIND = "<p><P>Please find attached the pay slip for the month of ";
	private static final String SALARY_RELATED = "<P>If you have any salary related queries please contact ";
	private static final String OLD_MESSAGE = "<P>This is duplicate copy given as per Employee request.";
	private static final String PWD_NOTE = "<P><b>Note:</b> Use your Employee Code as password to open pay slip.";
	private static final String SUBJECT = "PAYSLIP - ";
	private static final String PASSWORD_RESET_TEMPLATE = "/mail-templates/passwordReset";
	private static final String ACCOUNT_INFO_TEMPLATE = "/mail-templates/accountInfo";
	private static final String CHEQUE_DETAILS_TEMPLATE = "/mail-templates/chequeDetails";
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * Genarates and Sends an email with the credentials passed as arguments
	 * along with the VM File location. It sends email only to one address. and
	 * that address is shown in the mail
	 *
	 * @param employee
	 * @param salaryHistory
	 * @param inputStreamSource
	 * @param old
	 */
	public void sendMail(final Employee employee, final SalaryHistory salaryHistory, final InputStreamSource inputStreamSource, final boolean old) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings("unchecked")
			public void prepare(MimeMessage mimeMessage) throws MessagingException {

				MimeMessageHelper eMail = new MimeMessageHelper(mimeMessage, true);
				eMail.setSubject(SUBJECT.concat(SimpleUtils.MONTHYYYY.format(salaryHistory.getSalaryEndDate())).toUpperCase());
				eMail.setFrom(FROM_MAIL_ID);
				eMail.setTo(employee.getOfficialEmail());
				eMail.setBcc(FROM_MAIL_ID);
				eMail.addAttachment(("PAYSLIP-".concat(SimpleUtils.MONTH_YYYY.format(salaryHistory.getSalaryEndDate())).concat(".pdf")).toUpperCase(), inputStreamSource);
				if (old) {
					eMail.setText(DEAR.concat(employee.getFullName()).concat(PLEASE_FIND).concat(SimpleUtils.dateInWord(salaryHistory.getSalaryEndDate())).concat(".").concat(OLD_MESSAGE).concat(SALARY_RELATED).concat(StringConstants.HR_MAIL_ID).concat(".").concat(PWD_NOTE), true);
				} else {
					eMail.setText(DEAR.concat(employee.getFullName()).concat(PLEASE_FIND).concat(SimpleUtils.dateInWord(salaryHistory.getSalaryEndDate())).concat(".").concat(SALARY_RELATED).concat(StringConstants.HR_MAIL_ID).concat(".").concat(PWD_NOTE), true);
				}
			}
		};
		mailSender.send(preparator);
		log.debug("EMP_ID::" + employee.getEmpId() + " MAIL:" + SUBJECT.concat(SimpleUtils.dateInWord(salaryHistory.getSalaryEndDate())) + " Sent to :: " + employee.getOfficialEmail());
	}

	/**
	 * Sends @param newPassword to @param user.
	 *
	 * @param newPassword
	 * @param user
	 */
	public void mailUserPassword(final User user, final String newPassword) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings("unchecked")
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper eMail = new MimeMessageHelper(mimeMessage, true);
				eMail.setSubject("Password Reset");
				eMail.setFrom(FROM_MAIL_ID);
				eMail.setTo(user.getEmail());
				Map<String, Object> messageModel = new HashMap<String, Object>();
				messageModel.put("user", user);
				messageModel.put("newPassword", newPassword);
				messageModel.put("currentYear", Calendar.getInstance().get(Calendar.YEAR));
				String text = MailContentBuilder.build(templateEngine, PASSWORD_RESET_TEMPLATE, messageModel);
				eMail.setText(text, true);
			}
		};
		mailSender.send(preparator);
		log.debug("MAIL: Password is Reset and Sent to :: " + user.getEmail());

	}

	/**
	 * Sends @param newPassword to @param user.
	 *
	 * @param password
	 * @param user
	 */
	public void mailAccountInfo(final User user, final String password) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings("unchecked")
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper eMail = new MimeMessageHelper(mimeMessage, true);
				eMail.setSubject("Account Information");
				eMail.setFrom(FROM_MAIL_ID);
				eMail.setTo(BCC_MAIL_ID);
				eMail.setBcc(BCC_MAIL_ID);
				Map<String, Object> messageModel = new HashMap<String, Object>();
				messageModel.put("user", user);
				messageModel.put("password", password);
				messageModel.put("currentYear", Calendar.getInstance().get(Calendar.YEAR));
				String text = MailContentBuilder.build(templateEngine, ACCOUNT_INFO_TEMPLATE, messageModel);
				eMail.setText(text, true);
			}
		};
		try {
			mailSender.send(preparator);
		} catch (Exception e) {
			log.debug("Got exception while sending email: " + e, e);
		}
		log.debug("MAIL: Account Info Sent to :: " + user.getEmail());
	}

	/**
	 * Sends @param newPassword to @param user.
	 *
	 * @param empId
	 * @param email
	 */
	public void mailAccountDetails(final String empId, final String email) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings("unchecked")
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper eMail = new MimeMessageHelper(mimeMessage, true);
				eMail.setSubject("Account Details Saved");
				eMail.setFrom(FROM_MAIL_ID);
				eMail.setTo(BCC_MAIL_ID);
				eMail.setBcc(BCC_MAIL_ID);
				Map<String, Object> messageModel = new HashMap<String, Object>();
				messageModel.put("empId", empId);
				messageModel.put("currentYear", Calendar.getInstance().get(Calendar.YEAR));
				String text = MailContentBuilder.build(templateEngine, ACCOUNT_INFO_TEMPLATE, messageModel);
				eMail.setText(text, true);
			}
		};
		mailSender.send(preparator);
		log.debug("MAIL: Account details saved mail Sent to :: " + email);
	}


	/**
	 * send Post Cheque Details mail to accountant
	 *
	 * @param modelObjOne
	 */
	public void mailChequeDetails(final Object modelObjOne) {
		@SuppressWarnings("unused")
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper eMail = new MimeMessageHelper(mimeMessage, true);
				eMail.setSubject("Alert: Post Cheque due Date on " + SimpleUtils.dateToString(SimpleUtils.tomorrowDate()));
				eMail.setFrom(FROM_MAIL_ID);
				eMail.setTo(BCC_MAIL_ID);
				Map<String, Object> messageModel = new HashMap<String, Object>();
				messageModel.put("chequeDetailsList", modelObjOne);
				String text = MailContentBuilder.build(templateEngine, CHEQUE_DETAILS_TEMPLATE, messageModel);
				eMail.setText(text, true);
			}
		};
		mailSender.send(preparator);
		log.debug("MAIL: Sending Post Cheque Details to :: " + BCC_MAIL_ID);
	}

	/**
	 * This method to send the details of the employee whose birthday is coming
	 *
	 * @param employee
	 */
	public void mailBirthDayDates(final Employee employee) {
		@SuppressWarnings("unused")
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper eMail = new MimeMessageHelper(mimeMessage, true);
				eMail.setSubject("Reminder: Upcoming birthday - " + WordUtils.capitalize(employee.getFullName().toLowerCase()));
				eMail.setFrom(FROM_MAIL_ID);
				eMail.setTo(BCC_MAIL_ID);
				eMail.setCc(StringConstants.HR_MAIL_ID);
				String text = "<div style=\"color:#F58E27;margin-left: 30px; float:left; margin-right: 30px;\">" + "Employee Name: " + "</div> " + WordUtils.capitalize(employee.getFullName().toLowerCase()) + "<br/>" + "<div style=\"color:#F58E27;margin-left: 30px; float:left; margin-right: 50px;\">" + "Employee ID:" + "</div>" + employee.getEmpId() + "<br/>" + "<div style=\"color:#F58E27; float:left;margin-left: 30px; margin-right: 59px;\">" + "Department: " + "</div>" + employee.getDepartment().getDepartment() + "<br/>" + "<div style=\"color:#F58E27; float:left; margin-left: 30px; margin-right: 43px;\">" + "BirthDay Date: " + "</div>" + DateFormatter.format(employee.getDob(), DateFormatter.DD_MMM_YYYY);
				eMail.setText(text, true);
			}
		};
		mailSender.send(preparator);
		log.debug("MAIL: Sending Upcoming birthday of employee " + employee.getFullName() + " mail to :: " + BCC_MAIL_ID);
	}

	/**
	 * This method to send the details of the employee whose joined date is coming
	 *
	 * @param employee
	 */
	public void mailDateOfJoining(final Employee employee) {
		@SuppressWarnings("unused")
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper eMail = new MimeMessageHelper(mimeMessage, true);
				eMail.setSubject("Reminder: Date of Joining - " + WordUtils.capitalize(employee.getFullName().toLowerCase()));
				eMail.setFrom(FROM_MAIL_ID);
				eMail.setTo(BCC_MAIL_ID);
				eMail.setCc(StringConstants.HR_MAIL_ID);
				String text = "<div style=\"color:#F58E27; float:left; margin-left: 30px; margin-right: 30px;\">" + "Employee Name: " + "</div> " + WordUtils.capitalize(employee.getFullName().toLowerCase()) + "<br/>" + "<div style=\"color:#F58E27; float:left;margin-left: 30px; margin-right: 50px;\">" + "Employee ID:" + "</div>" + employee.getEmpId() + "<br/>" + "<div style=\"color:#F58E27; float:left;margin-left: 30px; margin-right: 59px;\">" + "Department: " + "</div>" + employee.getDepartment().getDepartment() + "<br/>" + "<div style=\"color:#F58E27;margin-left: 30px; float:left; margin-right: 85px;\">" + "Joined: " + "</div>" + DateFormatter.format(employee.getJoinDate(), DateFormatter.DD_MMM_YYYY);
				eMail.setText(text, true);
			}
		};
		mailSender.send(preparator);
		log.debug("MAIL: Sending joined date of the Employee " + employee.getFullName() + " mail to :: " + BCC_MAIL_ID);
	}

	/**
	 * This method to send the Financial year Employee Income Tax details to employee
	 *
	 * @param employee
	 * @param financialYear
	 * @param inputStreamSource
	 */
	public void sendEmployeeIncomeTax(final Employee employee, final FinancialYear financialYear, final InputStreamSource inputStreamSource) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@SuppressWarnings("unchecked")
			public void prepare(MimeMessage mimeMessage) throws MessagingException {

				MimeMessageHelper eMail = new MimeMessageHelper(mimeMessage, true);
				eMail.setSubject("Employee Income Tax Details of Financial Year " + financialYear.getFromYear() + " - " + financialYear.getToYear());
				eMail.setFrom(FROM_MAIL_ID);
				eMail.setTo(employee.getOfficialEmail());
				eMail.setBcc(BCC_MAIL_ID);
				eMail.addAttachment(("Financial Year(" + financialYear.getFromYear() + " - " + financialYear.getToYear() + ")-" + employee.getEmpId().concat(".pdf")).toUpperCase(), inputStreamSource);
				eMail.setText(DEAR.concat(employee.getFullName()).concat("<p>Please find the attached Employee Financial Year Income Tax Details.</p>").concat("<p>If you have any queries please contact.").concat(StringConstants.HR_MAIL_ID).concat("."), true);
			}
		};
		mailSender.send(preparator);
		log.debug("EMP_ID::" + employee.getEmpId() + " MAIL: Employee Income Tax Details of Financial Year " + financialYear.getFromYear() + " - " + financialYear.getToYear() + " Sent to :: " + employee.getOfficialEmail());
	}


}
