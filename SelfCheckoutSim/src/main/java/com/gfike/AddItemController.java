package com.gfike;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AddItemController{
	@Autowired
	private ItemDao itemDao;
	
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public String addItem() {
		return "addItem";
	}
	
@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String addItem(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int plu = Integer.parseInt(request.getParameter("plu"));
		int wt = Integer.parseInt(request.getParameter("wt"));
		String fs = request.getParameter("fs");
		String meas = request.getParameter("meas");
		
		if (plu == 0) {

			plu += Item.makePlu(name);
		}
		Item i = new Item();
		if (wt == 0 ){
		i = new Item(name, price, plu, fs, meas);
		}
		i = new Item(name, price, plu,wt, fs, meas);
		
		if (i.equals(itemDao.findByPlu(plu))) {
			String error = "This item has already been added \n";
			model.addAttribute("error", error);
			return "addItem";
		}
		else {
			itemDao.save(i);
			String msg = "Item has been succesfully added! \n";
			model.addAttribute("msg", msg);
		}
		return "addItem";
	}
}
