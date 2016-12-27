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


	public int getlTax() {
		return lTax;
	}


	public void setlTax(int lTax) {
		this.lTax = lTax;
	}


	public int gethTax() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fs == null) ? 0 : fs.hashCode());
		result = prime * result + hTax;
		result = prime * result + lTax;
		result = prime * result + ((meas == null) ? 0 : meas.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((plu == null) ? 0 : plu.hashCode());
		result = prime * result + price;
		result = prime * result + wt;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (fs == null) {
			if (other.fs != null)
				return false;
		} else if (!fs.equals(other.fs))
			return false;
		if (hTax != other.hTax)
			return false;
		if (lTax != other.lTax)
			return false;
		if (meas == null) {
			if (other.meas != null)
				return false;
		} else if (!meas.equals(other.meas))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (plu == null) {
			if (other.plu != null)
				return false;
		} else if (!plu.equals(other.plu))
			return false;
		if (price != other.price)
			return false;
		if (wt != other.wt)
			return false;
		return true;
	}
	
	
	
}
