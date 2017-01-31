package com.gfike;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));
			if (i == null) {
				String msg = "Please select an item from the list";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			//comma needs to be left off so first item can be found
			ArrayList <Item> arr_cart = new ArrayList<Item>();
			arr_cart.add(i);
			String cart = StrConvert.ArrLstToString(arr_cart);
			String msg = i.getName() +" was succesffuly added to the cart!";
			redirectAtt.addAttribute("cart", cart);
			redirectAtt.addAttribute("msg", msg);
			return String.format("redirect:newOrder%s/%s", cart, msg);		
		}
		
		String msg = "Cart is empty!";
		model.addAttribute("msg", msg);
		return "newOrder";
	}
	
	@RequestMapping(value = "/newOrder{cart}/{msg}", method = RequestMethod.GET)
	public String newOrderwithCart (HttpServletRequest request, Model model, RedirectAttributes redirectAtt,
			@PathVariable String cart, @PathVariable String msg) {
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		
		ArrayList<Item> arr_cart = StrConvert.StrToArrLst(cart);
		model.addAttribute("cart", arr_cart);
		return String.format("newOrder%s/%s", cart, msg);
	}
}
