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
	public String newOrder (HttpServletRequest request, Model model, @ModelAttribute String cart) {
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));
			cart = i.getPlu() + ",";
			String msg = i.getName() + " was succesfully added to the cart";
			return String.format("redirect:newOrder%s/%s", cart, msg);		
		}
		
		
		String msg = "Cart is empty!";
		model.addAttribute("msg", msg);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder{cart}/{msg}", method=RequestMethod.GET)
	public String newOrder (@PathVariable String cart, @PathVariable String msg, Model model) {
		
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		
		ArrayList <Item> ArrCart = StrToArrLst(cart);
		model.addAttribute("cart", ArrCart);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder{cart}/{msg}", method=RequestMethod.POST)
	public String newOrder (@PathVariable String cart, @PathVariable String msg, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttrs) {
		ArrayList <Item> ArrCart = StrConvert.StrToArrLst(cart);
		model.addAttribute("cart", ArrCart);
		
		ArrayList<Item> items = (ArrayList<Item>) itemDao.findAll();
		model.addAttribute("items", items);
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("add item")) {
			Item i = itemDao.findByName(request.getParameter("shelf"));
			msg = i.getName() + " was succesfully added to the cart";
			cart += i.getPlu() + ",";
			redirectAttrs.addFlashAttribute("cart", cart);
			redirectAttrs.addFlashAttribute("msg", msg);
			return String.format("redirect:/newOrder%s/%s", cart, msg);
		}
		
		if (action.equalsIgnoreCase("remove item")) {
			Item i = itemDao.findByName(request.getParameter("cartSelect"));
			ArrCart.remove(i);
			if (ArrCart.isEmpty()) {
				msg = i.getName() + " was succesfully removed from the cart!";
				model.addAttribute("msg", msg);
				return "newOrder";
			}
			cart = StrConvert.ArrLstToString(ArrCart);
			msg = i.getName() + " was succesfully removed from the cart!";
			redirectAttrs.addFlashAttribute("cart", cart);
			redirectAttrs.addFlashAttribute("msg", msg);
			return String.format("redirect:/newOrder%s/%s", cart, msg);
		}
		cart = StrConvert.ArrLstToString(ArrCart);
		return String.format("redirect:/checkout%s", cart);
	}
	
	@RequestMapping(value="/checkout{cart}", method=RequestMethod.GET)
	public String checkout (Model model, @PathVariable String cart, RedirectAttributes redirectAttrs) {
		ArrayList <Item> ArrCart = StrConvert.StrToArrLst(cart);
		model.addAttribute("cart", ArrCart);
		return "checkout";
	}
	
	@RequestMapping(value="/checkout{cart}", method=RequestMethod.POST)
	public String checkout (Model model, @PathVariable String cart, HttpServletRequest request, 
			RedirectAttributes redirectAttrs) {
		ArrayList <Item> ArrCart = StrConvert.StrToArrLst(cart);
		model.addAttribute("cart", ArrCart);
		
		String action = request.getParameter("action");
		Uscan u = new Uscan();
		
		if(action.equalsIgnoreCase("scan item") && u.getAct_wt() == u.getExp_wt()) {
			Item cartItem = itemDao.findByName(request.getParameter("cart"));
			u.setExp_wt(cartItem.getWt());
			Trans t = new Trans();
			t.addItem(cartItem);
			int tuid = t.getUid();
			String msg = "Place item in bagging area";
			redirectAttrs.addFlashAttribute("msg", msg);
			redirectAttrs.addFlashAttribute("cart", cart);
			redirectAttrs.addFlashAttribute("tuid", tuid);
			return String.format("redirect:/checkout%s/%s/%s", cart, tuid, msg);
		}
		
		//need to check to see if cart select is empty
				
		String msg = "Please scan items before placing them in the bagging area";
		model.addAttribute("msg", msg);
				return "checkout";
	}

	@RequestMapping(value="/checkout{cart}/{tuid}/{msg}", method=RequestMethod.GET)
	public String checkout (Model model, @PathVariable String cart, RedirectAttributes redirectAttrs,
			@PathVariable int tuid, @PathVariable String msg) {
		ArrayList <Item> ArrCart = StrConvert.StrToArrLst(cart);
		model.addAttribute("cart", ArrCart);
		
		
		return "checkout";
	}
	
	
	
	else if(action.equalsIgnoreCase("scan item") && u.getAct_wt() != u.getExp_wt()) {
		String msg = "Place previous item in bagging area";
	}
	
	else if (action.equalsIgnoreCase("bag item") && u.getAct_wt() != u.getExp_wt()) {
		Item cartItem = itemDao.findByName(request.getParameter("cart"));
		u.setAct_wt(cartItem.getWt());
		String msg = "Scan next item";
	}
	
	else if (action.equalsIgnoreCase("bag item") && u.getAct_wt() == u.getExp_wt()) {
		String msg = "Scan previous item";
	}
	}
