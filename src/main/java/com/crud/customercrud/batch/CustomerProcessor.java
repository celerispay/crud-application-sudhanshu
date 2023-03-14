package com.crud.customercrud.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.crud.customercrud.entity.Customer;
import com.crud.customercrud.entity.DemoCustomer;

import lombok.extern.log4j.Log4j2;



@Configuration
@Log4j2
public class CustomerProcessor implements ItemProcessor<Customer, DemoCustomer> {

	public DemoCustomer process(Customer cst) throws Exception {
		log.info("MyBatchProcessor : Processing data : "+cst);
		DemoCustomer demoCustomer = new DemoCustomer();
		demoCustomer.setCustomerName(cst.getCustomerName());
		demoCustomer.setAddress(cst.getAddress());
		demoCustomer.setContactNo(cst.getContactNo());
		return demoCustomer;
	}
}
