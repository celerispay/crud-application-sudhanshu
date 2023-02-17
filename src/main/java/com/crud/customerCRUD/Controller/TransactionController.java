package com.crud.customerCRUD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customerCRUD.Entity.Transaction;
import com.crud.customerCRUD.service.TransactionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/performtransaction")
	public Transaction performTransaction(@RequestBody Transaction transaction) {
		Transaction tr = new Transaction();
		tr.setTransactionId(transaction.getTransactionId());
		tr.setTransactionAmount(transaction.getTransactionAmount());
		return transactionService.save(tr);
	}
	
	@GetMapping("/transaction")
	public List<Transaction> getAllTransaction() {
		return transactionService.getAllTransactions();
	}

	@GetMapping("/transaction/{id}")
	public Transaction getTransactionById(@RequestBody Long id) {
		return transactionService.findByTransactionId(id);
	}

	@GetMapping("/transactionAmmount/{amount}")
	public Transaction getTransactionByTransactionAmount(@RequestBody Long amount) {
		return transactionService.getByTransactionAmount(amount);
	}
}
