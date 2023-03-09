package com.crud.customer_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customer_crud.Repository.CustomerRepository;
import com.crud.customer_crud.Repository.TransactionRepository;
import com.crud.customer_crud.entity.Transaction;
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
		return transactionRepository.findTransactionByTransactionId(tId);

	}
	
	

}
