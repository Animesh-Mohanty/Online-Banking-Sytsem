package com.animesh.Onlinebankingsystem.databaseconnection;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	public static Connection providerConnection()
	{
		Connection cnn = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException  e)
		{
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/bankingsystem";
		

		try
		{
			cnn = DriverManager.getConnection(url,"root","animesh1234+");
		}
		catch (Exception e)
		{
			System.out.println("Exception Occered "+e);
		}
		return cnn;
	}

}
