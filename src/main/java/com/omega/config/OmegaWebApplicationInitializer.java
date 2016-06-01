package com.omega.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.Sm2TagRuleBundle;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class OmegaWebApplicationInitializer implements WebApplicationInitializer {
	
	public static final int MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5;
	public static final int FILE_SIZE_THRESHOLD = 1024 * 1024;
	public static final long MAX_REQUEST_SIZE = -1L;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		registerCharacterEncodingFilter(servletContext);
        
        registerContextLoaderListener(servletContext);
        registerDispatcherServlet(servletContext);
        // registerSpringSecurityFilterChain(servletContext);
		registerSiteMeshFilter(servletContext);
	}
	
	private void registerCharacterEncodingFilter(ServletContext servletContext) {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
        
		FilterRegistration.Dynamic filter = servletContext.addFilter("characterEncoding", characterEncodingFilter);
		filter.addMappingForUrlPatterns(null, false, "/*");
	}
	
	private void registerContextLoaderListener(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(OmegaCoreConfig.class);
		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.addListener(new RequestContextListener());
	}
	
	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(OmegaWebApplicationConfig.class);
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		dispatcher.setMultipartConfig(new MultipartConfigElement(null, MAX_FILE_UPLOAD_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
	}
	
	private void registerSiteMeshFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic filter = servletContext.addFilter("sitemesh", new ConfigurableSiteMeshFilter() {
	        public void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
	        	builder.addTagRuleBundle(new Sm2TagRuleBundle());
	            
	            builder.addDecoratorPath("/home/*", "/WEB-INF/views/jsp/_layout0/base.jsp")
	                .addDecoratorPath("/books/*", "/WEB-INF/views/jsp/_layout0/base.jsp")
	                .addDecoratorPath("/angularseed/*", "/WEB-INF/views/jsp/_layout0/base.jsp");
	        }
	    });
	    
	    filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");
	}
}
