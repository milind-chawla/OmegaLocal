package com.omega.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omega.controllers.util.ModelAndViewFormer;
import com.omega.exceptions.BookNotFoundException;
import com.omega.exceptions.JSONException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ModelAndView exception(HttpServletRequest req, Exception e) {
		System.out.println(">> Exception: " + e);
		
		final ModelAndView mv = new ModelAndViewFormer(new ModelAndView()).value();
		
		mv.addObject("error", e);
		mv.addObject("url", req.getRequestURL());
		mv.addObject("ste", e.getStackTrace());
        
        mv.setViewName("_common0/exp");
		
		return mv;
	}
	
	@ExceptionHandler(value = { BookNotFoundException.class })
	public ModelAndView bookNotFoundException(HttpServletRequest req, BookNotFoundException e) {
		System.out.println(">> BookNotFoundException: " + e);
		
		final ModelAndView mv = new ModelAndViewFormer(new ModelAndView()).value();
		
		mv.addObject("id", e.getBookId());
		mv.addObject("ste", e.getStackTrace());
        mv.setViewName("books/404");
		
		return mv;
	}
	
	@ExceptionHandler(value = { JSONException.class })
	@ResponseBody
	public Map<String, Object> jsonException(HttpServletRequest req, JSONException e) {
		System.out.println(">> JSONException: " + e);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("error", e);
		map.put("url", req.getRequestURL());
		map.put("ste", e.getStackTrace());
		
		return map;
	}
}
