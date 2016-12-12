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
		model.addAttribute("cart", cart);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		String product = request.getParameter("product");
		
		Item i = itemDao.findByName(product);
		
		if(i == null) {
			String error = "Item was not found. Enter another item.";
			model.addAttribute("error", error);
			return "newOrder";
		}
		
		cart.add(i);
		model.addAttribute("cart", cart);
		
		return "newOrder";
	}
	
	@RequestMapping(value="/editCart", method=RequestMethod.GET)
	public String editCart (Model model) {
		model.addAttribute("cart", cart);
		return "editCart";
	}
	
	@RequestMapping(value="/editCart", method=RequestMethod.POST)
	public String editCart (HttpServletRequest request, Model model) {
		model.addAttribute("cart", cart);
		String product = request.getParameter("product");
		cart.remove(itemDao.findByName(product));
		return "editCart";
	}
	
	
}
