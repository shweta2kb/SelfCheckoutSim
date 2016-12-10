package com.gfike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {
	private ArrayList <Item> cart = new ArrayList<Item>();
	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (Model model){
		Scanner s = null;
		try {
			//file pathname on Surface: "C:\\Users\\aerot\\coding\\git-items\\SelfCheckoutSim\\grocery-items.txt"
			s = new Scanner(new File("C:\\Users\\aerot\\coding\\git-items\\SelfCheckoutSim\\grocery-items.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		
		model.addAttribute("list", list);
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder",method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("additem")) {
			
		}
		return "newOrder";
		
	}
}
