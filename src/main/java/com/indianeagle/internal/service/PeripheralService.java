package com.indianeagle.internal.facade;

import com.yana.internal.dto.Peripheral;
import java.util.List;

public interface PeripheralService {

    void saveOrUpdate(Peripheral peripheral);

    Peripheral findById(long id);

    List<Peripheral> searchPeripherals(Peripheral peripheral);
}
