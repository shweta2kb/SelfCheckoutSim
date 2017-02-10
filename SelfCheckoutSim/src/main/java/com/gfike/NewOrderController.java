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
	public String newOrderGet (Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("items") == null || session.getAttribute("items") == "") {
			ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
			model.addAttribute("items", items);
			session.setAttribute("items", items);
		}
		
		else {
			ArrayList <Item> items = (ArrayList<Item>) session.getAttribute("items");
			model.addAttribute("items", items);
		}
		
		if (session.getAttribute("cart") != null || session.getAttribute("cart") != "") {
			ArrayList <Item> cart = (ArrayList<Item>) session.getAttribute("cart");
			model.addAttribute("cart", cart);
		}
		

		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder", method=RequestMethod.POST)
	public String newOrderPost (HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String msg = "";
		
		//why does post need this?
		if (session.getAttribute("items") == null || session.getAttribute("items") == "") {
			ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
			model.addAttribute("items", items);
			session.setAttribute("items", items);
		}
		
		else {
			ArrayList <Item> items = (ArrayList<Item>) session.getAttribute("items");
			model.addAttribute("items", items);
		}
		
		
		if (session.getAttribute("cart") != null || session.getAttribute("cart") != "") {
			ArrayList <Item> cart = (ArrayList<Item>) session.getAttribute("cart");
			model.addAttribute("cart", cart);
		}
		
		//cleaner way?
		
		//empty cart, user has selected add item, there is an item to add
		if (session.getAttribute("cart") == null && action.equalsIgnoreCase("add item") 
				&& Tools.checkUserSelection(request, "shelf")) {
			ArrayList <Item> cart = new ArrayList <Item> ();
			Item i = itemDao.findByPlu(request.getParameter("shelf"));
			cart.add(i);
			model.addAttribute("cart", cart);
			session.setAttribute("cart", cart);
			msg = i.getName() + " has been added to the cart!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		//not empty cart, user has selected add item, there is an item to add
		else if (session.getAttribute("cart") != null && action.equalsIgnoreCase("add item") 
				&& Tools.checkUserSelection(request, "shelf")) {
			ArrayList <Item> cart = (ArrayList <Item>) session.getAttribute("cart");
			Item i = itemDao.findByPlu(request.getParameter("shelf"));
			cart.add(i);
			model.addAttribute("cart", cart);
			session.setAttribute("cart", cart);
			msg = i.getName() + " has been added to the cart!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		//user has selected add item and there is no item to add
		else if (action.equalsIgnoreCase("add item") && !Tools.checkUserSelection(request, "shelf")) {
			msg = "Selection is empty!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		//empty cart, user has either selected remove item or checkout
		else if ((action.equalsIgnoreCase("remove item") || action.equalsIgnoreCase("checkout")) 
				&& (session.getAttribute("cart") == null || session.getAttribute("cart") == "") ){
			msg = "Cart is empty!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		//user has selected remove item, selection is empty
		else if (action.equalsIgnoreCase("remove item") && !Tools.checkUserSelection(request, "cartSelect")) {
			msg = "Selection is empty!";
			model.addAttribute("msg", msg);
			return "newOrder";
		}
		
		//user has selected remove item, selection is not empty
		else if (action.equalsIgnoreCase("remove item") && Tools.checkUserSelection(request, "cartSelect")) {
			ArrayList <Item> cart = (ArrayList <Item>) session.getAttribute("cart");
			Item i = itemDao.findByPlu("cartSelect");
			msg = i.getName() + " has been removed!";
			int r_index = cart.indexOf(i);
			cart.remove(r_index + 1); //why does this need to happen?
			model.addAttribute("cart", cart);
			session.setAttribute("cart", cart);
			//the following works
			msg = "An item has been removed form the cart!";
			model.addAttribute("msg", msg); 
			
			//i.getName() seems to be the problem
			return "newOrder";
		}
		
		return "checkout";
	}
	}

//what doesn't work

/*			ArrayList <Item> cart = (ArrayList <Item>) session.getAttribute("cart");
Item i = itemDao.findByPlu("cartSelect");
msg = i.getName() + " has been removed!";
cart.remove(i);
model.addAttribute("msg", msg);
model.addAttribute("cart", cart);
session.removeAttribute("cart");
session.setAttribute("cart", cart);
return "newOrder";
*/


