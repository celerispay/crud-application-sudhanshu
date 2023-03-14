package com.crud.customercrud.entity;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int custId;
	
	@Column(name = "customer_name")
	@NotNull
	@Size(min = 5, max =20)
	@NotBlank(message = "Name is mandatory")
	private String customerName;
	
	@NotNull
	@Size(min = 5, max =25)
	@Column(name = "address")
	private String address;
	
	@NotEmpty(message = "Phone number is required")
    @Pattern(regexp="\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}",
             message="Mobile number is invalid")
	@Column(name = "contactNo", unique=true)
	private String contactNo;

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "customer")
	private List<BankDetails> bankDetails;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
	private Transaction transaction;



	public Customer(int custId, @NotBlank(message = "Name is mandatory") String customerName, String address,
			@NotNull(message = "Contact number must be provided") String contactNo) {
		super();
		this.custId = custId;
		this.customerName = customerName;
		this.address = address;
		this.contactNo = contactNo;
	}

	

}