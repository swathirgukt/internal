package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.ChequeDetailsRepository;
import com.indianeagle.internal.dto.ChequeDetails;
import com.indianeagle.internal.form.ChequeDetailsForm;
import com.indianeagle.internal.mail.MailingEngine;
import com.indianeagle.internal.service.ChequeDetailsService;
import com.indianeagle.internal.util.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

/**
 * @author Unskrishna
 * service implementation for Cheque Details
 */
public class ChequeDetailsServiceImpl implements ChequeDetailsService {
	@Autowired
	private ChequeDetailsRepository chequeDetailsRepository;
	@Autowired
	private List<ChequeDetails> chequeDetailsList;
	@Autowired
	private MailingEngine mailingEngine;
	
	/**
	 * To save or update cheque details into database
	 */

	public void saveOrUpdateCheque(ChequeDetails chequeDetails) {
        chequeDetailsRepository.save(chequeDetails);
		
	}

	@Override
	public List<ChequeDetails> searchChequeDetails(ChequeDetailsForm chequeDetailsForm)
	{
		return chequeDetailsList = chequeDetailsRepository.findByChequeDateBetweenAndAmountOrderByChequeDate(chequeDetailsForm.getFromDate(),chequeDetailsForm.getToDate(),chequeDetailsForm.getAmount());
	}

	/**
	 * To get the cheque details
	 */

	/**
	 * @param chequeDetailsDAO the chequeDetailsDAO to set
	 */
	public void setChequeDetailsDAO(ChequeDetailsRepository chequeDetailsDAO) {
		this.chequeDetailsRepository = chequeDetailsDAO;
	}

	/**
	 * @return the chequeDetailsList
	 */
	public List<ChequeDetails> getChequeDetailsList() {
		return chequeDetailsList;
	}

	/**
	 * @param chequeDetailsList the chequeDetailsList to set
	 */
	public void setChequeDetailsList(List<ChequeDetails> chequeDetailsList) {
		this.chequeDetailsList = chequeDetailsList;
	}

	/**
	 * find cheque details by id
	 */
	public Optional<ChequeDetails> findBy(long chequeDetails) {
		return chequeDetailsRepository.findById(chequeDetails);
	}

	/**
	 * Get tomorrowDate Cheque Details and call mailChequeDetails
	 */
	public void sendChequeDetailsMail(){
		List<ChequeDetails> chequeDetails = chequeDetailsRepository.findByChequeDate(SimpleUtils.tomorrowDate());
		if(chequeDetails.size() > 0){
			mailingEngine.mailChequeDetails(chequeDetails);
		}
	}

	/**
	 * @return the mailingEngine
	 */
	public MailingEngine getMailingEngine() {

		return mailingEngine;
	}

	/**
	 * @param mailingEngine the mailingEngine to set
	 */
	public void setMailingEngine(MailingEngine mailingEngine) {
		this.mailingEngine = mailingEngine;
	}
	
}
