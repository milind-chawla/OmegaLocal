package com.omega.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omega.controllers.config.ControllerConfig;
import com.omega.controllers.config.RootControllerConfig;

@Controller
@RequestMapping(value = { "", "/" })
public class RootController extends AbstractController {

	@RequestMapping(method = { RequestMethod.GET })
	public String root() {
		return "redirect:/home/index";
	}

	@Override
	public ControllerConfig config() {
		return new RootControllerConfig();
	}
}
