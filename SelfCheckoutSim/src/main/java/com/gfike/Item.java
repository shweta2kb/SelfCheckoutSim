package com.gfike;


//what's in the db
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
	private String meas;
	
	public Item(String name, int price, int plu, String fs, String meas) {
		this.name = name;
		this.price = price;
		this.plu = plu;
		this.fs = fs;
		this.meas = meas;
	}
	
	
	public Item (){}
	
	


	public Item(String name, int price, int plu, int wt, String fs, String meas) {
		super();
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
	public int getPlu() {
		return plu;
	}
	
	public void setPlu(int plu) {
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
	
	public static int makePlu (String name) {
		int plu = 0;
		for (int i = 0; i < 11; i++) {
			char c = name.charAt(i);
			int ascii = (int) c;
			plu += ascii;
		}
		
		return plu;
	}
	
	@Column(name="UnitofMeasure")
	@NotNull
	public String getMeas() {
		return meas;
	}


	public void setMeas(String meas) {
		this.meas = meas;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + plu;
		result = prime * result + price;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (plu != other.plu)
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	
	public String toString(){
		return this.name + " " + this.plu + " " + this.price + " per " + this.meas + " " + this.fs;
	}
	
}
