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
	@Autowired
	private Trans t;
	
	private ArrayList <Item> cart = new ArrayList <Item> ();
	private String msg;
	
	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (Model model){
		ArrayList <Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		ArrayList <Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));
			cart.add(i);
			msg = i.getName() +" was sucessfully added to the cart!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		else if (action.equalsIgnoreCase("remove item") && cart.isEmpty()) {
			msg = "Cart has nothing to remove!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		else if (action.equalsIgnoreCase("remove item") && !cart.isEmpty()) {
			Item i = itemDao.findByName(request.getParameter("cartSelect"));
			cart.remove(i);
			msg = i.getName() + " has been removed from the cart!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		return "checkout";
		}
	
	@RequestMapping(value="/checkout", method=RequestMethod.GET) 
		public String checkout (Model model) {
			model.addAttribute("cart", cart);
			model.addAttribute("trans", t);
			return "checkout";
		}
	}
