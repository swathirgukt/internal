package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.IncentiveRepository;
import com.indianeagle.internal.dto.Incentives;
import com.indianeagle.internal.form.IncentiveForm;
import com.indianeagle.internal.service.IncentiveService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;

/**
 * Service class for Incentives
 * @author kiran.paluvadi
 */
public class IncentiveServiceImpl implements IncentiveService {
	@Autowired
	private IncentiveRepository incentiveRepository;
	
	/**
	 * method to save Incentives
	 * @param incentiveForm 
	 */
	public void saveIncentives(IncentiveForm incentiveForm){
		List<Incentives> incentiveList = incentiveForm.getIncentivesList();
		for(Incentives incentives : incentiveList){
			incentives.setIncentiveDate(incentiveForm.getIncentiveDate());
		}
		incentiveRepository.saveAll(incentiveList);
	}


	/**
	 * method to search Incentives of Employees
	 * @param date
	 * @return List<Incentives>
	 */
	public List<Incentives> searchIncentives(Date date){
		return incentiveRepository.findByIncentiveDate(date);
	}

	/**
	 * @return the incentiveDAO
	 */
	public IncentiveRepository incentiveRepository() {
		return incentiveRepository;
	}

	/**
	 * @param incentiveRepository the incentiveRepository to set
	 */
	public void setIncentiveDAO(IncentiveRepository incentiveRepository) {
		this.incentiveRepository = incentiveRepository;
	}

}
