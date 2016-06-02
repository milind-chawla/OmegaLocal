package com.omega.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.omega.controllers.config.ControllerConfig;
import com.omega.controllers.config.HomeControllerConfig;
import com.omega.controllers.util.ModelAndViewFormer;

@Controller
@RequestMapping(value = { "/home" })
public class HomeController extends AbstractController {

    @RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public String root(HttpServletRequest req) {
    	return "redirect:/home/index";
    }
    
    @RequestMapping(value = { "/index", "/index/" }, method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest req) {
    	ModelAndView mv = new ModelAndViewFormer(this, new ModelAndView()).value();
    	
	    mv.setViewName("home/index");
	    
	    return mv;
    }

	@Override
	public ControllerConfig config() {
		return new HomeControllerConfig();
	}
}
