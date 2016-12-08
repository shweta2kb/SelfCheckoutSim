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
	ArrayList<Item> lst = new ArrayList<Item>();
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
		for(Item p : lst) {
			total += p.getPrice();
		}
		total /= 100;
		return total;
	}
	
	@Column(name="LowTax")
	public double getlTax() {
		lTax = 0.0;
		for(Item p : lst) {
			lTax += p.getlTax();
		}
		lTax /= 100;
		return lTax;
	}

	@Column(name="HighTax")
	public double gethTax() {
		hTax = 0.0;
		for(Item p : lst) {
			hTax += p.gethTax();
		}
		hTax /= 100;
		return hTax;
	}
	
	public ArrayList<Item> getLst() {
		return lst;
	}

	public void setLst(ArrayList<Item> lst) {
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
	
	public void additem(Item i) {
		lst.add(i);
	}
	
	public void removeitem(Item i) {
		lst.remove(i);
	}
	
	public void addMultiples(int num, Item i){
		for(int j = 0; j < num -1; j ++) {
			lst.add(i);
		}
	}
}
