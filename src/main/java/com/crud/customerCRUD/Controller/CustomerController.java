package com.crud.customerCRUD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.CustomerRepository;



@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@RequestMapping("/")  
	public String Welcome()   
	{  
	return "Hello User !!!! Welcome......";  
	}  

	@GetMapping("/customers/getAll")
	private List getAllCustomers() {
		System.out.println("Get mapping is called....");
		return (List) customerRepository.findAll();
	}

	@GetMapping("/customers/{id}")
	private Customer getCustomerWithId(@PathVariable int id) {
		return customerRepository.findById(id);
	}

	@DeleteMapping("/customers/delete/{id}")
	private String deleteCustomer(@PathVariable int id) {
		System.out.println("Delete mapping is called....");
		customerRepository.deleteById(id);
		return "Customer with customer id "+id+" is deleted successfully...";
	}

	@PostMapping("/customers/create")
	private Customer createCustomer(@RequestBody Customer customer) {
		System.out.println("Post mapping is called....");
		return customerRepository.save(customer);
	}

	@PutMapping("/customers/update/{id}")
	private String update(@RequestBody Customer customer, @PathVariable int id) {
		Customer currCust = customerRepository.findById(id);
		currCust.setCustName(customer.getCustName());
		currCust.setAddress(customer.getAddress());
		currCust.setContactNo(customer.getContactNo());
		System.out.println("Put mapping is called....");
		return "Customer with customer id " + id + " is updated Successfully!!!!!";
	}
}