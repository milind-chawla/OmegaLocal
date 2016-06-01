package com.omega.exceptions;

public class BookNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String bookId) {
		super(bookId);
	}
}
