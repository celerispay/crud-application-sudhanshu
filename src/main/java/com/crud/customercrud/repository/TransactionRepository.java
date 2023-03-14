package com.crud.customercrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.customercrud.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	Transaction findTransactionByTransactionId(Long transactionId);
	
	Transaction findByTransactionAmount(long amount);
}
