package com.omega.controllers.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.omega.controllers.AbstractController;
import com.omega.controllers.config.BooksControllerConfig;
import com.omega.controllers.config.ControllerConfig;
import com.omega.controllers.config.HomeControllerConfig;
import com.omega.controllers.config.RootControllerConfig;

public class ModelAndViewFormer {
	
	private AbstractController ctl = null;
	private ModelAndView mv = null;

	public ModelAndViewFormer(AbstractController ctl, ModelAndView mv) {
		this.ctl = ctl;
		this.mv = mv;
		this.fillValues();
		this.fillLinks();
	}

	public ModelAndView value() {
		return mv;
	}
	
	private void fillValues() {
		final ControllerConfig config = ctl.config();
		
		mv.addObject("id", config.id());
        mv.addObject("name", config.name());
        mv.addObject("path", config.path());
	}
	
	private void fillLinks() {
		final List<Map<String, String>> links = new ArrayList<>();
		
		Map<String, String> map = null;
		ControllerConfig config = null;
		
		//----------------------------------------------------
		map = new HashMap<>();
		config = new RootControllerConfig();
		
		map.put("id", config.id());
		map.put("name", config.name());
		map.put("path", config.path());
		
		links.add(map);
		
		//----------------------------------------------------
		map = new HashMap<>();
		config = new HomeControllerConfig();
		
		map.put("id", config.id());
		map.put("name", config.name());
		map.put("path", config.path());
		
		links.add(map);
		
		//----------------------------------------------------
		map = new HashMap<>();
		config = new BooksControllerConfig();
		
		map.put("id", config.id());
		map.put("name", config.name());
		map.put("path", config.path());
		
		links.add(map);
		
		this.mv.addObject("links", links);
	}
}
