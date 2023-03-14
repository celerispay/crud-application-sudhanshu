package com.crud.customercrud.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customercrud.entity.Customer;
import com.crud.customercrud.entity.Transaction;
import com.crud.customercrud.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/transaction")
@Log4j2
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Operation(summary = "Perform a new Transaction",description = "This API will perform a new Transaction",parameters = {})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Transaction done Successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "500", description = "Internal Server Error", 
			    content = @Content) })
	@PostMapping("/performtransaction")
	public Transaction performTransaction(@RequestBody @Valid Transaction transaction) {
		setupMDC("/transaction/performtransaction");
		log.debug("User has requested to perform transaction {} with data ",transaction);
		final Transaction tr = new Transaction();
		tr.setTransactionId(transaction.getTransactionId());
		tr.setTransactionDate(transaction.getTransactionDate());
		tr.setClientName(transaction.getClientName());
		tr.setTransactionType(transaction.getTransactionType());
		tr.setTransactionAmount(transaction.getTransactionAmount());
		
		return transactionService.save(tr);
		
	}
	
	 
	@Operation(summary = "Fetch All the Transaction Details",description = "This API will Fetch All the Transaction Details",parameters = {})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Transaction Details Found Successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "500", description = "Internal Server Error", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No Transaction Details found", 
			    content = @Content) })
	@GetMapping("/getAll")
	public List<Transaction> getAllTransaction() {
		setupMDC("/transaction/getAll");
		log.debug("User has requested to fetch all transaction data");
		return transactionService.getAllTransactions();
	}

	@Operation(summary = "Fetch the Transaction Details by Transaction Id",description = "This API will Fetch All the Transaction Details by providing Transaction Id ",parameters = {})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Transaction Details Found Successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "500", description = "Internal Server Error", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Transaction Details not found", 
			    content = @Content) })
	@GetMapping("/getTransactionById/{id}")
	public Transaction getTransactionById(@RequestBody Long id) {
		setupMDC("/transaction/getTransactionById/{id}");
		log.debug("User has requested to fetch transaction details with transaction id {} ",id);
		return transactionService.findByTransactionId(id);
	}
	
	private void setupMDC(String method) {
		MDC.clear();
		MDC.put("method", method);
		MDC.put("transactionId", UUID.randomUUID().toString());
	}
	
}
