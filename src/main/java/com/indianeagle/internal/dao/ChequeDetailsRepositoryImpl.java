
package com.indianeagle.internal.dao;


import com.indianeagle.internal.dto.ChequeDetails;
import com.indianeagle.internal.form.ChequeDetailsForm;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Custom Repository implementation for ChequeDetails
 *
 * @author Praveen
 * @Date 22/01/2020
 */
@Repository
@Transactional(readOnly = true)
public class ChequeDetailsRepositoryImpl implements ChequeDetailsRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Searches for cheque details object based on the information
     * provided in the chequeDetails form
     *
     * @param chequeDetailsForm
     * @return list of ChequeDetails objects
     */
    @Override
    public List<ChequeDetails> searchChequeDetails(ChequeDetailsForm chequeDetailsForm) {
        Query query = entityManager.createNativeQuery(generateQuery(chequeDetailsForm));
        return query.getResultList();
    }


    /**
     * Generate Query to search for ChequeDetails using fromDate, toDate and amount
     */

    public String generateQuery(ChequeDetailsForm chequeDetailsForm) {
        StringBuffer queryString = new StringBuffer("from ChequeDetails cheque where ");
        boolean checkFilter = false;
        if (!SimpleUtils.isEmpty(chequeDetailsForm.getFromDate())) {
            checkFilter = true;
            queryString.append(" cheque.chequeDate >= '" + SimpleUtils.YYYY_MM_DD.format(chequeDetailsForm.getFromDate()) + "'");
        }
        if (!SimpleUtils.isEmpty(chequeDetailsForm.getToDate())) {
            if (checkFilter) {
                queryString.append(" and ");
            }
            queryString.append(" cheque.chequeDate <= '" + SimpleUtils.YYYY_MM_DD.format(chequeDetailsForm.getToDate()) + "'");
            checkFilter = true;
        }
        if (!SimpleUtils.isEmpty(chequeDetailsForm.getAmount())) {
            if (checkFilter) {
                queryString.append(" and ");
            }
            queryString.append(" cheque.amount = " + chequeDetailsForm.getAmount());
        }
        queryString.append(" order by cheque.chequeDate");
        return queryString.toString();
    }

    @Override
    public List<ChequeDetails> postChequeDetails() {
        Query query = entityManager.createNativeQuery("select cd from ChequeDetails cd where cd.chequeDate = ?");
        query.setParameter(1, SimpleUtils.tomorrowDate());
        return query.getResultList();
    }


}

