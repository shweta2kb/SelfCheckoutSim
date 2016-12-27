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
	
	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (Model model){
		model.addAttribute("cart", cart);
		ArrayList <Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		ArrayList <Item> items = (ArrayList<Item>) itemDao.findAll();
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));	
			cart.add(i);
			model.addAttribute("cart", cart);
			String msg = i.getName() + " was succesfully added to the cart!";
			model.addAttribute("msg", msg);
			model.addAttribute("items", items);
			return "newOrder";
		}
		if (action.equalsIgnoreCase("remove item")) {
			model.addAttribute("items", items);
			Item i = itemDao.findByName(request.getParameter("cartSelect"));
			cart.remove(i);
			String msg = i.getName() + " was succesfully removed from the cart!";
			model.addAttribute("msg", msg);
			model.addAttribute("cart", cart);
			return "newOrder";
		}
		return "redirect:checkout";
		
	}
	
	
	@RequestMapping(value="/checkout", method=RequestMethod.GET)
	public String checkout (Model model) {
		model.addAttribute("cart", cart);
		return "checkout";
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public String checkout (HttpServletRequest request, Model model) {
		model.addAttribute("cart", cart);
		String action = request.getParameter("action");
		Uscan u = new Uscan();
		String msg = "";
		
		if (action.equalsIgnoreCase("bag item") && u.getExp_wt() != u.getAct_wt()) {
			msg += ""
		}
		return "checkout";
	}
}
