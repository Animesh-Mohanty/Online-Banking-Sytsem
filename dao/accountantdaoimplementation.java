package com.animesh.Onlinebankingsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.animesh.Onlinebankingsystem.databaseconnection.DatabaseConnection;
import com.animesh.Onlinebankingsystem.entity.Customer;
import com.animesh.Onlinebankingsystem.entity.accountant;
import com.animesh.Onlinebankingsystem.exception.accountantexception;
import com.animesh.Onlinebankingsystem.exception.customerException;
public class accountantdaoimplementation implements accountantdao{

	public accountant loginaccountan(String accountantUsername, String accountantPassword) throws accountantexception {
		
		accountant acc = null;
		try(Connection cnn = DatabaseConnection.providerConnection())
		{
			PreparedStatement ps = cnn.prepareStatement("select * from accountant where accountantUsername = ? and accountantPassword = ?"); 
			ps.setString(1, accountantUsername);
			ps.setString(2, accountantPassword);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				String n = rs.getString("accountantUsername");
				String e = rs.getString("accountantEmail");
				String p = rs.getString("accountantPassword");
				acc = new accountant(n,e,p);
			}
		}
		catch (Exception e)
		{
			throw new accountantexception("Invalid Username and password");
		}
		return acc;
	}

	public int addCustomer(String customerName, String customerEmail, String customerPassword, String customerAddress,String customerMobile) throws customerException, SQLException 
	{
		int cid = -1;
		try(Connection cnn = DatabaseConnection.providerConnection())
		{
			PreparedStatement pss = cnn.prepareStatement("insert into customerInformation (customerName,customerEmail,customerPassword,customerAddress,customerMobile) values(?,?,?,?,?)");
			pss.setString(1,customerName);
			pss.setString(2,customerEmail);
			pss.setString(3,customerPassword);
			pss.setString(4,customerAddress);
			pss.setString(5,customerMobile);
			int x = pss.executeUpdate();
			if (x>0)
			{
				PreparedStatement ps2 = cnn.prepareStatement("select cid from customerInformation where customerEmail =? and customerMobile =?");
				ps2.setString(1,customerEmail);
				ps2.setString(2, customerMobile);
				ResultSet rs = ps2.executeQuery();
				if(rs.next())
				{
					cid = rs.getInt("cid");
				}else
				{
					System.out.println("Inserted Data Incorrect");
				}
				System.out.println("customer Added SucessFully");
			}
			else
			{
				System.out.println("customer not added");
			}

		}
		catch(Exception e)
		{
			System.out.println(" SQL Exception");
		}
		
		return cid;
	}

	@Override
	public String addAccount(int customerBalance, int cid) throws customerException {
		String mes = null;
		try(Connection cnn = DatabaseConnection.providerConnection())
		{
			PreparedStatement ps = cnn.prepareStatement("insert into account (customerBalance ,cid) values (?,?)");
			ps.setInt(1, customerBalance);
			ps.setInt(2, cid);
			int x = ps.executeUpdate();
			if(x>0)
			{
				System.out.println("Account added Sucessfully");
			}
			else
			{
				System.out.println("Account not added");
			}
			
		} catch (SQLException e) {
			System.out.println("SQL related error");
		}
		return mes;
	}

	@Override
	public String updateCustomer(int customerAccountNumber,String customerAddress) throws customerException {
		String mss= null;
		try(Connection cnn = DatabaseConnection.providerConnection())
		{
			PreparedStatement ps = cnn.prepareStatement("update customerinformation i inner join account a on i.cid = a.cid and a.customerAccountnumber = ? set i.customerAddress =? ");
			ps.setInt(1,customerAccountNumber);
			ps.setString(2,customerAddress);
			int x =ps.executeUpdate();
			if(x>0)
			{
				System.out.println("Address Updated Sucessfully");
			}
			else
			{
				System.out.println("Customer updateion is not sucessfull");
				System.out.println("-------------------------------------");
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			mss = e.getMessage();
			
		}
		return mss;
	}

	@Override
	public String deleteAccount(int customerAccountNumber) throws customerException {
		String msg = null;
		try (Connection cnn = DatabaseConnection.providerConnection()){
			PreparedStatement ps = cnn.prepareStatement("delete i from customerinformation i inner join account a on i.cid = a.cid where a.customerAccountNumber = ?");
			ps.setInt(1, customerAccountNumber);
			int x = ps.executeUpdate();
			if (x>0) {
				msg ="Account Deleted Sucessfully...";
				System.out.println(msg);
			}else {
				System.out.println("Deletion Failed.........");
				System.out.println("-------------------------------------");
			}	
		}
		catch(SQLException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		
		return null;
	}

	@Override
	public Customer viewCustomer(int customerAccountNumber) throws customerException {
		Customer c = null;
		try(Connection cnn = DatabaseConnection.providerConnection()){
			PreparedStatement ps = cnn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid where customerAccountNumber = ?");
			ps.setInt(1, customerAccountNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int a  = rs.getInt("customerAccountNumber");
				String n = rs.getString("customerName");
				int bal  = rs.getInt("customerBalance");
				String mail = rs.getString("customerEmail");
				String pass = rs.getString("customerPassword");
				String mob = rs.getString("customerMobile");
				String add = rs.getString("customerAddress");
				
				c = new Customer(a,bal,n,mail,pass,mob,add);
				
			}else {
				throw new customerException("Invalid Account number");
			}

		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return c;
	}
}
