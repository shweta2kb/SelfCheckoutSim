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
}
