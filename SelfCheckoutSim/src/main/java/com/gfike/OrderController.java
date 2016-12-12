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
		ArrayList <Item> products = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("products", products);
		model.addAttribute("cart", cart);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder",method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		String item_add = request.getParameter("add");
		String item_remove = request.getParameter("remove");
		
		Item i = itemDao.findByName(item_add);
		Item j = itemDao.findByName(item_remove);
		
		cart.add(i);
		cart.remove(j);
		
		model.addAttribute("cart", cart);

		return "newOrder";
		
	}
}
