package com.crud.customerCRUD.Repository;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud.customerCRUD.Entity.Customer;

class CustomerRepositoryTest {

//	@Mock
//    private CustomerRepository customerRepository;

	@InjectMocks
	CustomerRepository customerRepository;
	
    private Customer customer;

    @BeforeEach
    public void setup(){
        customer = Customer.builder()
                .custId(100)
                .address("New York")
                .contactNo("7878787878")
                .customerName("Rahul")
                .build();
    }

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save Customer operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

//given - precondition or setup
        Customer customer = Customer.builder()
        		.custId(101)
                .address("kansas")
                .contactNo("8787878787")
                .customerName("Rojer")
                .build();
// when - action or the behaviour that we are going test
        Customer savedCustomer = customerRepository.save(customer);

        // then - verify the output
        
    }


    // JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList(){
        // given - precondition or setup

    	Customer customer1 = Customer.builder()
                .customerName("John")
                .custId(102)
                .address("Hyderabad")
                .contactNo("6565656565")
                .build();

        customerRepository.save(customer);
        customerRepository.save(customer1);

        // when -  action or the behaviour that we are going test
        List<Customer> employeeList = customerRepository.findAll();

        // then - verify the output
        assertEquals(employeeList.size(), 2);

    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){

    	customerRepository.save(customer);

        // when -  action or the behaviour that we are going test
    	Customer employeeDB = customerRepository.findById(customer.getCustId()).get();

        // then - verify the output
//        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){

    	customerRepository.save(customer);

        // when -  action or the behaviour that we are going test
//    	Customer savedEmployee = employeeRepository.findById(employee.getId()).get();
//        savedEmployee.setEmail("ram@gmail.com");
//        savedEmployee.setFirstName("Ram");
//        Customer updatedEmployee =  employeeRepository.save(savedEmployee);
//
//        // then - verify the output
//        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
//        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");
    }

    // JUnit test for delete employee operation
//    @DisplayName("JUnit test for delete employee operation")
//    @Test
//    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){
//
//    	customerRepository.save(employee);
//
//        // when -  action or the behaviour that we are going test
//    	customerRepository.deleteById(employee.getId());
//        Optional<Customer> employeeOptional = employeeRepository.findById(employee.getId());
//
//        // then - verify the output
//        assertThat(employeeOptional).isEmpty();
//    }
}
