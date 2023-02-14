package com.crud.customerCRUD.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.customerCRUD.Entity.BankDetails;
import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.CustomerRepository;
import com.crud.customerCRUD.customException.BankDetailsNotFoundException;
import com.crud.customerCRUD.customException.CustomerNotFoundException;
import com.crud.customerCRUD.service.CustomerService;



@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/welcome")  
	public String welcome()   
	{  
	return "Hello User !!!! Welcome......";  
	}  

	@GetMapping("/customers/getAll")
	private List<Customer> getAllCustomers() {
		System.out.println("Get mapping is called....");
		List<Customer> lst = new ArrayList<>();
		lst = customerService.getAllEmployee();
		for(Customer cust: lst) {
			System.out.println(cust);
		}
		return  customerService.getAllEmployee();
	}

//	@GetMapping("/customers/{id}")
//	private ResponseEntity<Customer> getCustomerWithId(@PathVariable int id) {			
//		try {
//			System.out.println("Get mapping is called....");
//			Customer cst = customerService.findCustomerById(id);
//			return new ResponseEntity<Customer>(cst,HttpStatus.OK);
//		}
//		catch (CustomerNotFoundException e) {
//			throw new CustomerNotFoundException("The Customer info is not available:" + id);
//		}
//    }
		
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isEmpty())
			throw new CustomerNotFoundException();

		return customer.get();
	}

	@DeleteMapping("/customers/delete/{id}")
	private String deleteCustomer(@PathVariable int id) {
		System.out.println("Delete mapping is called....");
		customerRepository.deleteById(id);
		return "Customer with customer id "+id+" is deleted successfully...";
	}

	@PostMapping("/customers/create")
	private ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		System.out.println("Post mapping is called....");
		 Customer cst = customerRepository.save(customer);
		 return new ResponseEntity<Customer>(cst, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/customers/update/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Customer customer, @PathVariable int id) {

		Optional<Customer> customerOptional = customerRepository.findById(id);

		if (customerOptional.isEmpty())
			return ResponseEntity.notFound().build();

		customer.setCustId(id);
		
		customerRepository.save(customer);

		return ResponseEntity.noContent().build();
	}
}
