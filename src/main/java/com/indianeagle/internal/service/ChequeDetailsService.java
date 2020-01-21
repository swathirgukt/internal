package com.indianeagle.internal.facade;

import com.yana.internal.dto.ChequeDetails;
import com.yana.internal.form.ChequeDetailsForm;
import java.util.List;

/**
 * @author irfan
 * service for Cheque Details
 */
public interface ChequeDetailsService {

    /**
     * To save an ChequeDetails
     *
     * @param chequeDetails
     */
    void saveOrUpdateCheque(ChequeDetails chequeDetails);

    /**
     * To search for ChequeDetails
     *
     * @param chequeDetails
     * @return
     */
    List<ChequeDetails> searchChequeDetails(ChequeDetailsForm chequeDetailsForm);

    /**
     * Find the cheque Details by id
     *
     * @param chequeDetails
     * @return
     */
    ChequeDetails findBy(long chequeDetails);

}
