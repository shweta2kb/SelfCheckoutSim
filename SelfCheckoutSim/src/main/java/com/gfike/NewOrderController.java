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
		
		if(session.getAttribute("cart") == null || session.getAttribute("cart") == "") {
			
			if (action.equalsIgnoreCase("add item")) {
				ArrayList<Item> cart = new ArrayList<Item>();
				session.setAttribute("cart", cart);
				
			}
		}
	}
	
	
	
	}

