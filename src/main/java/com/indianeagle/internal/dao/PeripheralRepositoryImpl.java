package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Peripheral;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PeripheralRepositoryImpl implements PeripheralRepositoryCustom {
    @PersistenceUnit
    EntityManager entityManager;

    /**
     * Method searches for peripheral objects based on the values
     * present in the argument object
     *
     * @param peripheral
     * @return collection of peripheral objects
     */
    @SuppressWarnings("unchecked")
    public List<Peripheral> searchPeripherals(Peripheral peripheral) {
        Query nativeQuery = null;
        StringBuffer query = new StringBuffer("from Peripheral p ");

        if (!SimpleUtils.isEmpty(peripheral.getPeripheralName()))
            query.append(" where p.peripheralName  LIKE '" + peripheral.getPeripheralName() + "'");
        else if (!SimpleUtils.isEmpty(peripheral.getType()))
            query.append(" where p.warrantyDate <= '" + SimpleUtils.utilDateToSqlDate(peripheral.getWarrantyDate()) + "'");
        else if (!SimpleUtils.isEmpty(peripheral.getYearOfPurchase()))
            query.append(" where p.yearOfPurchase <= '" + SimpleUtils.utilDateToSqlDate(peripheral.getYearOfPurchase()) + "'");
        else if (!SimpleUtils.isEmpty(peripheral.getUsers()))
            query.append(" where p.users LIKE '" + peripheral.getUsers() + "'");
        nativeQuery = entityManager.createNativeQuery(query.toString());
        return nativeQuery.getResultList();

    }
}
