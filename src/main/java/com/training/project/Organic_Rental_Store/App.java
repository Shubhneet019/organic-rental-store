package com.training.project.Organic_Rental_Store;


import java.io.*;
import java.sql.*;

import com.training.myjdbc.ConnectionUtils;
import com.training.services.ItemService;
public class App 
{
	
    public static void main( String[] args )
    {
    	Connection connection=new ConnectionUtils().getMyConnection();
    ItemService service=new ItemService(connection);
    System.out.println(connection);   
    service.addItem(107, 50);
    
   
    }
}
