package com.crud.customercrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customercrud.entity.Customer;
import com.crud.customercrud.repository.CustomerRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAll() {
		log.info("GetAll method for getting all Customers is called");
		return customerRepository.findAll();
	}

	public Customer findCustomerById(int id) throws Exception {
		log.info("findCustomerById method for getting Customers with id {} is called", id);
		Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(id).orElseThrow(() -> new Exception("No data found with Id - " + id)));
		return customer.get();
	}

	public Customer saveCustomer(final Customer customerData) {
		log.info("saveCustomer method for saving Customers is called");
		Customer customerModel = populateCustomerData(customerData);
		return customerRepository.save(customerModel);
	}

	private Customer populateCustomerData(final Customer customerData) {
		log.info("Populate Customer Data method called for populating Customers data ");
		Customer customer = new Customer();
		customer.setCustId(customerData.getCustId());
		customer.setCustomerName(customerData.getCustomerName());
		customer.setAddress(customerData.getAddress());
		customer.setContactNo(customerData.getContactNo());
		return customer;
	}

	public Customer updateCustomer(int custId, Customer customerData) {
		log.info("updateCustomer method for updating Customers is called");
		Customer customer = customerRepository.findById(custId).get();
		customer.setCustId(customerData.getCustId());
		customer.setCustomerName(customerData.getCustomerName());
		customer.setAddress(customerData.getAddress());
		customer.setContactNo(customerData.getContactNo());
		return customerRepository.save(customer);
	}

	public String deleteCustomerById(int id) {
		log.info("DeleteCustomer method for called to delete Customers with id {}", id);
		customerRepository.deleteById(id);
		return "Customer with customer Id " + id + " is deleted Successfully";
	}
}
