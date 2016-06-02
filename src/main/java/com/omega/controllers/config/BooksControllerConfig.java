package com.omega.controllers.config;

import com.omega.context.OmegaContextProvider;

public class BooksControllerConfig implements ControllerConfig {

	@Override
	public String id() {
		return "books";
	}

	@Override
	public String name() {
		return "Books";
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
