package com.crud.customercrud.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crud.customercrud.entity.Customer;

public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		   customer.setCustomerName(rs.getString("customer_name"));
		   customer.setAddress(rs.getString("address"));
		   customer.setContactNo(rs.getString("contact_no"));
		   return customer;
	}

}
