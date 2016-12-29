package com.gfike;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Trans")
public class Trans {
	
private int uid;
ArrayList<Item> lst = new ArrayList<Item> ();
private double subtotal;
private double total;
private double hTaxTotal;
private double lTaxTotal;
	
public Trans () {}

public Trans (ArrayList<Item> lst) {
	this.lst = lst;
}

	@Id
    @GeneratedValue
    @NotNull
    @Column(name = "uid", unique = true)
	public int getUid() {
		return this.uid;
	}
	
	protected void setUid(int uid) {
        this.uid = uid;
    }
	
	@Column(name="Items")
	public ArrayList<Item> getLst() {
		return lst;
	}

	public void setLst(ArrayList<Item> lst) {
		this.lst = lst;
	}
	
	public void addItem(Item i) {
		lst.add(i);
	}
	
	public void removeItem(Item i) {
		lst.remove(i);
	}
	
	@Column(name="subtotal")
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	@Column(name="total")
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	@Column(name="hTaxTotal")
	public double gethTaxTotal() {
		return hTaxTotal;
	}

	public void sethTaxTotal(double hTaxTotal) {
		this.hTaxTotal = hTaxTotal;
	}
	
	@Column(name="lTaxTotal")
	public double getlTaxTotal() {
		return lTaxTotal;
	}

	public void setlTaxTotal(double lTaxTotal) {
		this.lTaxTotal = lTaxTotal;
	}
	
}
