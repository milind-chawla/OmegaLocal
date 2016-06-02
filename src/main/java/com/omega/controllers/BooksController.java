package com.omega.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.omega.controllers.config.BooksControllerConfig;
import com.omega.controllers.config.ControllerConfig;
import com.omega.controllers.util.ModelAndViewFormer;
import com.omega.domain.Book;
import com.omega.service.BookService;
import com.omega.util.StringToInt;

@Controller
@RequestMapping(value = { "/books" })
public class BooksController extends AbstractController {

	@Autowired
    private BookService bookService = null;
	
	@Override
	public ControllerConfig config() {
		return new BooksControllerConfig();
	}
	
	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public String root(HttpServletRequest req) {
		return "redirect:/books/index";
	}
	
	@RequestMapping(value = { "index", "/index" }, method = { RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @RequestParam(value="page", required=false) final String _page) {
		final int page = new StringToInt(_page).value() < 0 ? 1 : new StringToInt(_page).value();
		String disable = NO_VALUE;
		
		final List<Book> books = bookService.getBooks(page);
		
		if(page == 1) {
			disable = "prev";
		} else if(books.size() < 10) {
			disable = "next";
		}
		
		final ModelAndView mv = new ModelAndViewFormer(this, new ModelAndView()).value();
		
		mv.addObject("disable", disable);
        mv.addObject("page", page);
        mv.addObject("books", books);
        
        mv.setViewName("books/index");
		
		return mv;
	}
}
