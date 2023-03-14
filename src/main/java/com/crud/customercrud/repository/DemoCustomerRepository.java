package com.crud.customercrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.customercrud.entity.DemoCustomer;

@Repository
public interface DemoCustomerRepository extends JpaRepository<DemoCustomer, Integer> {  

	@EntityGraph(attributePaths = {"transaction","bankDetails"})                
	List<DemoCustomer> findAll();

}
