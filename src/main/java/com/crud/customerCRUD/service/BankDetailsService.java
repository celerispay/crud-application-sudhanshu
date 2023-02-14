package com.crud.customerCRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.BankDetails;
import com.crud.customerCRUD.Repository.BankDetailsRepository;

@Service
public class BankDetailsService {
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;
	
	
	public List<BankDetails> getAllBankDetails(){
		return bankDetailsRepository.findAll();
		
	}


	public BankDetails findBankDetailByAccountNo(String accountNo) {
		return bankDetailsRepository.findByAccountNo(accountNo);
	}
	
	public void deleteBankDetailById(String accountNo) {
		bankDetailsRepository.deleteById(accountNo);
	}


}
