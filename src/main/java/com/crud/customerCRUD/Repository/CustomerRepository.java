package com.crud.customerCRUD.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.customerCRUD.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
