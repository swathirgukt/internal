package com.indianeagle.internal.service;

import com.indianeagle.internal.dto.Peripheral;
import java.util.List;
import java.util.Optional;

public interface PeripheralService {

    void saveOrUpdate(Peripheral peripheral);

    Optional<Peripheral> findById(long id);

    List<Peripheral> searchPeripherals(Peripheral peripheral);
}
