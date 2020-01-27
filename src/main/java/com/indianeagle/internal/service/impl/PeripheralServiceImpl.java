package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.PeripheralRepository;
import com.indianeagle.internal.dto.Peripheral;
import com.indianeagle.internal.service.PeripheralService;
import java.util.List;
import java.util.Optional;

/**
 * @author SVK
 */
public class PeripheralServiceImpl implements PeripheralService {

	private PeripheralRepository peripheralRepository;

	public void saveOrUpdate(Peripheral peripheral){
		peripheralRepository.save(peripheral);
	}
	
	@SuppressWarnings("unchecked")
	public Optional<Peripheral> findById(long id){

		return  (peripheralRepository.findById(id));
	}
	
	public List<Peripheral> searchPeripherals(Peripheral peripheral){
		return peripheralRepository.searchPeripherals(peripheral);
	}
	public void setPeripheralDAO(PeripheralRepository peripheralDAO) {
		this.peripheralRepository = peripheralDAO;
	}
}
