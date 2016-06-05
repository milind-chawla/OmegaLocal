package com.omega.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omega.controllers.config.BooksControllerConfig;
import com.omega.controllers.config.ControllerConfig;
import com.omega.controllers.util.ModelAndViewFormer;
import com.omega.domain.Book;
import com.omega.exceptions.BookNotFoundException;
import com.omega.exceptions.JSONException;
import com.omega.service.BookService;
import com.omega.util.StringToInt;
import com.omega.util.StringToLong;

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
	public String root() {
		return "redirect:/books/index";
	}
	
	@RequestMapping(value = { "/index", "/index/" }, method = { RequestMethod.GET })
	public ModelAndView index(@RequestParam(value="page", required=false) final String _page) throws Exception {
		final int page = new StringToInt(_page).value() < 0 ? 1 : new StringToInt(_page).value();
		String disable = NO_VALUE;
		
		final List<Book> books = bookService.findAll(page);
		
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
	
	@RequestMapping(value = { "/index.json", "/index.json/" }, method = { RequestMethod.GET }, produces = { "application/json; charset=UTF-8" })
	@ResponseBody
	public List<Book> indexJson() throws JSONException {
		try {
			return bookService.findAll();
		} catch (Exception e) {
			throw new JSONException(e);
		}
	}
	
	@RequestMapping(value = { "/{bid}", "/{bid}/" }, method = { RequestMethod.GET })
	public ModelAndView show(@PathVariable("bid") String bid) throws Exception {
		final ModelAndView mv = new ModelAndViewFormer(this, new ModelAndView()).value();
		
		try {
			Book book = bookService.findById(new StringToLong(bid).value());
			if(book == null) throw new BookNotFoundException(bid);
			
			mv.addObject("book", book);
            mv.setViewName("books/show");
		} catch (BookNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		return mv;
	}
	
	@RequestMapping(value = { "/{bid}.json", "/{bid}.json/" }, method = { RequestMethod.GET }, produces = { "application/json; charset=UTF-8" })
	@ResponseBody
	public Book showJson(@PathVariable("bid") String bid) throws JSONException {
		try {
			Book book = bookService.findById(new StringToLong(bid).value());
			if(book == null) throw new BookNotFoundException(bid);
			return book;
		} catch (BookNotFoundException e) {
			throw new JSONException(e);
		} catch (Exception e) {
			throw new JSONException(e);
		}
	}
	
	@RequestMapping(value = { "/new", "/new/" }, method = { RequestMethod.GET })
	public ModelAndView newBookFormDisplay() {
		final ModelAndView mv = new ModelAndViewFormer(this, new ModelAndView()).value();
		
		mv.addObject("book", new Book());
        mv.setViewName("books/new");
        
		return mv;
	}
	
	@RequestMapping(value = { "/new", "/new/" }, method = { RequestMethod.POST })
	public ModelAndView newBookFormSubmit(@Valid Book book, BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
		final ModelAndView mv = new ModelAndViewFormer(this, new ModelAndView()).value();
		
		if(result.hasErrors()) {
            mv.addObject("book", book);
            mv.setViewName("books/new");
        } else {
            bookService.save(book);
            redirectAttributes.addFlashAttribute("messages", Arrays.asList(book + " created successfully"));
            mv.clear();
            mv.setViewName("redirect:/books/" + book.getId()); 
        }
        
		return mv;
	}
}
