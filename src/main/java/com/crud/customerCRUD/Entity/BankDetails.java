package com.crud.customerCRUD.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.crud.customerCRUD.accountType;

@Entity
@Table(name="bankDetails")
public class BankDetails {
	
	@Id
	private String accountNo;
	
	private String bankName;
	
	@Enumerated(EnumType.STRING)
	private accountType accType;
	
	@Column(name="custId")
	private int custId;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    private Customer customer;
	
	
	public BankDetails() {
		super();
	}

	public BankDetails(String accountNo, String bankName, accountType accType, int custId) {
		super();
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.accType = accType;
		this.custId = custId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public accountType getAccType() {
		return accType;
	}

	public void setAccType(accountType accType) {
		this.accType = accType;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "BankDetails [accountNo=" + accountNo + ", bankName=" + bankName + ", accType=" + accType + ", custId="
				+ custId + "]";
	}
	 
	 
}
