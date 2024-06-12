package com.jsp.batchupdate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

public class BatchUpdatestmt {

	public static void main(String[] args) {
		FileReader filereader = null;
		Properties properties = new Properties();
		Connection connection = null;
		Statement statement = null;
		String instquery = "insert into emp1(empname,email,password,address,state,city,pincode,phone) values('Veda','veda123@gmail.com','123ved','Vijayanagar','Karanataka','Bangalore',567801,9743873002)";
				//"create table emp1(empid integer auto_increment primary key,empname varchar(20),email varchar(20),password varchar(20),address varchar(20),state varchar(20),city varchar(20),pincode integer,phone bigint)";
		String updquery = "update emp1 set email='arpitha123@gmail.com' where empid=1";
		String delequery = "delete from emp1 where empid=3";
		
		try {
			filereader = new FileReader("config/dbconfig.properties");
			properties.load(filereader);
			
			Class.forName(properties.getProperty("Driver"));
			System.out.println("load and Register the driver");
			
			connection = DriverManager.getConnection(properties.getProperty("url"), 
					properties.getProperty("username"), 
					properties.getProperty("password"));
			System.out.println("Established connection : " + connection);
			
			statement = connection.createStatement();
//			System.out.println(statement);
//	
//			boolean res = statement.execute(query);
//			System.out.println(res);
//			
//			int updatecount = statement.getUpdateCount();
//			System.out.println(updatecount);
			
			/*Create a batch by adding Dml sql query*/
			statement.addBatch(instquery);
			statement.addBatch(updquery);
			statement.addBatch(delequery);
			
			/*exectue the query*/
			int[] norarr = statement.executeBatch();
			//0-->Result of insert query--->1
			//1-->Result of insert query--->1
			//2-->Result of insert query--->1
			System.out.println("Batch Results " + Arrays.toString(norarr));
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if(statement!=null)
			{
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		

	}

}
