package com.training.myjdbc;
import java.sql.*;
import javax.sql.DataSource;
import java.util.Properties;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
public class ConnectionUtils {
      public static Connection getMyConnection() {
    	  Connection con=null;
    	  try {
    	  
    	  String fileName="Resources/DBConnection.properties";
    	  InputStream inStream=ConnectionUtils.class.getClassLoader().getResourceAsStream(fileName);
    	  
    	
//    	  System.out.println(inStream);
    	  Properties prop=new Properties();
    	  prop.load(inStream);
    	  
    	  String url=prop.getProperty("database.url");
    	  String userName=prop.getProperty("database.userName");
    	  String userPassword=prop.getProperty("database.passWord");
    	  con=DriverManager.getConnection(url,userName,userPassword);
    	  }
    	  catch (Exception e) {
			e.printStackTrace();
		} 
    	  return con;
      }
      public static Connection getConnectionFromPool() throws IOException {
    	  Connection connection=null;
    	  try {
    		  HikariConfig config=new HikariConfig();
    		  String []values=getPropsAsArray();
    		  config.setJdbcUrl(values[0]);
    		  config.setUsername(values[1]);
    		  config.setPassword(values[2]);
    		  config.addDataSourceProperty("maximumPoolSize", 25);
    		  
    		  DataSource dataSource=new HikariDataSource(config);
    		  connection=dataSource.getConnection();
    		  
    	  }
    	  catch(SQLException e) {
    		  e.printStackTrace();
    		  
    	  }
    	  return connection;
      }
      private static String[] getPropsAsArray() throws IOException {
    	  String fileName="Resources/DBConnection.properties";
    	  InputStream inStream=ConnectionUtils.class.getClassLoader().getResourceAsStream(fileName);
    	  
    	
//    	  System.out.println(inStream);
    	  Properties prop=new Properties();
    	  prop.load(inStream);
    	  
    	  String url=prop.getProperty("database.url");
    	  String userName=prop.getProperty("database.userName");
    	  String userPassword=prop.getProperty("database.passWord");
    	  return new String[] {url,userName,userPassword};
	}
	public static void main(String []args)
      {
    	 System.out.println(getMyConnection()); 
      }
}