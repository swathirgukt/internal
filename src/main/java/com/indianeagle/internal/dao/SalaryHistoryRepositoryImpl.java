package com.indianeagle.internal.dao;

import com.indianeagle.internal.util.CalculationRules;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SalaryHistoryRepositoryImpl implements SalaryHistoryRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * gives the count of employees's list for specified range to calculate PT
     *
     * @param date
     * @return List<Integer>
     */
    public List<Long> getMonthlyRtReport(Date date) {
        Query query = null;

        List<Integer> inputList = new ArrayList<Integer>(CalculationRules.PT_RANGE.keySet());
        List<Long> resultList = new ArrayList<Long>();

        String minQuery = "SELECT count(sh.empId) FROM SalaryHistory as sh WHERE month(sh.salaryDate) = month(?) AND year(sh.salaryDate) = year(?) AND (sh.grossSalary > " + CalculationRules.PT_PAID_ON.intValue() + ") AND sh.grossSalary  BETWEEN ? AND ? ";
        String maxQuery = "SELECT count(sh.empId) FROM SalaryHistory as sh WHERE month(sh.salaryDate) = month(?) AND year(sh.salaryDate) = year(?) AND sh.grossSalary  >= ? ";

        for (int i = 0; i < inputList.size(); i++) {
            if (i != inputList.size() - 1) {

                query = entityManager.createNativeQuery(minQuery);
                query.setParameter(1, date);
                query.setParameter(2, date);
                query.setParameter(3, new BigDecimal(inputList.get(i) + 1));
                query.setParameter(4, new BigDecimal((inputList.get(i + 1))));

                Long noOfEmp = ((Number) query.getSingleResult()).longValue();
                resultList.add(noOfEmp);
            } else {

                query = entityManager.createNativeQuery(maxQuery);
                query.setParameter(1, date);
                query.setParameter(2, date);
                query.setParameter(3, new BigDecimal(inputList.get(i) + 1));


                Long noOfEmp = ((Number) query.getSingleResult()).longValue();
                resultList.add(noOfEmp);

            }

        }

        return resultList;
    }

    /**
     * get Monthly PF Report
     *
     * @param date
     * @param range
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<Object> getMonthlyPfReport(Date date, BigDecimal range) {
        Query basicLtListQuery = null;
        Query basicGtlistQuery = null;

        basicLtListQuery = entityManager.createNativeQuery("SELECT COUNT(sh.empId) AS COUNT,SUM(sh.basic) AS AMOUNT FROM SalaryHistory sh WHERE sh.basic <= ? AND MONTH(sh.salaryDate) = MONTH(?) AND YEAR(sh.salaryDate) = YEAR (?)");
        basicLtListQuery.setParameter(1, range);
        basicLtListQuery.setParameter(2, date);
        basicLtListQuery.setParameter(3, date);
        List<Object> basicLtList = basicLtListQuery.getResultList();

        basicGtlistQuery = entityManager.createNativeQuery("SELECT COUNT(sh.empId) AS COUNT,SUM(sh.basic) AS AMOUNT FROM SalaryHistory sh WHERE sh.basic > ? AND MONTH(sh.salaryDate) = MONTH(?) AND YEAR(sh.salaryDate) = YEAR (?)");
        basicGtlistQuery.setParameter(1, range);
        basicGtlistQuery.setParameter(2, date);
        basicGtlistQuery.setParameter(3, date);
        List<Object> basicGtlist = basicGtlistQuery.getResultList();

        List<Object> totalList = new ArrayList<Object>();
        if (!basicGtlist.isEmpty()) {
            totalList.add(basicGtlist.get(0));
        }
        if (!basicLtList.isEmpty()) {
            totalList.add(basicLtList.get(0));
        }
        return totalList;
    }
}
