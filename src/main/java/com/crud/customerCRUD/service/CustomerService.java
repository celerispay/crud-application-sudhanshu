package com.crud.customerCRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Repository.CustomerRepository;
import com.crud.customerCRUD.customException.CustomerNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
//	public Customer addEmployee(Customer customer){
//		try {
//			if(customer.getCustName().isEmpty()) {
//				throw new CustomerNotFoundException("","Please enter proper Name details"); 
//			}
//			Customer cust = customerRepository.save(customer);
//			return cust;
//		}
//		catch (IllegalArgumentException e) {
//			throw new CustomerNotFoundException("306","Please enter proper Name details"); 
//			
//		}
//		catch (Exception e) {
//			throw new CustomerNotFoundException("305","Something went wrong in service layer.."); 
//			
//		}
		
		
//	}
	
	public List<Customer> getAllEmployee(){
		List<Customer> custList = customerRepository.findAll();
		return custList;
	}
//
//
//	public Customer findCustomerById(int id) {
//		
//		try {
//			Customer cust = customerRepository.findById(id);
//			if(cust.getCustName().length()==0) {
//				throw new CustomerNotFoundException("305", "No Customer is Found ...");
//			}
//			return cust;
//		}
//		catch (IllegalArgumentException e) {
//			throw new CustomerNotFoundException("307", "No customer is present in the DB...");
//		}
//		catch (java.util.NoSuchElementException e) {
//			throw new CustomerNotFoundException("306", "Something went wrong in the service layer"+" "+e.getMessage());
//		} 
//		
//		
//	}
//	
//	public void deleteCustomerById(int id) {
//		
//		try {
//			Customer cust = customerRepository.findById(id);
//			if(cust.getCustName().length()==0) {
//				throw new CustomerNotFoundException("305", "No Record Found...");
//			}
//			customerRepository.deleteById(id);
//		}
//		catch (java.util.NoSuchElementException e) {
//			throw new CustomerNotFoundException("306", "Something went wrong in the service layer"+" "+e.getMessage());
//		}
//	}


}
