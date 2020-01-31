package com.indianeagle.internal.service;

import com.indianeagle.internal.dto.ChequeDetails;
import com.indianeagle.internal.form.ChequeDetailsForm;
import java.util.List;
import java.util.Optional;

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
     * @param chequeDetailsForm
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
