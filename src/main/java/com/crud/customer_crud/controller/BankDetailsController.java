package com.crud.customer_crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customer_crud.entity.BankDetails;
import com.crud.customer_crud.entity.Customer;
import com.crud.customer_crud.service.BankDetailsService;
import com.crud.exception.BankDetailsAlreadyExistsException;
import com.crud.exception.BankDetailsNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/bankdetails")
public class BankDetailsController {
	
	@Autowired
	private BankDetailsService bankDetailsService;

	@Operation(summary = "Fetch All the Bank Details",description = "This API will Fetch All the Bank Details",parameters = {})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Bank Details Found Successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "500", description = "Internal Server Error", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Bank Details not found", 
			    content = @Content) })
	@GetMapping("/getAll")
	public List<BankDetails> getAllBankDetails() {
		setupMDC("/bankdetails/getAll");
		log.debug("User has requested to fetch all bank details");
		List<BankDetails> lst = bankDetailsService.getAllBankDetails();
		return lst;
	}

	@Operation(summary = "Fetch the Bank Details by Account Number",description = "This API will Fetch the Bank Details by Account NUmber",parameters = {})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Bank Details Found Successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "500", description = "Internal Server Error", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Bank Details not found", 
			    content = @Content) })
	@GetMapping("/getByAccountNo/{accountNo}")
	public BankDetails getBankDetailsByAccountNo(@PathVariable @Valid String accountNo) throws BankDetailsNotFoundException{
		setupMDC("/bankdetails/getByAccountNo");
		log.debug("User has requested to fetch bank details of account {} :", accountNo);
		try {
			BankDetails bd = bankDetailsService.findBankDetailByAccountNo(accountNo);
			if(bd==null) throw new BankDetailsNotFoundException("Bank Account with "+accountNo+" not found");
			else return bd;
		}
		catch (BankDetailsNotFoundException e) {
			throw new BankDetailsNotFoundException("Bank Details not found for this account number");
		}
	}

	
	@Operation(summary = "Delete the Bank Details by Account Number",description = "This API will the Bank Details by ACcount Number",parameters = {})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Bank Details Deleted Successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "500", description = "Internal Server Error", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Bank Details not found", 
			    content = @Content) })
	@DeleteMapping("/deleteByAccountNo/{accountNo}")
	public String deleteBankDetails(@PathVariable @Valid String accountNo) throws BankDetailsNotFoundException{
		setupMDC("/bankdetails/deleteByAccountNo");
		log.debug("User has requested to delete bank details of account {} :", accountNo);
		bankDetailsService.deleteBankDetailsByAccountNo(accountNo);
		return "Customer with Bank Account number "+accountNo+" is deleted successfully...";
	}

	
	@Operation(summary = "Create a Bank Details",description = "This API will Create a Bank Details",parameters = {})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Bank Details created Successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "500", description = "Internal Server Error", 
			    content = @Content) })
	@PostMapping("/create")
	public ResponseEntity<BankDetails> createBankDetails(@RequestBody @Valid BankDetails bankDetails) throws BankDetailsAlreadyExistsException{
		setupMDC("/bankdetails/create");
		log.debug("User has requested to create bank details with data :", bankDetails);
		BankDetails bd = bankDetailsService.createBankDetails(bankDetails);
		return new ResponseEntity<BankDetails>(bd, HttpStatus.CREATED);
	}

	
	@Operation(summary = "Update an Existing Bank Details data",description = "This API will create a new Bank Details",parameters = {})
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Bank Details Created Successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "400", description = "Invalid data supplied", 
			    content = @Content), 
			  @ApiResponse(responseCode = "500", description = "Internal Server Error", 
			    content = @Content)})
	@PutMapping("update/{accountNo}")
	public String update(@RequestBody @Valid BankDetails bankDetails, @PathVariable String accountNo) {
		setupMDC("/bankdetails/update");
		log.debug("User has requested to update bank details of account Number {} :", accountNo);
		BankDetails currDetail = bankDetailsService.findBankDetailByAccountNo(accountNo);
		currDetail.setAccountNo(bankDetails.getAccountNo());
		currDetail.setBankName(bankDetails.getAccountNo());
		currDetail.setAccType(bankDetails.getAccType());
		currDetail.setCustomer(bankDetails.getCustomer());
		return "Bank Details with Account number " + accountNo + " is updated Successfully!!!!!";
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
	
	/**
     * Setup MDC variables.
     *
     * @param method the api endpoint that was called
     */
	
	private void setupMDC(String method) {
        MDC.clear();
        MDC.put("method", method);
        MDC.put("transactionId", UUID.randomUUID().toString());
    }
}
