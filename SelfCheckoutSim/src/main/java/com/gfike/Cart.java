package com.gfike;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="Cart")
public class Cart {
	ArrayList<Product> lst = new ArrayList<Product>();
	
	public Cart () {}
	
	@Column(name="lst")
	public ArrayList<Product> getLst() {
		return lst;
	}

	public void setLst(ArrayList<Product> lst) {
		this.lst = lst;
	}

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
