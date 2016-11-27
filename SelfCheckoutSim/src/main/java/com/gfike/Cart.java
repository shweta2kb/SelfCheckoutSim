package com.gfike;

import java.util.ArrayList;

public class Cart {
	ArrayList<Product> lst = new ArrayList<Product>();
	
	public Cart () {}
	
	public void addProduct(Product p) {
		lst.add(p);
	}
	
	public void removeProduct(Product p) {
		lst.remove(p);
	}
	
	public void addMultiples(int num, Product p){
		for(int i = 0; i < num -1; i ++) {
			lst.add(p);
		}
	}
}
