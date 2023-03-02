package com.crud.customerCRUD.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.BankDetailsRepository;
import com.crud.customerCRUD.Repository.CustomerRepository;
import com.crud.exception.CustomerNotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomerService {

	

	
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
    	this.customerRepository=customerRepository;
    }
	
	public List<Customer> getAll() {
		List<Customer> custList = customerRepository.findAll();
		log.info("GetAll method for getting all Customers is called");
		return custList;
	}

	public Customer findCustomerById(int id) throws CustomerNotFoundException {

		Customer customer;
		if (customerRepository.findById(id).isEmpty()) {
			throw new CustomerNotFoundException("Customer not found !");
		} else {
			customer = customerRepository.findById(id).get();
		}
		log.info("findCustomerById method for getting Customers with id "+id+" is called");
		return customer;

	}

	public Customer saveCustomer(final Customer customerData) {
		Customer customerModel = populateCustomerData(customerData);
		log.info("saveCustomer method for saving Customers is called");
		return customerRepository.save(customerModel);
	}

	private Customer populateCustomerData(final Customer customerData) {
		Customer customer = new Customer();
		customer.setCustId(customerData.getCustId());
		customer.setCustomerName(customerData.getCustomerName());
		customer.setAddress(customerData.getAddress());
		customer.setContactNo(customerData.getContactNo());
		return customer;
	}
	
	public Customer updateCustomer(int custId, Customer customerData) {
		Customer customer = customerRepository.findById(custId).get();
		customer.setCustId(customerData.getCustId());
		customer.setCustomerName(customerData.getCustomerName());
		customer.setAddress(customerData.getAddress());
		customer.setContactNo(customerData.getContactNo());
		log.info("updateCustomer method for updating Customers is called");
		return customerRepository.save(customer);
	}

	public String deleteCustomerById(int id) {
		customerRepository.deleteById(id);
		return "Customer with customer Id " + id + " is deleted Successfully";
	}
}
