package com.indianeagle.internal.dao.repository;


import com.indianeagle.internal.dto.ChequeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface ChequeDetailsRepository extends JpaRepository<ChequeDetails, Long>, ChequeDetailsRepositoryCustom {


    ChequeDetails save(ChequeDetails chequeDetails);


    List<ChequeDetails> findByChequeDateAndChequeDateBetweenAndAmountOrderByChequeDate(Date startDat, Date startDate, Date endDate, BigDecimal amount);


}
