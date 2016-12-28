package com.gfike;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private TransDao transDao;
	
	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (Model model){
		ArrayList <Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		ArrayList <Item> items = (ArrayList<Item>) itemDao.findAll();
		String action = request.getParameter("action");
		ArrayList <Item> cart = new ArrayList<Item>();
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));	
			cart.add(i);
			model.addAttribute("cart", cart);
			String msg = i.getName() + " was succesfully added to the cart!";
			model.addAttribute("msg", msg);
			model.addAttribute("items", items);
			return String.format("redirect:newOrder%s", msg);
		}
		String msg = "Add items to the cart to checkout or remove them";
		model.addAttribute("items", items);
		model.addAttribute("msg", msg);
		return "newOrder";
		
	}
	
	@RequestMapping(value = "/newOrder/{msg}", method=RequestMethod.GET)
	public String newOrder(@PathVariable String msg, Model model) {
		model.addAttribute("msg", msg);
		return "newOrder";
	}
	
	
	@RequestMapping(value = "/newOrder/{msg}", method=RequestMethod.POST)
	public String newOrder (@PathVariable String msg, Model model, HttpServletRequest request) {;
		model.addAttribute("msg", msg);
		return "newOrder";
	}
	
	
}
