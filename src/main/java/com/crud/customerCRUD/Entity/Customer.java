package com.crud.customerCRUD.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int custId;
	
	@Column(name = "customer_name")
	@NotBlank(message = "Name is mandatory")
	private String customerName;
	
	@Column(name = "address")
	private String address;
	
	@NotNull(message="Contact number must be provided")
	@Column(name = "contactNo")
	private String contactNo;

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "customer")
	private List<BankDetails> bankDetails;
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
	private Transaction transaction;


}