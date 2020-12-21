package com.training.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.training.ifaces.Report;
import com.training.model.Item;

public class ItemService implements Report {


private Connection connection;
// Mapping tables with name
private String Mapping_ITEM_TABLE="item";
private String MAPPING_FASHION_TABLE="Fashion";
private String MAPPING_EDIBLE_TABLE="Edible";
private String MAPPING_ELECTRONICS_TABLE="Electronics";

Item item=null;
 
	public ItemService(Connection connection) {
	super();
	this.connection = connection;
}
	//to buy items-->addItem()
	@Override
	public void addItem(int itemCode,int quantity) {
		String value=findCategory(itemCode);
		System.out.println(value);
		item=getItemDetail(itemCode,value,quantity);
		System.out.println(item.getPrice());
		System.out.println(item.getItemName());
		
		entryToDatabase(item,quantity);
		
	}
	@Override
	public List<Item> dailyReport(){
		return null;
	}
	@Override
	public List<Item> monthlyReport(){
		return null;
	}
	@Override
	public List<Item> monthlyReportByCategory(  ){
		return null;
	}
	public String findCategory(int itemCode)  {
		String result="";
	
		String sql="select * from " +Mapping_ITEM_TABLE+ " where itemCode=? ";
		
		try (PreparedStatement stmt=connection.prepareStatement(sql)){
			stmt.setInt(1,itemCode);
			
			ResultSet rset=stmt.executeQuery();
			while(rset.next())
			result=rset.getString("category");
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
return result;
}
private Item getItemDetail(int itemCode,String category,int quantity) {
	String tableName="";
	switch(category) {
	case "Garments":tableName=MAPPING_FASHION_TABLE;break;
	case "Edible":tableName=MAPPING_EDIBLE_TABLE;break;
	case "Electronics":tableName=MAPPING_ELECTRONICS_TABLE;break;
	default:break;	
	}
	Item item=null;
	String sql="select * from " +tableName+ " where itemCode=? ";
	
	try (PreparedStatement stmt=connection.prepareStatement(sql)){
		stmt.setInt(1,itemCode);
		
		ResultSet rset=stmt.executeQuery();
		while(rset.next()) {
		String name=rset.getString("itemName");
		double price=rset.getDouble("unitPrice");
		item=new Item(itemCode,price,quantity,category,name);
		
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}	
	return item;
	
}
@Override
public boolean entryToDatabase(Item item,int quantity) {
	boolean result=false;
	int code=item.getItemCode();
	String itemName=item.getItemName();
	String cat=item.getCategory();
	Date currentDate = new Date(System.currentTimeMillis());
	double price=item.getPrice();
	double total=quantity*price;
	
	PreparedStatement stmt;
	try {
		String sql="insert into soldItems values(?,?,?,?,?,?,?)";
		stmt=connection.prepareStatement(sql);
		stmt.setInt(1, code);
		stmt.setString(2, itemName);
		stmt.setDate(6, currentDate);
		stmt.setDouble(4, price);
		stmt.setDouble(7, total);
		stmt.setString(3, cat);
		stmt.setInt(5, quantity);
		
		int val=stmt.executeUpdate();
		if(val==1)	
		result=true;
	}
	catch(Exception e) {
		e.printStackTrace();
	}	
	return result;	

}}

////copied code
//
//public List<Item> dailyReportByCategory(String category, int noOfItems) {
//	List<Item> result = null;
//	String query = "select sum(quantity) as quantitySum,sum(total) as totalSum,itemCode,itemName,category,unitPrice from soldItems where category = "
//	+ "\"" + category + "\"" + " and date = ? group by itemCode order by sum(quantity) desc";
//	PreparedStatement statement;
//	try {
//	statement = connection.prepareStatement(query);
//	statement.setDate(1, new Date(System.currentTimeMillis()));
//	ResultSet rs = statement.executeQuery();
//	result = addItemFromResultSet(rs, noOfItems);
//
//	 } catch (Exception ex) {
//	ex.printStackTrace();
//	}
//
//	 return result;
//	}
//
//private List<Item> addItemFromResultSet(ResultSet rs, int counter) {
//
//	 List<Item> list = new ArrayList<Item>();
//	int i = 0;
//	try {
//	while (rs.next() && i < counter) {
//	Item entry = new Item();
//	entry.setItemCode(rs.getInt("itemCode"));
//	entry.setItemName(rs.getString("itemName"));
//	entry.setPrice((double) rs.getDouble("unitPrice"));
//	entry.setCategory(rs.getString("category"));
//	entry.setQuantity(rs.getInt("quantitySum"));
//	entry.setPrice(rs.getDouble("totalSum"));
//	list.add(entry);
//	i++;
//	}
//	} catch (Exception ex) {
//	ex.printStackTrace();
//	}
//	return list;
//	}




