package com.omega.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/home" })
public class HomeController {

    @RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public String root(HttpServletRequest req) {
    	return "redirect:/home/index";
    }
    
    @RequestMapping(value = { "/index", "/index/" }, method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest req){
    	ModelAndView mv = new ModelAndView();
    	
	    mv.setViewName("home/index");
	    
	    return mv;
    }
}
