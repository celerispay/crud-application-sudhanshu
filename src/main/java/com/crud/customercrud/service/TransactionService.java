package com.crud.customercrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customercrud.entity.Transaction;
import com.crud.customercrud.repository.TransactionRepository;

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

	public Transaction findByTransactionId(Long tId) {
		return transactionRepository.findTransactionByTransactionId(tId);

	}
	
	

}
