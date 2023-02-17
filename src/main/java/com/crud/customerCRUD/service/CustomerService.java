package com.crud.customerCRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.CustomerRepository;
import com.crud.exception.CustomerNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	

	
	public List<Customer> getAllEmployee(){
		List<Customer> custList = customerRepository.findAll();
		return custList;
	}

	public Customer findCustomerById(int id) throws CustomerNotFoundException {
		
		Customer customer;
        if (customerRepository.findById(id).isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            customer = customerRepository.findById(id).get();
        }
        return customer;
		
	}
	
	
}
