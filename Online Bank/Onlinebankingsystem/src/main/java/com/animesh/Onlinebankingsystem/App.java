package com.animesh.Onlinebankingsystem;

import java.sql.SQLException;
import java.util.Scanner;

import com.animesh.Onlinebankingsystem.dao.accountantdao;
import com.animesh.Onlinebankingsystem.dao.accountantdaoimplementation;
import com.animesh.Onlinebankingsystem.dao.customerdao;
import com.animesh.Onlinebankingsystem.dao.customerdaoImplementation;
import com.animesh.Onlinebankingsystem.entity.Customer;
import com.animesh.Onlinebankingsystem.entity.accountant;
import com.animesh.Onlinebankingsystem.exception.accountantexception;
import com.animesh.Onlinebankingsystem.exception.customerException;

public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        while(f)
        {
        	System.out.println("------------Welcome to online banking System------------");
        	System.out.println("--------------------------------------------------------");
        	System.out.println("1.Admin Login Portal\r\n"+"2.Customer");
        	System.out.println("Choose Your Option");
        	int ch = sc.nextInt();
        	switch(ch)
        	{
        	case 1:
        		System.out.println("Admin Login Credentials------------Accountant");
        		System.out.println("Enter Username");
        		String un = sc.next();
        		System.out.println("Enter Password");
        		String p = sc.next();
        		
        		accountantdao ad = new accountantdaoimplementation();
        		try
        		{
        			accountant a = ad.loginaccountan(un,p);
        			if(a==null)
        			{
        				System.out.println("Wrong credentials");
        				break;
        			}
        			System.out.println("Login Succesfull");
        			System.out.println("Welcome to : "+a.getAccountantUsername()+" as Admin of Online Banking System");
        			
        			boolean y = true;
        			while(y)
        			{
        				System.out.println("--------------\r\n"+
        						"1. Add new Customer Account \r\n"+
        						"2. Update Customer Address\r\n"+
        						"3. Delete Account by Account Number\r\n"+
        						"4. View Particular Account Details\r\n"+
        						"5. Logout\r\n");
        				int x = sc.nextInt();
        				if(x==1)
        				{
        					System.out.println("-------New Account-------\r\n");
        					System.out.println("Enter CustomerName: ");
        					String a1 = sc.next();
        					System.out.println("Enter CustomerEmail: ");
        					String a2 = sc.next();
        					System.out.println("Enter CustomerPassword: ");
        					String a3 = sc.next();
        					System.out.println("Enter CustomerAddress: ");
        					String a4 = sc.next();
        					System.out.println("Enter CustomerMobile: ");
        					String a5 = sc.next();
        					System.out.println("Enter Account Opening Balance: ");
        					int a6 = sc.nextInt();
        				
	        				int s1 = -1;
	        				try
	        				{
	        					try {
									s1 = ad.addCustomer(a1,a2,a3,a4,a5);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	        					try
	        					{
	        						ad.addAccount(a6, s1);
	        					}
	        					catch(Exception e)
	        					{
	        						e.printStackTrace();
	        					}
	        				}
	        				catch(customerException e)
	        				{
	        					System.out.println(e.getMessage());
	        				}
	        				System.out.println("Customer added Sucessfully");
	        				System.out.print("-----------------");
        				}
        				if (x==2)
        				{
        					System.out.println("update Customer Address");
        					System.out.println("Enter customer Account number");
        					int u = sc.nextInt();
        					System.out.println("Enter new Address..");
        					String u2 = sc.next();
        					try
        					{
        						String mes = ad.updateCustomer(u, u2);
        					}
        					catch (customerException e)
        					{
        						e.printStackTrace();
        					}
        					
        				}
        				
        				if (x==3) {
        					System.out.println("--------Remove Account---------");
        					System.out.println("Enter Account Number: ");
        					int ac = sc.nextInt();
        					String s = null;
        					try {
        						s = ad.deleteAccount(ac);
        					}catch(customerException e) {
        						e.printStackTrace();
        					}
        					if (s!=null) {
        						System.out.println(s);
        					}
        					
        				}
        				
        				if (x==4) {
        					System.out.println("--------Customer Details-------");
        					System.out.println("Enter Customer Account Number: ");
        					int ac = sc.nextInt();
        					try {
        						Customer c  = ad.viewCustomer(ac);
        						if(c!=null) {
        							System.out.println("****************");
        							System.out.println("Account no: "+c.getCustomerAccountNumber());
        							System.out.println("Name: "+c.getCustomerName());
        							System.out.println("Ballance: "+c.getCustomerBalance());
        							System.out.println("Email: "+c.getCustomerEmail());
        							System.out.println("Password: "+c.getCustomerPassword());
        							System.out.println("Address: "+c.getCustomerAddress());
        							System.out.println("Mobile no: "+c.getCustomerMobile());
        							System.out.println("----------------------");
        						}else {
        							System.out.println("Account does not exist");
        							System.out.println("----------------------");
        						}
        					}
        					catch(customerException e) {
        						e.printStackTrace();
        					}
        				}
        				
        				if(x==5) {
        					System.out.println("---------Account Logout Sucessfully---------");
        					y = false;
        				}
        				
        				
        			}
        			break;
        			
        		}
        		catch(accountantexception e)
        		{
        			System.out.println(e.getMessage());
        		}
        		break;
        		
        		
        	case 2:
        		System.out.println("Login----------------------------->Customer");
        		System.out.println("Enter Account Name: ");
        		String customerName = sc.next();
        		System.out.println("Enter Account Password: ");
        		String customerPassword = sc.next();
        		System.out.println("Enter Account Number: ");
        		int cid = sc.nextInt();
        		
        		customerdao cd = new customerdaoImplementation();
        		try {
        			Customer cu = cd.customerLogin(customerName, customerPassword, cid);
        			System.out.println("Welcome------>  "+cu.getCustomerName());
        			boolean m = true;
        			while(m) {
        				System.out.println("------------------------------------\r\n"+
        									"1. View Balance\r\n"+
        									"2. Deposit Money\r\n"+
        									"3> Widthdraw Money\r\n"+
        									"4. Transfer Money\r\n"+
        									"5. Logout\r\n");
        				int x = sc.nextInt();
        				if(x == 1) {
        					System.out.println("------------BALANCE------------");
        					System.out.println("Current Account Balance:  "+cd.accountBalance(cid));
        				}
        				if(x==2) {
        					System.out.println("Enter Amount to Deposit: ");
        					int a = sc.nextInt();
        					if (a<=0) {
        						System.out.println("Deposit cannot be 0 or negative");
        					}else {
        						cd.deposit(cid, a);
            					System.out.println("Balane After Deposit: "+cd.accountBalance(cid));
            					
        					}
        					
        				}
        				if(x==3) {
        					System.out.println("Enter amount to widthdraw: ");
        					int a = sc.nextInt();
        					if (a<=0) {
        						System.out.println("Amount cannot be 0 or negative");
        					}else {
        						cd.widthdraw(cid, a);
            					System.out.println("Balane After Widthdraw: "+cd.accountBalance(cid));
            					
        					}
        					
        				}
        				if(x==4) {
        					System.out.println("Enter Sender Bank Account Number: ");
        					int a1 = sc.nextInt();
        					System.out.println("Enter Recivers Bank Account Number: ");
        					int a2 = sc.nextInt();
        					System.out.println("Enter Amount to transfer: ");
        					int am = sc.nextInt();
        					
        					try {
        						cd.transfer(a1, am, a2);
        						System.out.println("Amount Transfered Sucessfully");
        						System.out.println("------------------------------");
        						
        						
        					}catch(Exception e) {
        						System.out.println(e.getMessage());
        					}
        				}
        				if(x==5) {
        					m = false;
        				}
        				
        			}
        			
        		}
        		catch(Exception e) {
        			
        		}
        		
        		
        	}
        	
        }
    }
}
