package com.animesh.Onlinebankingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.animesh.Onlinebankingsystem.databaseconnection.DatabaseConnection;
import com.animesh.Onlinebankingsystem.entity.Customer;
import com.animesh.Onlinebankingsystem.exception.customerException;

public class customerdaoImplementation implements customerdao{

	@Override
	public Customer customerLogin(String customerName, String customerPassword, int customerAccountNumber) throws customerException {
		Customer c = null;
		try(Connection cnn = DatabaseConnection.providerConnection()){
			PreparedStatement ps = cnn.prepareStatement("select * from customerinformation i inner join account a on i.cid = a.cid where  customerName = ? and customerPassword = ? and customerAccountNumber = ?");
			ps.setString(1, customerName);
	        ps.setString(2, customerPassword);
	        ps.setInt(3, customerAccountNumber);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String n = rs.getString("customerName");
				String p = rs.getString("customerPassword");
				int a = rs.getInt("customerAccountNumber");
				String ad = rs.getString("customerAddress");
				int bal = rs.getInt("customerBalance");
				String mail = rs.getString("customerEmail");
				String mob = rs.getString("customerMobile");
				
				c =new Customer(a,bal,n,mail,p,ad,mob);
				
			}
			else {
				throw new customerException("Invalid Customer name and password");
			}
		}catch(SQLException e) {
			throw new customerException(e.getMessage());
		}
		return c;
	}

	@Override
	public int accountBalance(int customerAccountNumber) throws customerException {
		int b =-1;
		try(Connection cnn = DatabaseConnection.providerConnection()){
			PreparedStatement ps = cnn.prepareStatement("select customerBalance from account where customerAccountNumber =? ");
			ps.setInt(1,customerAccountNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				b = rs.getInt("customerBalance");
			}
			
		}
		catch(SQLException e) {
			throw new customerException(e.getMessage());
			
		}
		return b;
	}

	@Override
	public int deposit(int customerAccountNumber, int a) throws customerException {
		if (a<=0) {
			return -1;
		}
		int b = -1;
		try(Connection cnn = DatabaseConnection.providerConnection()){
			PreparedStatement ps = cnn.prepareStatement("update account set customerBalance = customerBalance+? where customerAccountNumber =?");
			ps.setInt(1, a);
			ps.setInt(2, customerAccountNumber);
			int rs = ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new customerException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int widthdraw(int customerAccountNumber, int a) throws customerException {
		if(a<=0) {
			return -1;
		}
		int b = -1;
		try(Connection cnn = DatabaseConnection.providerConnection()){
			PreparedStatement ps = cnn.prepareStatement("update account set customerBalance = customerBalance-? where customerAccountNumber = ?");
			ps.setInt(1, a);
			ps.setInt(2, customerAccountNumber);
			int rs = ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new customerException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int transfer(int customerAccountNumber, int a, int customerAccountNumber2) throws customerException {
		int bal = accountBalance(customerAccountNumber);
		if (bal>=a && checkAccount(customerAccountNumber2)) {
			int wid = widthdraw(customerAccountNumber,a);
			int dep = deposit(customerAccountNumber2,a);
		}else {
			throw new customerException("Insufficent balance");
		}
		return 0;
	}
	public boolean checkAccount(int customerAccountNumber) throws customerException {
		try(Connection cnn = DatabaseConnection.providerConnection()){
			PreparedStatement ps = cnn.prepareStatement("select * from account where customerAccountNumber = ?");
			ps.setInt(1, customerAccountNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			throw new customerException(e.getMessage());
		}
		return false;
	}


}
