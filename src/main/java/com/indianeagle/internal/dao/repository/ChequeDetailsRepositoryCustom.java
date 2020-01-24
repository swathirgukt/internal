package com.indianeagle.internal.dao.repository;


import com.indianeagle.internal.dto.ChequeDetails;
import com.indianeagle.internal.form.ChequeDetailsForm;

import java.util.List;

/**
 * Custom Repository for ChequeDetails
 *
 * @author Praveen
 * @Date 22/01/2020
 */
public interface ChequeDetailsRepositoryCustom {
    public List<ChequeDetails> searchChequeDetails(ChequeDetailsForm chequeDetailsForm);

    public List<ChequeDetails> postChequeDetails();
}
