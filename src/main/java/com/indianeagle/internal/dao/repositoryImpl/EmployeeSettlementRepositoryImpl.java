package com.indianeagle.internal.dao.repositoryImpl;

import com.indianeagle.internal.dao.repository.EmployeeSettlementRepositoryCustom;
import com.indianeagle.internal.dto.EmployeeSettlement;
import com.indianeagle.internal.util.CalculationRules;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Custom  Repository implementation for EmployeeSettlement
 * User: Praveen
 * Date: 22/01/2020
 * Time: 5:52 PM
 */
@Repository
@Transactional(readOnly = true)
public class EmployeeSettlementRepositoryImpl implements EmployeeSettlementRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Method to find the count of employee's list for specified range to calculate PT
     *
     * @param date the specified date
     * @return list containing no of employees by PT range
     */
    //todo: error will be gone after adding service packgae
    public List<Long> findMonthlySettlementReport(Date date) {
        Query query = null;
        List<Integer> ptRangeList = new ArrayList<Integer>(CalculationRules.PT_RANGE.keySet());
        List<Long> noOfEmployeesByPTRange = new ArrayList<Long>();
        String minDateRangeQuery = "SELECT count(ID) FROM EMPLOYEE_SETTLEMENT as employeeSettlement WHERE month(SETTLEMENT_DATE) = month(?) AND year(SETTLEMENT_DATE) = year(?) AND (GROSS_SALARY> " + CalculationRules.PT_PAID_ON.intValue() + ") AND GROSS_SALARY  BETWEEN ? AND ? ";
        String maxDateRangeQuery = "SELECT count(ID) FROM EMPLOYEE_SETTLEMENT as employeeSettlement WHERE month(SETTLEMENT_DATE) = month(?) AND year(SETTLEMENT_DATE) = year(?) AND GROSS_SALARY  >= ? ";
        for (int index = 0; index < ptRangeList.size(); index++) {
            if (index != ptRangeList.size() - 1) {

                query = entityManager.createNativeQuery(minDateRangeQuery);
                query.setParameter(1, date);
                query.setParameter(2, date);
                query.setParameter(3, new BigDecimal(ptRangeList.get(index) + 1));
                query.setParameter(4, new BigDecimal((ptRangeList.get(index + 1))));
                Long noOfEmployees = ((Number) query.getSingleResult()).longValue();
                noOfEmployeesByPTRange.add(noOfEmployees);
            } else {
                query = entityManager.createNativeQuery(maxDateRangeQuery);
                query.setParameter(1, date);
                query.setParameter(2, date);
                query.setParameter(3, new BigDecimal(ptRangeList.get(index) + 1));
                Long noOfEmployees = ((Number) query.getSingleResult()).longValue();
                noOfEmployeesByPTRange.add(noOfEmployees);
            }
        }
        return noOfEmployeesByPTRange;
    }


    /**
     * Method to find the EmployeeSettlement list by given settlement date
     *
     * @param date EmployeeSettlement date
     * @return EmployeeSettlement list
     */
    public List<EmployeeSettlement> findEmployeeSettlementByDate(Date date) {
        Query query = entityManager.createQuery("select employeeSettlement from EmployeeSettlement employeeSettlement left join fetch employeeSettlement.employee employee WHERE (month(employeeSettlement.settlementDate) = month(?1) AND year(employeeSettlement.settlementDate) = year(?2)) OR (month(employeeSettlement.settlementDate) = month(?3) AND year(employeeSettlement.settlementDate) = year(?4))");
        Calendar previousMonthDate = Calendar.getInstance();
        previousMonthDate.setTime(date);
        previousMonthDate.set(Calendar.MONTH, previousMonthDate.get(Calendar.MONTH) - 1);
        query.setParameter(1, date);
        query.setParameter(2, date);
        query.setParameter(3, previousMonthDate.getTime());
        query.setParameter(4, previousMonthDate.getTime());
        return query.getResultList();
    }

    /**
     * method to get monthly ESI report
     *//*
    public List<Object> getMonthlyESIReport(Date formDate) {
        Query query = entityManager.createNativeQuery("select COUNT(es.employee),SUM(es.esi),SUM(es.grossSalary) from EmployeeSettlement es where MONTH (es.settlementDate) = month(?) and year(es.settlementDate) = year(?) and es.esi > 0");
        query.setParameter(1, formDate);
        query.setParameter(2, formDate);
        return query.getResultList();

    }*/
}

