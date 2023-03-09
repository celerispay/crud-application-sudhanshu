package com.crud.customer_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customer_crud.entity.BankDetails;
import com.crud.customer_crud.exception.NotFoundException;
import com.crud.customer_crud.repository.BankDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BankDetailsService {
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;

	public BankDetailsService(BankDetailsRepository bankDetailsRepository) {
		this.bankDetailsRepository = bankDetailsRepository;
	}

	public List<BankDetails> getAllBankDetails() throws NotFoundException {
		return bankDetailsRepository.findAll();

	}

	public BankDetails createBankDetails(BankDetails bankDetails) throws NotFoundException {
		return bankDetailsRepository.save(bankDetails);
	}

	public BankDetails findBankDetailByAccountNo(String accountNo) throws NotFoundException {

		BankDetails bankDetails;
		if (bankDetailsRepository.findByAccountNo(accountNo) == null) {
			throw new NotFoundException("Bank details not found !");
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
		log.info("updateBankDetails method for updating BankDetails is called");
		return bankDetailsRepository.save(bdetails);
	}

	public String deleteBankDetailsByAccountNo(String accountNo) {
		bankDetailsRepository.deleteByAccountNo(accountNo);
		return "Bank Details with accountNo  " + accountNo + " is deleted Successfully";
	}

}
