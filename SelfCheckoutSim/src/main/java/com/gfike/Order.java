package com.gfike;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Order")
public class Order {

	private float total;
	private float total_hTax;
	private float total_lTax;
	ArrayList<Item> list = new ArrayList<Item>();
	private int uid;
	
	Order () {}
	
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
	
	@Column(name="total")
	@NotNull
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	@Column(name="HighTax")
	public float getTotal_hTax() {
		return total_hTax;
	}

	public void setTotal_hTax(float total_hTax) {
		this.total_hTax = total_hTax;
	}
	
	@Column(name="LowTax")
	public float getTotal_lTax() {
		return total_lTax;
	}

	public void setTotal_lTax(float total_lTax) {
		this.total_lTax = total_lTax;
	}
	
	@Column(name="items")
	public ArrayList<Item> getList() {
		return list;
	}

	public void setList(ArrayList<Item> list) {
		this.list = list;
	}
	
	
}
