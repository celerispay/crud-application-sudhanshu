package com.crud.customerCRUD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customerCRUD.Entity.Transaction;
import com.crud.customerCRUD.Entity.User;
import com.crud.customerCRUD.service.TransactionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("performtransaction")
	public Transaction performTransaction(@RequestBody Transaction transaction) {
		final Transaction tr = new Transaction();
		tr.setTransactionId(transaction.getTransactionId());
		tr.setTransactionDate(transaction.getTransactionDate());
		tr.setClientName(transaction.getClientName());
		tr.setTransactionType(transaction.getTransactionType());
		tr.setTransactionAmount(transaction.getTransactionAmount());
		return transactionService.save(tr);
	}
	
	 

	@GetMapping("getAll")
	public List<Transaction> getAllTransaction() {
		return transactionService.getAllTransactions();
	}

	@GetMapping("{id}")
	public Transaction getTransactionById(@RequestBody Long id) {
		return transactionService.findByTransactionId(id);
	}

	@GetMapping("getByAmount/{amount}")
	public Transaction getTransactionByTransactionAmount(@RequestBody Long amount) {
		return transactionService.getByTransactionAmount(amount);
	}
}
