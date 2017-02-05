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
		
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("items", items);
		
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrder (HttpServletRequest request, Model model, RedirectAttributes redirectAtt) {
		
		HttpSession session = request.getSession();
		
//		ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items"); 
//		model.addAttribute("items", items);
		
		String action = request.getParameter("action");
		
		//when the cart first starts off
		if (session.getAttribute("cart") == null &&  action.equalsIgnoreCase("add item")) {
			ArrayList <Item> cart = new ArrayList <Item>();
			cart.add(itemDao.findByPlu(request.getParameter("shelf")));
			
			if (!Tools.checkUserSelection(request, request.getParameter("shelf"))) {
				String msg = "Please select an item fromm the list to add";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			
			String msg = itemDao.findByPlu(request.getParameter("shelf")).getName() + " has been added to the cart";
			model.addAttribute("msg", msg);
			session.setAttribute("cart",cart);
			model.addAttribute("cart", session.getAttribute("cart"));
			return "newOrder";
		}
		
		/* 
		
		what doesn't work : 
		session.getAttribute("cart") != null
		
		works as long as checkout on empty cart isn't clicked first:
		!session.isNew() && action.equalsIgnoreCase("add item")
		
		*/
		
		//if the cart has items already in it
		else if(session.getAttribute("cart") != null &&  action.equalsIgnoreCase("add item")) {
			ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart"); 
			cart.add(itemDao.findByPlu(request.getParameter("shelf")));
			
			if (!Tools.checkUserSelection(request, request.getParameter("shelf"))) {
				String msg = "Please select an item fromm the list to add";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			
			String msg = itemDao.findByPlu(request.getParameter("shelf")).getName() + " has been added to the cart";
			model.addAttribute("msg", msg);
			model.addAttribute("cart", cart);
			return "newOrder";
		}
		
		//to a remove an item from the cart
		//TODO add a clear cart button, clear cart once user goes to checkout and comes back?
		else if(session.getAttribute("cart") != null && action.equalsIgnoreCase("remove item")) {
			ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart"); 
			cart.remove(itemDao.findByPlu(request.getParameter("shelf")));
			
			if (!Tools.checkUserSelection(request, request.getParameter("shelf"))) {
				String msg = "Please select an item fromm the cart to remove.";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			
			String msg = itemDao.findByPlu(request.getParameter("shelf")).getName() + " has been removed the cart";
			model.addAttribute("msg", msg);
			model.addAttribute("cart", cart);
			return "newOrder";
		}
		
		else if(session.getAttribute("cart") != null && action.equalsIgnoreCase("checkout")) {
			return "checkout";
		}
		
		String msg = "Cart is empty!";
		model.addAttribute("msg", msg);
		return "newOrder";
	}
	
	
	
	}

