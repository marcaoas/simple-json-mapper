package com.simplejsonmapper;
import com.simplejsonmapper.JsonField;


public class Product {
	
	private String name;
	private int amount;
	private double price;
	
	@JsonField("retail_price")
	private Double retailPrice;
	
	
	public String getName() {
		return name;
	}
	public int getAmount() {
		return amount;
	}
	public double getPrice() {
		return price;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}
	
	
}
