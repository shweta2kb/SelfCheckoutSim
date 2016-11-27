package com.gfike;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Item")
public class Item {
	private String name;
	private int price;
	private int plu;
	private int wt;
	private String fs;
	
	public Item(String name, int price, int plu, int wt, String fs) {
		this.name = name;
		this.price = price;
		this.plu = plu;
		this.wt = wt;
		this.fs = fs;
	}
	
	
	public Item (){}
	
	@Column(name="name", unique = true)
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="price")
	@NotNull
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Id
	@NotNull
	@Column(name="PLU", unique = true)
	public int getPlu() {
		return plu;
	}
	
	public void setPlu(int plu) {
		this.plu = plu;
	}
	
	@Column(name="weight")
	@NotNull
	public int getWt() {
		return wt;
	}
	
	public void setWt(int wt) {
		this.wt = wt;
	}
	
	@Column(name="foodstamps")
	@NotNull
	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}
	
	public static int makePlu (String name) {
		int plu = 0;
		for (int i = 0; i < 11;) {
			char c = name.charAt(i);
			int ascii = (int) c;
			plu += ascii;
		}
		
		return plu;
	}
	
}
