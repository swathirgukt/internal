package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Incentives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Jpa Repository for incentives
 *
 * @author Praveen
 */
@Repository
public interface IncentiveRepository extends JpaRepository<Incentives, Long> {


    /**
     * method to search Incentives of Employees
     *
     * @param incentiveDate
     * @return List<Incentives>
     */
    //List<Incentives> searchIncentives(Date date);
    List<Incentives> findByIncentiveDate(Date incentiveDate);
}
