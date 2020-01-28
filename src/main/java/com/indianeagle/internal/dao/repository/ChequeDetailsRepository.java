package com.indianeagle.internal.dao.repository;


import com.indianeagle.internal.dto.ChequeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DAO for saveOrUpdate, search and edit the 'ChequeDetails' into 'CHEQUE_DETAILS' table
 *
 * @author Praveen
 * @Date 22/01/2020
 */
@Repository
public interface
ChequeDetailsRepository extends JpaRepository<ChequeDetails, Long> {

    /**
     * to get tomorrow's ChequeDetails object
     *
     * @param chequeDate
     * @return collection of ChequeDetail objects
     */
    List<ChequeDetails> findByChequeDate(Date chequeDate);

    /**
     * to get all ChequeDetails between two dates with given amount
     *
     * @param startDate
     * @param endDate
     * @param amount
     * @return collection of ChequeDetails objects
     */
    List<ChequeDetails> findByChequeDateBetweenAndAmountOrderByChequeDate(@Temporal(TemporalType.DATE) Date startDate, @Temporal(TemporalType.DATE) Date endDate, BigDecimal amount);


}
