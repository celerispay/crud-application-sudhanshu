package com.crud.customer_crud.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.crud.customer_crud.entity.Customer;
import com.crud.customer_crud.entity.DemoCustomer;



@Configuration
public class CustomerProcessor implements ItemProcessor<Customer, DemoCustomer> {

//	@Override
//	public Customer process(Customer item) throws Exception {
//		return new Customer();
//	}

	public DemoCustomer process(Customer cst) throws Exception {
		System.out.println("MyBatchProcessor : Processing data : "+cst);
		DemoCustomer demoCustomer = new DemoCustomer();
		demoCustomer.setCustomerName(cst.getCustomerName());
		demoCustomer.setAddress(cst.getAddress());
		demoCustomer.setContactNo(cst.getContactNo());
		return demoCustomer;
	}
}
