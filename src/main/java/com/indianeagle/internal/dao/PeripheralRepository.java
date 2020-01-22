package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Peripheral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Praveen
 */
@Repository
public interface PeripheralRepository extends JpaRepository<Peripheral, Long>, PeripheralRepositoryCustom {


}
