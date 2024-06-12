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

public class BatchUpdate2 {

	public static void main(String[] args) {
		FileReader filereader = null;
		Properties properties = new Properties();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateQry = "update emp1 set empname = ? where empid = ?";
		
		try {
			filereader = new FileReader("config/dbconfig.properties");
			properties.load(filereader);
			
			Class.forName(properties.getProperty("Driver"));
			System.out.println("load and register the driver");
			
			connection = DriverManager.getConnection(properties.getProperty("url"), 
					properties.getProperty("username"), 
					properties.getProperty("password"));
			
			preparedStatement = connection.prepareStatement(updateQry);
			
			/*set the placeholders*/
			preparedStatement.setString(1, "smith");
			preparedStatement.setInt(2, 5);
			preparedStatement.addBatch();
			
			preparedStatement.setString(1, "Blake");
			preparedStatement.setInt(2, 8);
			preparedStatement.addBatch();
			
			preparedStatement.setString(1, "Clake");
			preparedStatement.setInt(2, 4);
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
