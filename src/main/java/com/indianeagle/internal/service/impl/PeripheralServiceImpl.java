package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.PeripheralRepository;
import com.indianeagle.internal.dto.Peripheral;
import com.indianeagle.internal.service.PeripheralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author SVK
 */

@Service
public class PeripheralServiceImpl implements PeripheralService {
	@Autowired
	private PeripheralRepository peripheralRepository;

	public void saveOrUpdate(Peripheral peripheral){
		peripheralRepository.save(peripheral);
	}
	
	@SuppressWarnings("unchecked")
	public Peripheral findById(long id){
		Optional<Peripheral> optional = peripheralRepository.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	
	public List<Peripheral> searchPeripherals(Peripheral peripheral){
		return peripheralRepository.searchPeripherals(peripheral);
	}
	public void setPeripheralDAO(PeripheralRepository peripheralDAO) {
		this.peripheralRepository = peripheralDAO;
	}
}
