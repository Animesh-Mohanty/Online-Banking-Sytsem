package com.animesh.Onlinebankingsystem.entity;

public class Customer {
	private int customerAccountNumber;
	private int customerBalance;
	private String customerName;
	private String customerEmail;
	private String customerPassword;
	private String customerAddress;
	private String customerMobile;
	public Customer(int customerAccountNumber, int customerBalance, String customerName, String customerEmail,
			String customerPassword, String customerAddress, String customerMobile) {
		super();
		this.customerAccountNumber = customerAccountNumber;
		this.customerBalance = customerBalance;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.customerAddress = customerAddress;
		this.customerMobile = customerMobile;
	}
	public Customer() {
		super();
	}
	public int getCustomerAccountNumber() {
		return customerAccountNumber;
	}
	public void setCustomerAccountNumber(int customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}
	public int getCustomerBalance() {
		return customerBalance;
	}
	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	@Override
	public String toString() {
		return "Customer [customerAccountNumber=" + customerAccountNumber + ", customerBalance=" + customerBalance
				+ ", customerName=" + customerName + ", customerEmail=" + customerEmail + ", customerPassword="
				+ customerPassword + ", customerAddress=" + customerAddress + ", customerMobile=" + customerMobile
				+ "]";
	}
	
	
	

}
