package com.gfike;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Trans")
public class Trans {
//TODO need to redo deterTax methods
	private int uid;
	private String lst;
	private double subtotal;
	private double total;
	private double hTaxTotal;
	private double lTaxTotal;

	public Trans() {
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

	@Column(name = "Items")
	public String getLst() {
		return lst;
	}

	public void setLst(String lst) {
		this.lst = lst;
	}

	public void addItem(Item i) {
		lst += i.getPlu() + ",";
	}


	@Column(name = "subtotal")
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Column(name = "total")
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Column(name = "hTaxTotal")
	public double gethTaxTotal() {
		return hTaxTotal;
	}

	public void sethTaxTotal(double hTaxTotal) {
		this.hTaxTotal = hTaxTotal;
	}

	@Column(name = "lTaxTotal")
	public double getlTaxTotal() {
		return lTaxTotal;
	}

	public void setlTaxTotal(double lTaxTotal) {
		this.lTaxTotal = lTaxTotal;
	}

}
