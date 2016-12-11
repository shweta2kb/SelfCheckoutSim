package com.gfike;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {

	@RequestMapping(value="/newOrder", method=RequestMethod.GET)
	public String newOrder (){
		ArrayList <String> lst;
		return "newOrder";
	}
	
	@RequestMapping(value="/newOrder",method=RequestMethod.POST)
	public String newOrder(HttpServletRequest request, Model model) {
		return "newOrder";
		
	}
}
