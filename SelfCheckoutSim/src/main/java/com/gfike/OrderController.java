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
			String plu = request.getParameter("shelf");
			if (plu.isEmpty() || plu.equalsIgnoreCase("") || plu == "") {
				String msg = "Please select an item to add to the cart";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			String msg = itemDao.findByPlu(plu) + " has been added to the cart";
			redirectAtt.addAttribute("plu", plu);
			redirectAtt.addAttribute("msg", msg);
			return String.format("redirect:newOrder%s/%s", plu, msg);		
		}
		
		String msg = "Cart is empty!";
		model.addAttribute("msg", msg);
		return "newOrder";
	}
	
	@RequestMapping(value = "/newOrder{plu}/{msg}", method = RequestMethod.GET)
	public String newOrderWitem (Model model, @PathVariable String plu, @PathVariable String msg) {
		
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		model.addAttribute("msg", msg);
		
		if (plu.contains(",")) {
			model.addAttribute("cart", StrConvert.StrToArrLst(plu));
			return "newOrder";
		}
		
		model.addAttribute("cart", itemDao.findByPlu(plu));
		return "newOrder";
	}
	
	
	@RequestMapping(value = "/newOrder{plu}/{msg}", method = RequestMethod.POST)
	public String newOrderWitem (Model model, @PathVariable String plu, @PathVariable String msg,
			HttpServletRequest request,RedirectAttributes redirectAtt) {
		
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		model.addAttribute("msg", msg);
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("add item") && !plu.contains(",")) {
			model.addAttribute("cart", itemDao.findByPlu(plu));
			ArrayList<Item> arrCart = new ArrayList<Item> ();
			arrCart.add(itemDao.findByPlu(plu));
			arrCart.add(itemDao.findByPlu(request.getParameter("shelf")));
			plu = StrConvert.ArrLstToString(arrCart);
			msg = itemDao.findByPlu(request.getParameter("shelf")).getName() + " was successully added to the cart!";
			redirectAtt.addAttribute("plu", plu);
			redirectAtt.addAttribute("msg", msg);
			return String.format("newOrder%s/%s", plu, msg);
			
		}
		
		return "newOrder";
	}
	
	}

