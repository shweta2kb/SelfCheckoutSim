package com.gfike;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NewOrderController {
	
	@Autowired
	private ItemDao itemDao;
	

	
	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("items") == null || session.getAttribute("items") == "") {
			ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
			model.addAttribute("items", items);
			session.setAttribute("items", items);
			return "newOrder";
		}
		
		ArrayList <Item> items = (ArrayList <Item>) session.getAttribute("items");
		model.addAttribute("items", items);
		
		if (session.getAttribute("cart") != null || session.getAttribute("cart") != "") {
			ArrayList <Item> cart = (ArrayList<Item>) session.getAttribute("cart");
			model.addAttribute("cart", cart);
		}
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrder (HttpServletRequest request, Model model, RedirectAttributes redirectAtt) {
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String msg = "";
		
		if (session.getAttribute("items") == null || session.getAttribute("items") == "") {
			ArrayList<Item> items =(ArrayList<Item>) itemDao.findAll();
			model.addAttribute("items", items);
			session.setAttribute("items", items);
		}
		
		ArrayList <Item> items = (ArrayList <Item>) session.getAttribute("items");
		model.addAttribute("items", items);
		
		if (session.getAttribute("cart") == null && action.equalsIgnoreCase("add item")) {
			if (!Tools.checkUserSelection(request, "shelf")) {
				msg = "Please select an item from the list to add to the cart";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			ArrayList<Item> cart = new ArrayList<Item>();
			Item i = itemDao.findByPlu("shelf");
			cart.add(i);
			session.setAttribute("cart", cart);
			model.addAttribute("cart", cart);
			msg = i.getName() + " has been added to the cart!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		
		else if (session.getAttribute("cart") != null && action.equalsIgnoreCase("add item")) {
			ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");
			if (cart == null|| !Tools.checkUserSelection(request, "shelf")) {
				msg = "Please select an item from the list to add to the cart";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			Item i = itemDao.findByPlu("shelf");
			cart.add(i);
			session.setAttribute("cart", cart);
			model.addAttribute("cart", cart);
			msg = i.getName() + " has been added to the cart!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		else if (session.getAttribute("cart") != null && action.equalsIgnoreCase("remove item")) {
			ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");
			if (cart.isEmpty() || !Tools.checkUserSelection(request, "cartSelect")) {
				msg = "Cart is empty";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			Item i = itemDao.findByPlu("cartSelect");
			cart.remove(i);
			session.setAttribute("cart", cart);
			model.addAttribute("cart", cart);
			msg = i.getName() + " has been added to the cart!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		return "checkout";
	}
	}
