package com.crud.customerCRUD.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.customerCRUD.Entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	List<Customer> findAll();

	Customer findById(int id);
}
