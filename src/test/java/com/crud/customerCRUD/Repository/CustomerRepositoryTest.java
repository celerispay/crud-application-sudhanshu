//package com.crud.customerCRUD.Repository;
//
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.activation.DataSource;
//import javax.persistence.EntityManager;
//
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import com.crud.customerCRUD.Entity.Customer;
//
////@ExtendWith(SpringExtension.class)
//@DataJpaTest
//class CustomerRepositoryTest {
//	
//	@Autowired
//	CustomerRepository customerRepository;
//	
//    private Customer customer;
//
//    @BeforeEach
//    void setup(){
//        customer = Customer.builder()
//                .custId(100)
//                .address("New York")
//                .contactNo("7878787878")
//                .customerName("Rahul")
//                .build();
//    }
//
//    @Test
//    void testFindById() {
//       Customer customer = Customer.builder()
//       		.custId(101)
//            .address("Mumbai")
//            .contactNo("8787878787")
//            .customerName("Rojer")
//            .build();     
//       customerRepository.save(customer);
//       Customer result = customerRepository.findById(customer.getCustId()).get();
//       assertEquals(customer.getCustId(), result.getCustId());	     
//    }
//    
//    @Test
//    public void testFindAll() {
//    	Customer employee = Customer.builder()
//        		.custId(101)
//                .address("Mumbai")
//                .contactNo("8787878787")
//                .customerName("Rojer")
//                .build();
//       customerRepository.save(employee);
//       List<Customer> result = new ArrayList<>();
//       customerRepository.findAll().forEach(e -> result.add(e));
//       assertEquals(result.size(), 1);	     
//    }
//    
//    @Test
//    void testSave() {
//    	Customer employee = Customer.builder()
//        		.custId(101)
//                .address("Mumbai")
//                .contactNo("8787878787")
//                .customerName("Rojer")
//                .build();
//       customerRepository.save(employee);
//       Customer found = customerRepository.findById(employee.getCustId()).get();
//       assertEquals(employee.getCustId(), found.getCustId());	     
//    }
//    
//    @Test
//    void testDeleteById() {
//    	Customer employee = Customer.builder()
//        		.custId(101)
//                .address("Mumbai")
//                .contactNo("8787878787")
//                .customerName("Rojer")
//                .build();
//       customerRepository.save(employee);
//       customerRepository.deleteById(employee.getCustId());
//       List<Customer> result = new ArrayList<>();
//       customerRepository.findAll().forEach(e -> result.add(e));
//       assertEquals(result.size(), 0);
//    }
//    
//}
