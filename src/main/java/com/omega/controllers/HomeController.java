package com.omega.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/home" })
public class HomeController extends AbstractController {

    @RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public String root(HttpServletRequest req) {
    	return "redirect:/home/index";
    }
    
    @RequestMapping(value = { "/index", "/index/" }, method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest req) {
    	ModelAndView mv = new ModelAndView();
    	
	    mv.setViewName("home/index");
	    
	    return mv;
    }
    
    @Override
	public Config config() {
		return new HomeControllerConfig();
	}
	
	public static class HomeControllerConfig implements Config {

		@Override
		public String id() {
			return null;
		}

		@Override
		public String name() {
			return null;
		}

		@Override
		public String view(String v) {
			return null;
		}

		@Override
		public String apath() {
			return null;
		}

		@Override
		public String rpath() {
			return null;
		}
	}
}
