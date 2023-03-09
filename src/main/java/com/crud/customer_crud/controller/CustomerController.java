package com.crud.customer_crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import com.crud.customer_crud.entity.Customer;
import com.crud.customer_crud.exception.AlreadyExistException;
import com.crud.customer_crud.exception.NotFoundException;
import com.crud.customer_crud.service.CustomerService;

import io.swagger.annotations.ResponseHeader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Operation(summary = "Fetch All the Customers", description = "This API will Return the list of all the Customers", parameters = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer List Found Successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "404", description = "No Customer found", content = @Content) })
	@GetMapping("/getAll")
	@ResponseHeader
	@ResponseStatus(HttpStatus.OK)
	private List<Customer> getAllCustomers() throws NotFoundException {
		setupMDC("/customers/getAll");
		log.info("CustomerController.getAllCustomers() called by User");
		List<Customer> lst = new ArrayList<>();
		lst = customerService.getAll();
		return lst;
	}

	@Operation(summary = "Fetch the Customer with ID", description = "This API will Return the Customer with Specific customer Id", parameters = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "404", description = "Customer not found", content = @Content) })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/getById/{id}")
	public Customer getCustomerById(@PathVariable int id) throws Exception {
		setupMDC("/customers/getById/{id}");
		log.debug("User has requested to get the customer details of Customer having customer id : {} ", id);
		return customerService.findCustomerById(id);
	}

	@Operation(summary = "Delete the Customer with ID", description = "This API will delete the Customer with Specific customer Id", parameters = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Deleted Successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "404", description = "Customer not found", content = @Content) })
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable int id) throws NotFoundException {
		setupMDC("/customers/delete/{id}");
		log.debug("User has requested to delete the customer details of Customer having customer id : {} ", id);
		customerService.deleteCustomerById(id);
		return "Customer with customer id " + id + " is deleted successfully...";
	}

	@Operation(summary = "Create a new Customer", description = "This API will create a new Customer", parameters = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Created Successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer)
			throws AlreadyExistException {
		setupMDC("/customers/create");
		log.debug("User has requested to Create the customer with details : {} ", customer);
		Customer cst = customerService.saveCustomer(customer);
		return new ResponseEntity<Customer>(cst, HttpStatus.CREATED);
	}

	@Operation(summary = "Update the existing Customer", description = "This API will update the existing Customer data", parameters = {})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Updated Successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),
			@ApiResponse(responseCode = "404", description = "Customer data not found", content = @Content) })

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody @Valid Customer customer)
			throws Exception {
		setupMDC("/customers/update/{id}");
		log.debug("User has requested to update the customer having id: {} ", id);
		Optional<Customer> customerOptional = Optional.of(customerService.findCustomerById(id));
		return new ResponseEntity<>(customerService.updateCustomer(id, customer), HttpStatus.OK);
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
