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
public class OrderController {
	
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private TransDao transDao;

	
	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (Model model) {
		
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrder (HttpServletRequest request, Model model, RedirectAttributes redirectAtt) {
		
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		
		if (session.isNew() && action.equalsIgnoreCase("add item")) {
			ArrayList <Item> cart = new ArrayList <Item>();
			cart.add(itemDao.findByPlu(request.getParameter("shelf")));
			if (request.getParameter("shelf").isEmpty() || 
					request.getParameter("shelf").equalsIgnoreCase("") 
					|| request.getParameter("shelf") == "") {
				String msg = "Please select an item to add to the cart";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			String msg = itemDao.findByPlu(request.getParameter("shelf")).getName() + " has been added to the cart";
			model.addAttribute("msg", msg);
			session.setAttribute("cart",cart);
			model.addAttribute("cart", session.getAttribute("cart"));
			return "newOrder";
		}
		
		/*what doesn't work 
		session.getAttribute("cart") != null
		*/
		
		else if(!session.isNew() && action.equalsIgnoreCase("add item")) {
			ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart"); 
			cart.add(itemDao.findByPlu(request.getParameter("shelf")));
			String msg = itemDao.findByPlu(request.getParameter("shelf")).getName() + " has been added to the cart";
			model.addAttribute("msg", msg);
			model.addAttribute("cart", cart);
			return "newOrder";
		}
		
		else if(!session.isNew() && action.equalsIgnoreCase("remove item")) {
			ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart"); 
			cart.remove(itemDao.findByPlu(request.getParameter("shelf")));
			String msg = itemDao.findByPlu(request.getParameter("shelf")).getName() + " has been removed the cart";
			model.addAttribute("msg", msg);
			model.addAttribute("cart", cart);
			return "newOrder";
		}
		
		else if(!session.isNew() && action.equalsIgnoreCase("checkout")) {
			ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart"); 
			cart.remove(itemDao.findByPlu(request.getParameter("shelf")));
			String msg = itemDao.findByPlu(request.getParameter("shelf")).getName() + " has been removed the cart";
			model.addAttribute("msg", msg);
			model.addAttribute("cart", cart);
			return "newOrder";
		}
		
		String msg = "Cart is empty!";
		model.addAttribute("msg", msg);
		return "newOrder";
	}
	
	
	}

