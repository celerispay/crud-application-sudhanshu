package com.crud.customerCRUD.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Controller.CustomerController;
import com.crud.customerCRUD.Entity.BankDetails;
import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.BankDetailsRepository;
import com.crud.exception.BankDetailsNotFoundException;
import com.crud.exception.CustomerNotFoundException;

@Service
public class BankDetailsService {

	Logger logger = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;

	public BankDetailsService(BankDetailsRepository bankDetailsRepository) {
		this.bankDetailsRepository = bankDetailsRepository;
	}

	public List<BankDetails> getAllBankDetails() throws BankDetailsNotFoundException {
		return bankDetailsRepository.findAll();

	}

	public BankDetails createBankDetails(BankDetails bankDetails) throws BankDetailsNotFoundException {
		return bankDetailsRepository.save(bankDetails);
	}

	public BankDetails findBankDetailByAccountNo(String accountNo) throws BankDetailsNotFoundException {

		BankDetails bankDetails;
		if (bankDetailsRepository.findByAccountNo(accountNo) == null) {
			throw new BankDetailsNotFoundException("Bank details not found !");
		} else {
			bankDetails = bankDetailsRepository.findByAccountNo(accountNo);
		}
		return bankDetails;

	}

	public BankDetails updateBankDetails(String accountNo, BankDetails bankDetails) {
		BankDetails bdetails = bankDetailsRepository.findByAccountNo(accountNo);
		bdetails.setId(bankDetails.getId());
		bdetails.setAccountNo(bankDetails.getAccountNo());
		bdetails.setBankName(bankDetails.getBankName());
		bdetails.setAccType(bankDetails.getAccType());
		bdetails.setAvailableBalance(bankDetails.getAvailableBalance());
		logger.info("updateBankDetails method for updating BankDetails is called");
		return bankDetailsRepository.save(bdetails);
	}

	public String deleteBankDetailsByAccountNo(String accountNo) {
		bankDetailsRepository.deleteByAccountNo(accountNo);
		return "Bank Details with accountNo  " + accountNo + " is deleted Successfully";
	}

}
