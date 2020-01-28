package com.indianeagle.internal.Repository;

import com.indianeagle.internal.dao.repository.PeripheralRepository;
import com.indianeagle.internal.dto.Peripheral;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PeripheralRepositoryTest {
    @Autowired
    PeripheralRepository peripheralRepository;

    @Test
    public void searchPeripherals() {
        Peripheral peripheral = new Peripheral();
        peripheral.setUsers("s");
        peripheralRepository.searchPeripherals(peripheral);

    }
    @Test
    public void findById(){
        Peripheral peripheral=peripheralRepository.findById(1000l).get();
        System.out.println(peripheral);

    }
}
