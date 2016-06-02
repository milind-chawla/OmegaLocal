package com.omega.util;

public class StringToLong {

	private String input;

	public StringToLong(String input) {
		this.input = input;
	}
	
	public long value() {
		try {
			return Long.parseLong(input);
		} catch (Exception e) {
			return -1L;
		}
	}
}
