package com.animesh.Onlinebankingsystem.dao;
import com.animesh.Onlinebankingsystem.exception.accountantexception;
import com.animesh.Onlinebankingsystem.exception.customerException;

import java.sql.SQLException;

import com.animesh.Onlinebankingsystem.entity.Customer;
import com.animesh.Onlinebankingsystem.entity.accountant;
public interface accountantdao {
	
	public accountant loginaccountan(String accountantUsername,String accountantPassword) throws accountantexception;
	
	public int addCustomer(String customerName,String customerEmail,String customerPassword,String customerAddress,String customerMobile) throws customerException, SQLException;
	
	public String addAccount(int customerBalance,int cid) throws customerException;
	
	public String updateCustomer(int customerAccountNumber,String customerAddress) throws customerException;
	
	public String deleteAccount(int customerAccountNumber) throws customerException;
	
	public Customer viewCustomer(int customerAccountNumber) throws customerException;
	
	

}
