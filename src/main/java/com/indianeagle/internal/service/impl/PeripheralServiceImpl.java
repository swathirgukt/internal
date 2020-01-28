package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.PeripheralRepository;
import com.indianeagle.internal.dto.Peripheral;
import com.indianeagle.internal.service.PeripheralService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author SVK
 */
@Service
public class PeripheralServiceImpl implements PeripheralService {

	private PeripheralRepository peripheralRepository;

	public void saveOrUpdate(Peripheral peripheral){
		peripheralRepository.save(peripheral);
	}
	
	@SuppressWarnings("unchecked")
	public Peripheral findById(long id){

		return  peripheralRepository.findById(id).get();
	}
	
	public List<Peripheral> searchPeripherals(Peripheral peripheral){
		return peripheralRepository.searchPeripherals(peripheral);
	}
	public void setPeripheralDAO(PeripheralRepository peripheralDAO) {
		this.peripheralRepository = peripheralDAO;
	}
}
