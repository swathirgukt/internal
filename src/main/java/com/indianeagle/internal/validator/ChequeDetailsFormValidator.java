package com.indianeagle.internal.validator;

import com.indianeagle.internal.form.ChequeDetailsForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
@Component
public class ChequeDetailsFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.isAssignableFrom(ChequeDetailsForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChequeDetailsForm chequeDetailsForm=(ChequeDetailsForm)target;

        if(isEmpty(chequeDetailsForm.getChequeDetails().getChequeDate()))
        {
            errors.rejectValue("date","provide.date");
        }

        if(chequeDetailsForm.getChequeDetails().getAmount().doubleValue()==0)
        {
            errors.rejectValue("amount","provide.amount");
        }
        if(chequeDetailsForm.getChequeDetails().getChequeNo()==0)
        {
            errors.rejectValue("chequeNumber","provide.chequeNumber");
        }
        if(StringUtils.isEmpty(chequeDetailsForm.getChequeDetails().getBank()))
        {
            errors.rejectValue("bank","provide.bank");
        }
        if(StringUtils.isEmpty(chequeDetailsForm.getChequeDetails().getStatus()))
        {
            errors.rejectValue("status","provide.Status");
        }
        if(StringUtils.isEmpty(chequeDetailsForm.getChequeDetails().getNameOfPay()))
        {
            errors.rejectValue("nameOfPay","provide.nameOfPay");
        }

        if(StringUtils.isEmpty(chequeDetailsForm.getChequeDetails().getComments()))
        {
            errors.rejectValue("nameOfPay","provide.nameOfPay");
        }

    }

    /**
     * Check null or empty
     * @param value
     * @return
     */
    public static boolean isEmpty(Date date) {
        return (date == null || "".equals(date));
    }


}
