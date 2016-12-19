package com.gfike;


import java.util.ArrayList;
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
	public String addItem(Model model) {
		ArrayList <Item> all = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("all", all);
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
			try{
			plu = Item.makePlu(name);
			} catch (StringIndexOutOfBoundsException e) {
				String n_name = name + name;
				plu = Item.makePlu(n_name);
			}
		}
		

		Item db_i = itemDao.findByName(name);
		
		
		if (db_i != null) {
			String error = "This item has already been added!";
			model.addAttribute("error", error);
			model.addAttribute("name", name);
			model.addAttribute("plu", plu);
			return "addItem";
		}
		else {
			Item i = new Item (name, price, plu, wt, fs, meas);
			itemDao.save(i);
			String msg = i.getName() + " has been succesfully added " + "with a plu of  "+ plu;
			model.addAttribute("msg", msg);
		}
		
		ArrayList <Item> all = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("all", all);
		return "addItem";
	}

}
