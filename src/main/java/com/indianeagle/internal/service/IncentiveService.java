package com.indianeagle.internal.facade;

import com.indianeagle.internal.dto.Incentives;
import com.yana.internal.form.IncentiveForm;
import java.util.Date;
import java.util.List;

/**
 * Service class for Incentives
 *
 * @author kiran.paluvadi
 */
public interface IncentiveService {

    /**
     * method to save Incentives
     *
     * @param incentiveForm
     */
    void saveIncentives(IncentiveForm incentiveForm);

    /**
     * method to search Incentives of Employees
     *
     * @param date
     * @return List<Incentives>
     */
    List<Incentives> searchIncentives(Date date);


}
