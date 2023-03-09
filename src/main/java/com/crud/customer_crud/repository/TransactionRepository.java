package com.crud.customer_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.customer_crud.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	Transaction findTransactionByTransactionId(String transactionId);
	
	Transaction findByTransactionAmount(long amount);
}
