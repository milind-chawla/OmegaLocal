package com.omega.controllers;

import com.omega.controllers.config.ControllerConfig;

public abstract class AbstractController {

	public static final String NO_VALUE = "--x--";
	
	public abstract ControllerConfig config();
}
