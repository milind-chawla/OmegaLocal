package com.omega.context;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

public class OmegaContextProvider implements ApplicationContextAware, ServletContextAware {
	private static ServletContext servletContext = null;
	private static ApplicationContext applicationContext = null;

	@Override
	public void setServletContext(ServletContext servletContext) {
		OmegaContextProvider.servletContext = servletContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		OmegaContextProvider.applicationContext = applicationContext;
	}

	public static ServletContext getServletContext() {
		return OmegaContextProvider.servletContext;
	}

	public static ApplicationContext getApplicationContext() {
		return OmegaContextProvider.applicationContext;
	}
}
