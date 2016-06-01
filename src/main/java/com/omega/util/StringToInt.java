package com.omega.util;

public class StringToInt {
	private String input;

	public StringToInt(String input) {
		this.input = input;
	}

	public int value() {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			return -1;
		}
	}
}
