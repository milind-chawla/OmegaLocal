package com.omega.controllers.config;

import com.omega.context.OmegaContextProvider;

public class RootControllerConfig implements ControllerConfig {

	@Override
	public String id() {
		return "root";
	}

	@Override
	public String name() {
		return "Root";
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
