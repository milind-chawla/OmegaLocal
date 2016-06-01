package com.omega.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "", "/" })
public class RootController extends AbstractController {

	@RequestMapping(method = { RequestMethod.GET })
	public String root() {
		return "redirect:/home/index";
	}

	@Override
	public Config config() {
		return new RootControllerConfig();
	}
	
	public static class RootControllerConfig implements Config {

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
