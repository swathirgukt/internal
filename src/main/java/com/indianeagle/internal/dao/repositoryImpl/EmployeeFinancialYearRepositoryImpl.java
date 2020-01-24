package com.indianeagle.internal.dao.repositoryImpl;

import com.indianeagle.internal.dao.repository.EmployeeFinancialYearRepositoryCustom;
import com.indianeagle.internal.dto.EmployeeFinancialYear;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class EmployeeFinancialYearRepositoryImpl implements EmployeeFinancialYearRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * To retrieve EmployeeFinancialYear with employee Id and FinancialYear
     *
     * @param empId
     * @param fromMonth
     * @param fromYear
     * @param toMonth
     * @param toYear
     * @return
     */


/*
    if possible later try to change it to Spring data jpa
     */
    public List<EmployeeFinancialYear> findEmployeeFinancialYearWithEmpIdAndFinancialYear(String empId, String fromMonth, String fromYear, String toMonth, String toYear) {
        Query query = entityManager.createNativeQuery("select efy from EmployeeFinancialYear efy left join fetch efy.financialYear fy left join fetch efy.employeeTaxSections ets left join fetch ets.employeeTaxSectionDeclarations etsd where efy.empId=? and fy.fromMonth=? and fy.fromYear=? and fy.toMonth=? and fy.toYear=? and ets.active=true and etsd.active=true");
        query.setParameter(1, empId);
        query.setParameter(2, fromMonth);
        query.setParameter(3, fromYear);
        query.setParameter(4, toMonth);
        query.setParameter(5, toYear);
        return query.getResultList();
    }


}
