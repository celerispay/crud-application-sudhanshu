package com.crud.customerCRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.Transaction;
import com.crud.customerCRUD.Repository.CustomerRepository;
import com.crud.customerCRUD.Repository.TransactionRepository;
import com.crud.exception.BankDetailsNotFoundException;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
    	this.transactionRepository=transactionRepository;
    }
	
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();

	}

	public Transaction save(Transaction tr) {
		return transactionRepository.save(tr);

	}

	public Transaction findByTransactionId(String tId) {

		Transaction transaction;
		if (transactionRepository.findByTransactionId(tId) == null) {
			throw new BankDetailsNotFoundException("Bank Details not found");
		} else {
			transaction = transactionRepository.findByTransactionId(tId);
		}
		return transaction;

	}
	
	

}
