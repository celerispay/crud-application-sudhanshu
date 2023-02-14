package com.crud.customerCRUD.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

	@Id
	private int custId;
	
	@Column(name = "custName")
	@NotBlank(message = "Name is mandatory")
	private String custName;
	
	@Column(name = "address")
	private String address;
	
	@NotNull(message="Contact number must be provided")
	@Column(name = "contactNo")
	private String contactNo;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "customer")
	private List<BankDetails> bankDetails;
	
	

}