package com.omega.service;

import java.util.List;

import com.omega.domain.Book;

public interface BookService {

	public Book findById(long id);
	public List<Book> findByName(String name);
	public List<Book> findAll();
	public List<Book> findAll(int page);
	
	public Book save(Book book);
	public Book update(Book book);
}
