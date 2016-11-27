package com.gfike;

//what's used in the selfcheckout
public class Product {

	private int hTax;
	private int lTax;
	private int barcode;
	private String name;
	private int wt;
	private int price;
	
	public Product(String name) {
		this.name = name;
		this.barcode = makeBarcode(name);
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
	
	public static int makeBarcode (String name) {
		int barcode = 0;
		for (int i = 0; i < 11; i++) {
			char c = name.charAt(i);
			int ascii = (int) c;
			barcode += ascii;
		}
		
		return barcode;
	}
}
