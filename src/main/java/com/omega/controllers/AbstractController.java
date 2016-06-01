package com.omega.controllers;

public abstract class AbstractController {

	public static final String NO_VALUE = "--x--";
	
	public interface Config {
		
		public String id();
		
		public String name();
		
		public String view(String v);
		
		public String apath();
		
		public String rpath();
	}
	
	public abstract Config config();
}
