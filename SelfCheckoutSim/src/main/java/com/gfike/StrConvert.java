package com.gfike;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class StrConvert {
@Autowired
static
ItemDao itemDao;

public StrConvert () {}
	public static ArrayList<Item> StrToArrLst (String str) {
		String arr [] = str.split(",");
		ArrayList<Item> lst = new ArrayList<Item>();
		for (int i = 0; i < arr.length; i ++) {
			Item j = itemDao.findByPlu(arr[i]);
			lst.add(j);
		}
		return lst;
	}
	
	public static String ArrLstToString (ArrayList<Item> i) {
		String str = "";
		for (Item j : i) {
			str += j.getPlu() +",";
		}
		return str;
	}
}
