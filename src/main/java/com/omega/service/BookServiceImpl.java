package com.omega.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.omega.domain.Book;
import com.omega.repository.BookDao;

@Transactional
public class BookServiceImpl implements BookService {

	private BookDao bookDao = null;
	
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@Override
	@Transactional
	public Book findById(long id) {
		return bookDao.findById(id);
	}

	@Override
	@Transactional
	public Book findByName(String name) {
		return bookDao.findByName(name);
	}

	@Override
	@Transactional
	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

	@Override
	@Transactional
	public List<Book> getBooks(int page) {
		return bookDao.getBooks(page);
	}

	@Override
	@Transactional
	public Book save(Book book) {
		return bookDao.save(book);
	}

	@Override
	@Transactional
	public Book update(Book book) {
		return bookDao.update(book);
	}
}
