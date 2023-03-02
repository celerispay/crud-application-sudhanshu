package com.crud.customerCRUD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.customerCRUD.Entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	Transaction findByTransactionId(String transactionId);
	
	Transaction findByTransactionAmount(long amount);
}
