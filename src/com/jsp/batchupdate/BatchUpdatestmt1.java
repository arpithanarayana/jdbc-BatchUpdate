package com.jsp.batchupdate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class BatchUpdatestmt1 {

	public static void main(String[] args) {
		FileReader filereader = null;
		Properties properties = new Properties();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertQry = "insert into emp1(empname,email,password,address,state,city,pincode,phone) values(?,?,?,?,?,?,?,?)";
		
		try {
			filereader = new FileReader("config/dbconfig.properties");
			properties.load(filereader);
			
			Class.forName(properties.getProperty("Driver"));
			System.out.println("load and register the driver");
			
			connection = DriverManager.getConnection(properties.getProperty("url"), 
					properties.getProperty("username"), 
					properties.getProperty("password"));
			
			preparedStatement = connection.prepareStatement(insertQry);
			
			/*set the placeholders*/
			preparedStatement.setString(1, "Push");
			preparedStatement.setString(2, "pushchild@gmail.com");
			preparedStatement.setString(3, "push123");
			preparedStatement.setString(4, "Basavanagudi");
			preparedStatement.setString(5, "Karnataka");
			preparedStatement.setString(6, "Bangalore");
			preparedStatement.setInt(7, 574001);
			preparedStatement.setLong(8, 9743873008L);
			preparedStatement.addBatch();
			
			preparedStatement.setString(1, "Pooja");
			preparedStatement.setString(2, "pooja123@gmail.com");
			preparedStatement.setString(3, "pooja456");
			preparedStatement.setString(4, "Kengeri");
			preparedStatement.setString(5, "Karnataka");
			preparedStatement.setString(6, "Bangalore");
			preparedStatement.setInt(7, 574007);
			preparedStatement.setLong(8, 9743873009L);
			preparedStatement.addBatch();
			
			preparedStatement.setString(1, "Chaithra");
			preparedStatement.setString(2, "chai123@gmail.com");
			preparedStatement.setString(3, "chai456");
			preparedStatement.setString(4, "Attigupe");
			preparedStatement.setString(5, "Karnataka");
			preparedStatement.setString(6, "Bangalore");
			preparedStatement.setInt(7, 574009);
			preparedStatement.setLong(8, 9743873006L);
			preparedStatement.addBatch();
			
			int[] execteBatch = preparedStatement.executeBatch();
			System.out.println("Result of Batch " + Arrays.toString(execteBatch));
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(preparedStatement!=null)
			{
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
