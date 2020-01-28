package com.indianeagle.internal.service.impl1;

import com.indianeagle.internal.dto.Peripheral;
import com.indianeagle.internal.service.PeripheralService;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service
public class PeripheralServiceImpl implements PeripheralService {
    List<Peripheral> peripherals;

    @PostConstruct
    public void loadPeripherals() {
        peripherals = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Peripheral peripheral = new Peripheral();
            peripheral.setPeripheralName("peripheralName" + i);
            peripheral.setBrand("peripheralBrand" + i);
            peripheral.setModel("peripheralModel" + i);
            peripheral.setType("peripheralType" + i);
            peripheral.setSerialNumber("peripheralSN" + i);
            peripheral.setStatus("peripheralStatus" + i);
            peripheral.setUsers("peripheralUser" + i);
            peripheral.setYearOfPurchase(new Date());
            peripheral.setWarrantyDate(new Date(2022));
            peripheral.setId(i + 1000L);
            peripherals.add(peripheral);
        }
    }

    @Override
    public void saveOrUpdate(Peripheral peripheral) {
        ListIterator<Peripheral> iterator = peripherals.listIterator();
        System.out.println("##iterator "+iterator.hasNext());
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(peripheral.getId())) {
                iterator.set(peripheral);
                return;
            }
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral findById(long id) {
        for (Peripheral peripheral:peripherals)
            if (peripheral.getId().equals(id))
                return peripheral;
        return null;
    }

    @Override
    public List<Peripheral> searchPeripherals(Peripheral peripheral) {
        List<Peripheral> list = new ArrayList<>();
        if (!SimpleUtils.isEmpty(peripheral.getPeripheralName())) {
            for (Peripheral p : peripherals) {
                if (p.getPeripheralName().equals(peripheral.getPeripheralName())) {
                    list.add(p);
                    return list;
                }
            }
        }
        if (!SimpleUtils.isEmpty(peripheral.getType())) {
            for (Peripheral p : peripherals) {
                if (p.getType().equals(peripheral.getType())) {
                    list.add(p);
                    return list;
                }
            }
        }
        if (peripheral.getYearOfPurchase()!=null) {
            for (Peripheral p : peripherals) {
                if (p.getYearOfPurchase().before(peripheral.getYearOfPurchase()) || p.getYearOfPurchase().equals(peripheral.getYearOfPurchase())) {
                    list.add(p);
                }
            }
            if (!list.isEmpty())
                return list;
        }

        return peripherals;
    }
}
