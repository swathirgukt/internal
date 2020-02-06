package com.indianeagle.internal.validator;

import com.indianeagle.internal.form.EmployeeForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class EmployeeFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.isAssignableFrom(EmployeeForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeForm employeeForm=(EmployeeForm)target;

        /*if(isEmpty(employeeForm.getEmployeeVO().getJoinDate()))
        {
            errors.rejectValue("joinDate","provide.joinDate");
        }*/

       /* if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getDesignation()))
        {
            errors.rejectValue("designation","provide.designation");
        }*/

       /* if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getEmpId()))
        {
            errors.rejectValue("empId","provide.empId");
        }
        if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getBankName()))
        {
            errors.rejectValue("bankName","provide.bankName");
        }
        if(StringUtils.isEmpty(employeeForm.getRoleName()))
        {
            errors.rejectValue("roleName","provide.roleName");
        }
        if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getBankAc()))
        {
            errors.rejectValue("bankAccountNumber","provide.bankAccountNumber");
        }
*/
      /*  if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getFirstName()))
        {
            errors.rejectValue("firstName","provide.firstName");
        }

        if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getLastName()))
        {
            errors.rejectValue("lastName","provide.lastName");
        }

        if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getPerAddress()))
        {
            errors.rejectValue("perAddress","provide.perAddress");
        }

        if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getTempAddress()))
        {
            errors.rejectValue("tempAddress","provide.tempAddress");
        }

        if(isEmpty(employeeForm.getEmployeeVO().getDob()))
        {
            errors.rejectValue("dob","provide.dob");
        }

        if(employeeForm.getEmployeeVO().getMobileNo()==0)
        {
            errors.rejectValue("mobileNo","provide.mobileNo");
        }
        if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getEmergencyContact()))
        {
            errors.rejectValue("emergencyContact","provide.emergencyContact");
        }
        if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getPersonalEmail()))
        {
            errors.rejectValue("personalEmail","provide.personalEmail");
        }else if(isValidEmail(employeeForm.getEmployeeVO().getPersonalEmail()))
        {
            errors.rejectValue("invalidPersonalEmail","provide.invalidPersonalEmail");
        }

        if(StringUtils.isEmpty(employeeForm.getEmployeeVO().getOfficialEmail()))
        {
            errors.rejectValue("officialEmail","provide.officialEmail");
        }else if(isValidEmail(employeeForm.getEmployeeVO().getOfficialEmail()))
        {
            errors.rejectValue("invalidOfficialEmail","provide.invalidOfficialEmail");
        }*/

        /*if(employeeForm.getEmployeeVO().getId()==0){
            errors.rejectValue("id","provide.id");
        }*/

    }

    /**
     * Validate Email format.Checks whether mail is in the format or not.
     * Note : If Email is Valid then return 'false' otherwise 'true'
     * @param value
     * @return
     */
  /*  public static boolean isValidEmail(String value)
    {
        Pattern p = Pattern.compile("^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");

        Matcher matcher = p.matcher(value.toUpperCase());
        if(!matcher.find())
        {
            return true;
        }
        else
        {
            return false ;
        }
    }*/

    /**
     * Check null or empty
     * @param date
     * @return
     */
 /*   public static boolean isEmpty(Date date) {
        return (date == null || "".equals(date));
    }*/
}
