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
	private ArrayList<Item> trans = new ArrayList<Item> ();
	
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
		String msg = i + " was succesfully added to the cart!";
		model.addAttribute("msg", msg);
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
		Item i = itemDao.findByName(product);
		String msg = i +" was removed from the cart";
		model.addAttribute("msg", msg);
		cart.remove(i);
		return "editCart";
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.GET)
	public String checkout (Model model) {
		model.addAttribute("cart", cart);
		return "checkout";
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public String checkout (HttpServletRequest request, Model model) {
		model.addAttribute("cart", cart);
		Item scanned = itemDao.findByName(request.getParameter("scan"));
		Item bagged = itemDao.findByName(request.getParameter("bag"));
		
		if(bagged != null) {
			String msg = bagged + " must be scanned first";
			model.addAttribute("msg", msg);
		}
		
		return "checkout";
	}
}
