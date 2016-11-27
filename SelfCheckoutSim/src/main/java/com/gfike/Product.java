package com.gfike;

//what's used in the selfcheckout
public class Product {

	private int hTax;
	private int lTax;
	private int barcode;
	private String name;
	private int wt;
	private int price;
	
	public Product() {
	}
	
	public int gethTax() {
		return hTax;
	}
	
	public int getlTax() {
		return lTax;
	}
	
	public int getBarcode() {
		return barcode;
	}

	public String getName() {
		return name;
	}

	public int getWt() {
		return wt;
	}

	public int getPrice() {
		return price;
	}
	
	
}
