package com.gfike;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {
	@Autowired
	private ItemDao itemDao;
	private ArrayList<Item> cart = new ArrayList<Item>();
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String index (HttpServletRequest request, Model model) {
		return "index";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (Model model){
		ArrayList<Item> allItems = (ArrayList<Item>) itemDao.findAll();
		ArrayList<String> list = new ArrayList<String>();

		for(Item i : allItems) {
			list.add(i.getName());
		}
		
		ArrayList<String> cart_lst = new ArrayList<String>();
		for (Item i : cart) {
			cart_lst.add(i.getName());
		}
		model.addAttribute("list", list);
		model.addAttribute("cart_lst", cart_lst);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder",method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		
		Item i = itemDao.findByName(request.getParameter("items"));
		if(action.equalsIgnoreCase("additem")) {
			cart.add(i);
			return "newOrder";
		}
		
		if (action.equalsIgnoreCase("removeItem")) {
			Item j = itemDao.findByName(request.getParameter("cart"));
			cart.remove(j);
		}
		return "newOrder";
		
	}
}
