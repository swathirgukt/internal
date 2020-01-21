package com.indianeagle.internal.facade;

import com.indianeagle.internal.form.FormESIC;
import com.indianeagle.internal.form.PtForm;
import java.io.InputStream;
import java.util.Date;

/**
 * Service class for various forms
 *
 * @author kiran.paluvadi
 */
public interface FormsService {

    /**
     * prepares FormV's form result
     *
     * @param date
     * @return prepares FormsResultData
     */
    PtForm getMonthlyRtReport(Date date);

    /**
     * method to generate Monthly ESIC report
     *
     * @param date
     * @return formESIC
     */
    FormESIC getMonthlyESIReport(Date date);

    /**
     * method to generate Monthly PF report
     *
     * @param date
     * @return InputStream
     */
    InputStream getMonthlyPfReport(Date date);


}
