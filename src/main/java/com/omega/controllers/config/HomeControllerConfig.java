package com.omega.controllers.config;

import com.omega.context.OmegaContextProvider;

public class HomeControllerConfig implements ControllerConfig {

	@Override
	public String id() {
		return "home";
	}

	@Override
	public String name() {
		return "Home";
	}

	@Override
	public String view(String v) {
		return id() + "/" + v;
	}

	@Override
	public String path() {
		return OmegaContextProvider.getServletContext().getContextPath() + "/" + id();
	}
}
