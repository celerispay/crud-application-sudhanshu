package com.crud.customerCRUD.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	private int custId;
	@Column(name = "custName")
	private String custName;
	@Column(name = "address")
	private String address;
	@Column(name = "contactNo")
	private String contactNo;

	public Customer() {
		super();
	}

	public Customer(int custId, String custName, String address, String contactNo) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.address = address;
		this.contactNo = contactNo;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", address=" + address + ", contactNo="
				+ contactNo + "]";
	}

}