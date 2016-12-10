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
	private String plu;
	private int wt;
	private String fs;
	private String meas;
	private int lTax;
	private int hTax;

	public Item (){}

	public Item(String name, int price, String plu, int wt, String fs, String meas) {
		this.name = name;
		this.price = price;
		this.plu = plu;
		this.wt = wt;
		this.fs = fs;
		this.meas = meas;
	}

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
	public String getPlu() {
		return plu;
	}
	
	public void setPlu(String plu) {
		this.plu = plu;
	}
	
	@Column(name="weight")
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
	
	public static String makePlu (String name) {
		String temp_plu = "";
		int  i = 0;
        while (temp_plu.length() < 11) {
                char c = name.charAt(i);
                int ascii = (int) c;
                temp_plu += ascii;
                i ++;
        }
		
		return temp_plu;
	}
	
	@Column(name="UnitOfMeasure")
	@NotNull
	public String getMeas() {
		return meas;
	}


	public void setMeas(String meas) {
		this.meas = meas;
	}


	
	public String toString(){
		return this.name + " " + this.plu + " " + this.price + " per " + this.meas + " " + this.fs;
	}
	
	
	public boolean equals(Item i) {
		if (this.plu == i.getPlu()) {
			return true;
		}
		return false;
	}


	public float getlTax() {
		return lTax;
	}


	public void setlTax(int lTax) {
		this.lTax = lTax;
	}


	public float gethTax() {
		return hTax;
	}


	public void sethTax(int hTax) {
		this.hTax = hTax;
	}
	
	public void deterTax () {
		if(this.fs.equals("y")) {
			this.lTax = price * 4;
		}
		
		else {
			this.hTax = price * 9;
		}
	}
	
}
