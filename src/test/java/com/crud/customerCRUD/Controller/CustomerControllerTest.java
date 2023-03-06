package com.crud.customerCRUD.Controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.internal.creation.bytebuddy.ByteBuddyCrossClassLoaderSerializationSupport.MockitoMockObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crud.customerCRUD.Entity.Customer;
import com.crud.customerCRUD.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
//@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CustomerService service;

	@InjectMocks
	CustomerController customerController;

	private ObjectMapper objectMapper;

	private Customer customer;
	private Customer customer1;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		objectMapper = new ObjectMapper();
		customer = Customer.builder().custId(100).customerName("Tony").address("Goa").contactNo("7890563420").build();
		customer1 = Customer.builder().custId(101).customerName("Athrav").address("New Delhi").contactNo("9123456789")
				.build();

	}

	@Test
	void testCreateCustomer() throws Exception {
		when(service.findCustomerById(Mockito.anyInt())).thenReturn(customer);
		when(service.saveCustomer(Mockito.any(Customer.class))).thenReturn(customer);
		
		this.mockMvc.perform(post("/customers/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.customerName", is(customer.getCustomerName())))
		.andExpect(jsonPath("$.address", is(customer.getAddress())))
		.andExpect(jsonPath("$.contactNo", is(customer.getContactNo().toString())));
			
	}

	@Test
	void testgetAllCustomers() throws Exception {

		List<Customer> list = new ArrayList<>();
		list.add(customer);
		list.add(customer1);

		when(service.getAll()).thenReturn(list);

		this.mockMvc.perform(get("/customers/getAll")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(list.size())));
	}

	@Test
	void testGetCustomerById() throws Exception {
		
		when(service.findCustomerById(Mockito.anyInt())).thenReturn(customer);
		
		this.mockMvc.perform(get("/customers/getById/{id}", 101))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.customerName", is(customer.getCustomerName())))
			.andExpect(jsonPath("$.contactNo", is(customer.getContactNo())));
	}

	@Test
	void testDeleteCustomer() throws Exception {
		
		when(service.deleteCustomerById(Mockito.anyInt())).thenReturn(Mockito.anyString());
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/customers/delete/{id}", 100))
        .andExpect(status().isOk());	
	}

	@Test
	void testUpdateCustomer() throws Exception {
		System.out.println(customer);
		when(service.findCustomerById( Mockito.anyInt())).thenReturn(customer);
		when(service.updateCustomer( Mockito.anyInt(),Mockito.any(Customer.class))).thenReturn(customer);		
		this.mockMvc.perform(put("/customers/update/{id}", 100)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer1)))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.custId").value(customer1.getCustId()));
//      .andExpect(MockMvcResultMatchers.jsonPath("$.customerName", is(customer1.getCustomerName())))
//      .andExpect(MockMvcResultMatchers.jsonPath("$.address", is(customer1.getAddress())))
//      .andExpect(MockMvcResultMatchers.jsonPath("$.contactNo", is(customer1.getContactNo())));
//		.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName2"))
//	      .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName2"))
//	      .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email2@mail.com"));
		System.out.println(customer);
	}
		
}
