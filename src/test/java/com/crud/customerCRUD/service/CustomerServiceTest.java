package com.crud.customerCRUD.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import com.crud.customerCRUD.Entity.BankDetails;
import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.Entity.Transaction;
import com.crud.customerCRUD.Repository.CustomerRepository;
import com.crud.exception.CustomerNotFoundException;

@DisplayName("This Test class will test all the Customer Service")
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@InjectMocks
	CustomerService service;

	@Mock
	CustomerRepository repo;

	@Mock
	Logger logger;

	private Customer customer;

	@BeforeEach
	public void setup() {
		customer = Customer.builder().custId(100).customerName("Athrav").address("New Delhi").contactNo("9123456789")
				.build();
	}

	@DisplayName("JUnit test for saveCustomer() method")
	@Test
	public void testSaveCustomer() {
		Mockito.when(repo.save(Mockito.any(Customer.class))).thenReturn(customer);

		Customer savedCustomer = service.saveCustomer(customer);

		System.out.println(savedCustomer);

		assertThat(savedCustomer).isNotNull();

	}

	@DisplayName("JUnit test for getAll() method")
	@Test
	public void testGetAll() {
		Customer customer1 = Customer.builder().custId(101).customerName("Tony").address("Goa").contactNo("7890563420")
				.build();

		// when
		Mockito.when(repo.findAll()).thenReturn(List.of(customer, customer1));

		List<Customer> customers = service.getAll();

		// then - verify the output
		assertThat(customers).isNotNull();
		assertThat(customers.size()).isEqualTo(2);
	}

	@DisplayName("JUnit test for getCustomerById method")
	@Test
	public void testFindCustomerById() {
		// when
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(customer));

		Customer savedCustomer = service.findCustomerById(customer.getCustId());

		// then
		assertThat(savedCustomer).isNotNull();

	}

	@DisplayName("JUnit test for findCustomerById method which throws exception")
	@Test
	void testFindCustomerByIdThrowsException() {

		CustomerNotFoundException customerNotFoundException =assertThrows(CustomerNotFoundException.class,
				() -> service.findCustomerById(100));

		assertEquals("Customer not found !", customerNotFoundException.getMessage());
	}

	
	@DisplayName("JUnit test for updateCustomer method")
	@Test
	public void testUpdateCustomer() {
		Customer c = Customer.builder().custId(100).contactNo("7878787878").address("Chikago").customerName("Ram")
				.build();

		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(customer));
		Mockito.when(repo.save(Mockito.any(Customer.class))).thenReturn(customer);

		Customer updatedCustomer = service.updateCustomer(customer.getCustId(), c);
		System.out.println(customer);
		System.out.println(updatedCustomer);

		assertThat(updatedCustomer.getAddress()).isEqualTo("Chikago");
		assertThat(updatedCustomer.getCustomerName()).isEqualTo("Ram");
	}

	@DisplayName("JUnit test for deleteCustomer method")
	@Test
	public void testDeleteCustomerById() {

		int custId = 100;

		willDoNothing().given(repo).deleteById(custId);

		service.deleteCustomerById(custId);

		verify(repo, times(1)).deleteById(custId);
	}

}
