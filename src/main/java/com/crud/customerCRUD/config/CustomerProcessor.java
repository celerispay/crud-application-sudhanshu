package com.crud.customerCRUD.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.crud.customerCRUD.Entity.Customer;



@Configuration
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer customer) throws Exception {
		return customer;
	}

}
