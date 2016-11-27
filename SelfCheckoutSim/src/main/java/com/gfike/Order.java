package com.gfike;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "Order")
public class Order {
	private double total;
	private double lTax;
	private double hTax;
	ArrayList<Product> lst = new ArrayList<Product>();
	private int uid;
	
	public Order () {
	}
	
	@Id
    @GeneratedValue
    @NotNull
    @Column(name = "uid", unique = true)
	public int getUid() {
		return this.uid;
	}
	
	@Column(name="Total")
	public double getTotal() {
		total = 0.0;
		for(Product p : lst) {
			total += p.getPrice();
		}
		total /= 100;
		return total;
	}
	
	@Column(name="LowTax")
	public double getlTax() {
		lTax = 0.0;
		for(Product p : lst) {
			lTax += p.getlTax();
		}
		lTax /= 100;
		return lTax;
	}

	@Column(name="HighTax")
	public double gethTax() {
		hTax = 0.0;
		for(Product p : lst) {
			hTax += p.gethTax();
		}
		hTax /= 100;
		return hTax;
	}
	
	public ArrayList<Product> getLst() {
		return lst;
	}

	public void setLst(ArrayList<Product> lst) {
		this.lst = lst;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setlTax(double lTax) {
		this.lTax = lTax;
	}

	public void sethTax(double hTax) {
		this.hTax = hTax;
	}

	public void setUid(int uid) {
		this.uid = uid;
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
