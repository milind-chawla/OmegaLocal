package com.omega.repository;

import java.util.List;

import com.omega.domain.Book;

public interface BookDao {

	public Book findById(long id);
	public Book findByName(String name);
	public List<Book> getBooks();
	public List<Book> getBooks(int page);
	
	public Book save(Book book);
	public Book update(Book book);
}
