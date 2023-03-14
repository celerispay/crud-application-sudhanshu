package com.crud.customercrud.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crud.customercrud.entity.Customer;
import com.crud.customercrud.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
		.andExpect(status().isOk());
		
	}
		
}
