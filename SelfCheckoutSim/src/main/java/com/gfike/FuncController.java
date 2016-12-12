package com.gfike;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FuncController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String index (HttpServletRequest request, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error (HttpServletRequest request) {
		return "error";
	}

	@RequestMapping(value = "/error", method = RequestMethod.POST)
	public String error (HttpServletRequest request, Model model) {
		return "error";
	}
}
