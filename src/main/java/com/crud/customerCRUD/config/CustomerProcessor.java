package com.crud.customerCRUD.config;

import org.springframework.batch.item.ItemProcessor;

import com.crud.customerCRUD.Entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

	private static final Logger log = LoggerFactory.getLogger(CustomerProcessor.class);

	@Override
	public Customer process(Customer customer) throws Exception {
		return customer;
	}

}
