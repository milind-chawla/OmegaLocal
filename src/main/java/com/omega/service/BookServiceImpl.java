package com.omega.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
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
	@Transactional(readOnly = true)
	public Book findById(long id) {
		return bookDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findByName(String name) {
		return bookDao.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAll(int page) {
		return bookDao.findAll(page);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Book save(Book book) {
		return bookDao.save(book);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Book update(Book book) {
		return bookDao.update(book);
	}
}
