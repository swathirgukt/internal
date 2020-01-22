package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.GeneratedForm16;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Jpa Repository for Generated Form16
 * <p>
 * User: Praveen
 * Date: 22/01/2020
 */
@Repository
public interface GeneratedForm16Repository extends JpaRepository<GeneratedForm16, Long> {

    // void saveOrUpdate(GeneratedForm16 generatedForm16);

    @Query("from GeneratedForm16 f16 left join fetch f16.financialYear fy where f16.empId=:empId and fy.fromMonth=:fromMonth and fy.fromYear=:fromYear and fy.toMonth=:toMonth and fy.toYear=:toYear")
    GeneratedForm16 findPdfByEmpIdAndFinancialYear(@Param("empId") String empId, @Param("fromMonth") String fromMonth, @Param("fromYear") String fromYear, @Param("toMonth") String toMonth, @Param("toYear") String toYear);
}
