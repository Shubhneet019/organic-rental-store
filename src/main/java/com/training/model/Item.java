package com.training.model;

public class Item {
	private int itemCode;
	private double price;
	private int quantity;
	private String category;
	private String itemName;
	public Item(int itemCode, double price, int quantity, String category, String itemName) {
		super();
		this.itemCode = itemCode;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.itemName = itemName;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

}
