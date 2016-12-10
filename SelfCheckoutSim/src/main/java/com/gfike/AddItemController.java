package com.gfike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AddItemController{
	@Autowired
	private ItemDao itemDao;
	
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public String addItem() {
		return "addItem";
	}
	
@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String addItem(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String plu = request.getParameter("plu");
		int wt = Integer.parseInt(request.getParameter("wt"));
		String fs = request.getParameter("fs");
		String meas = request.getParameter("meas");
		
		if (plu.equals("") || plu == null) {

			plu = Item.makePlu(name);
		}
		

		Item db_i = itemDao.findByName(name);
		
		
		if (db_i != null) {
			String error = "This item has already been added";
			model.addAttribute("error", error);
			model.addAttribute("name", name);
			model.addAttribute("plu", plu);
			return "addItem";
		}
		else {
			Item i = new Item (name, price, plu, wt, fs, meas);
			itemDao.save(i);
			String msg = "Item has been succesfully added!";
			model.addAttribute("msg", msg);
		}
		return "addItem";
	}
}
