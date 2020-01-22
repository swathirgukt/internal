package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Peripheral;

import java.util.List;

public interface PeripheralRepositoryCustom {
    /**
     * Method searches for peripheral objects based on the values
     * present in the argument object
     *
     * @param peripheral
     * @return collection of peripheral objects
     */
    @SuppressWarnings("unchecked")
    public List<Peripheral> searchPeripherals(Peripheral peripheral);


}
