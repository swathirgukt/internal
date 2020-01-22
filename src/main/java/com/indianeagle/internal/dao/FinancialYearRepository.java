package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.FinancialYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Jpa Repository for FinancialYear
 * <p>
 * User: Praveen
 * Date: 22/02/2020
 */
@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYear, Long> {

    /**
     * To retrieve financialYear data
     *
     * @param fromMonth
     * @param fromYear
     * @param toMonth
     * @param toYear
     * @return
     */
    @Query("select fy from FinancialYear fy left join fetch fy.incomeTaxSlabs its left join fetch fy.taxSections ts left join fetch ts.taxSectionDeclarations tsd left join fetch fy.rebates r where fy.fromMonth=:fromMonth and fy.fromYear=:fromYear and fy.toMonth=:toMonth and fy.toYear=:toYear and its.active=true and ts.active=true and tsd.active=true and r.active=true")
    List<FinancialYear> findFinancialYearSectionsByFinancialYear(String fromMonth, String fromYear, String toMonth, String toYear);

    /**
     * To load all financialYear data
     *
     * @return
     */
    @Query("select fy from FinancialYear fy left join fetch fy.incomeTaxSlabs its left join fetch fy.taxSections ts left join fetch ts.taxSectionDeclarations tsd left join fetch fy.rebates r where its.active=true and ts.active=true and tsd.active=true and r.active=true")
    List<FinancialYear> findAllFinancialYearSections();

    //List<FinancialYear> findAll();
}
