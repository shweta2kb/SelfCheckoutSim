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
	
	//when page is first loaded
	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (Model model){
		ArrayList <Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		return ("newOrder");
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		ArrayList <Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));
			ArrayList <Item> cart = new ArrayList<Item> ();
			cart.add(i);
			String msg = i.getName() +" was sucessfully added to the cart!";
			model.addAttribute("msg", msg);
			return String.format("redirect:newOrder%s/%s/%s", items, cart, msg);
		}
		return String.format("redirect:newOrder%s", items);

	}
	
	@RequestMapping(value="/newOrder{items}", method=RequestMethod.GET)
	public String newOrder (Model model, @PathVariable ArrayList <Item> items){
		model.addAttribute("items", items);
		return String.format("redirect:newOrder%s", items);
	}
	
	
	@RequestMapping(value="/newOrder{items}", method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model, @PathVariable ArrayList <Item> items) {
		model.addAttribute("items", items);
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));
			ArrayList <Item> cart = new ArrayList<Item> ();
			cart.add(i);
			String msg = i.getName() +" was sucessfully added to the cart!";
			model.addAttribute("msg", msg);
			return String.format("redirect:newOrder%s/%s/%s", items, cart, msg);
		}
		
		String msg = "Cart is empty!";
		model.addAttribute("msg", msg);
		return String.format("redirect:newOrder%s", items);

	}
	
	@RequestMapping(value="/newOrder{items}/{cart}/{mesg}", method=RequestMethod.GET)
	public String newOrder (@PathVariable ArrayList <Item> items, @PathVariable ArrayList <Item> cart,
			@PathVariable String msg, Model model, HttpServletRequest request) {
		model.addAttribute("cart", cart);
		model.addAttribute("items", items);
		model.addAttribute("msg", msg);
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));
			cart.add(i);
			msg = i.getName() +" was sucessfully added to the cart!";
			model.addAttribute("msg", msg);
			return String.format("redirect:newOrder%s/%s/%s", items, cart, msg);
		}
		
		else if (action.equalsIgnoreCase("remove item") && cart.isEmpty()) {
			msg = "Cart has nothing to remove!";
			model.addAttribute("msg", msg);
			return String.format("redirect:newOrder%s/%s/%s", items, cart, msg);
		}
		
		else if (action.equalsIgnoreCase("remove item") && !cart.isEmpty()) {
			Item i = itemDao.findByName(request.getParameter("cartSelect"));
			cart.remove(i);
			msg = i.getName() + " has been removed from the cart!";
			model.addAttribute("msg", msg);
			return String.format("redirect:newOrder%s/%s/%s", items, cart, msg);
		}
		
		return String.format("redirect:checkout%s", cart);

	}
	
	}
