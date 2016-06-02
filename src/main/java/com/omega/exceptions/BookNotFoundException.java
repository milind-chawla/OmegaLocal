package com.omega.exceptions;

public class BookNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String bookId = null;

	public BookNotFoundException(String bookId) {
		this.bookId = bookId;
	}

	public String getBookId() {
		return bookId;
	}
}
