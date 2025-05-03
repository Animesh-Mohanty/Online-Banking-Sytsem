package com.animesh.Onlinebankingsystem.dao;

import com.animesh.Onlinebankingsystem.entity.Customer;
import com.animesh.Onlinebankingsystem.exception.customerException;

public interface customerdao {
	public Customer customerLogin(String customerName,String customerPassword,int customerAccountNumber) throws customerException;
	
	public int accountBalance(int customerAccountNumber) throws customerException;
	
	public int deposit(int customerAccountNumber,int a) throws customerException;
	
	public int widthdraw(int customerAccountNumber,int a) throws customerException;
	
	public int transfer(int customerAccountNumber,int a ,int customerAccountNumber2) throws customerException;
	


}
