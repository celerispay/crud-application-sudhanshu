package com.crud.customerCRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.BankDetails;
import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.BankDetailsRepository;
import com.crud.exception.BankDetailsNotFoundException;
import com.crud.exception.CustomerNotFoundException;

@Service
public class BankDetailsService {
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;
	
	
	public List<BankDetails> getAllBankDetails() throws BankDetailsNotFoundException {
		return bankDetailsRepository.findAll();
		
	}


public BankDetails findBankDetailByAccountNo(String accountNo) throws BankDetailsNotFoundException {
		
		BankDetails bankDetails;
        if (bankDetailsRepository.findByAccountNo(accountNo)==null) {
            throw new BankDetailsNotFoundException();
        } else {
        	bankDetails = bankDetailsRepository.findByAccountNo(accountNo);
        }
        return bankDetails;
		
	}




}
