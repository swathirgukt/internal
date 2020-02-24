package com.indianeagle.internal.dao.repository;

import com.indianeagle.internal.dto.EmpSalaryDecider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository for SalaryDecider DTO
 *
 * Author: Taymur Shaikh
 * Date: 10/02/2020
 */
public interface SalaryDeciderRepository extends JpaRepository<EmpSalaryDecider, Long> {

    @Query("SELECT s from EmpSalaryDecider s where (DATE(s.salaryDate) BETWEEN DATE(:salaryDate)AND DATE(:salaryEndDate)) OR (DATE(s.salaryEndDate) BETWEEN DATE(:salaryDate)AND DATE(:salaryEndDate)) AND (YEAR(s.salaryDate) = YEAR(salaryDate) OR  YEAR(s.salaryEndDate) = YEAR(:salaryEndDate))")
    List<EmpSalaryDecider> findAllBetween(@Param("salaryDate")Date salaryDate, @Param("salaryEndDate")Date salaryEndDate);
}
