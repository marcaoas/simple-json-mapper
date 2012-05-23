package com.simplejsonmapper.test;

import com.simplejsonmapper.JsonField;


public class Product {
	
	private String name;
	private int amount;
	private double price;
	private double[] oldPrices;
	
	@JsonField("retail_price")
	private Double retailPrice;
	
	public double[] getOldPrices(){
		return oldPrices;
	}
	
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
