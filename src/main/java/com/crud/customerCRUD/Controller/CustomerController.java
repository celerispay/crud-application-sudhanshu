package com.crud.customerCRUD.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.CustomerRepository;
import com.crud.customerCRUD.service.CustomerService;
import com.crud.exception.CustomerAlreadyExistsException;
import com.crud.exception.CustomerNotFoundException;
import lombok.extern.log4j.Log4j2;



@RestController
@Log4j2
@RequestMapping("/customers/")
public class CustomerController {
	Logger logger = LogManager.getLogger(CustomerRepository.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;  

	@GetMapping("getAll")
	private List<Customer> getAllCustomers() throws CustomerNotFoundException{
		logger.debug("User has requested to get all the customer details");
		List<Customer> lst = new ArrayList<>();
		lst = customerService.getAllEmployee();
		for(Customer cust: lst) {
			System.out.println(cust);
		}
		return  customerService.getAllEmployee();
	}
		
	@GetMapping("getById/{id}")
	public Customer getCustomer(@PathVariable int id) throws CustomerNotFoundException{
		logger.debug("User has requested to get the customer details of Customer having customer id : "+id);
		return customerService.findCustomerById(id);
	}

	@DeleteMapping("delete/{id}")
	private String deleteCustomer(@PathVariable int id) throws CustomerNotFoundException{
		logger.debug("User has requested to delete the customer details of Customer having customer id : "+id);
		customerRepository.deleteById(id);
		return "Customer with customer id "+id+" is deleted successfully...";
	}

	@PostMapping("create")
	private ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws CustomerAlreadyExistsException{
		logger.debug("User has requested to Create the customer with details : "+customer);
		 Customer cst = customerRepository.save(customer);
		 return new ResponseEntity<Customer>(cst, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Customer customer, @PathVariable int id) throws CustomerNotFoundException, CustomerAlreadyExistsException{
		logger.debug("User has requested to update the customer having id "+id +" with details : "+customer);
		Optional<Customer> customerOptional = customerRepository.findById(id);

		if (customerOptional.isEmpty())
			return ResponseEntity.notFound().build();

		customer.setCustId(id);
		
		customerRepository.save(customer);

		return ResponseEntity.noContent().build();
	}
}
