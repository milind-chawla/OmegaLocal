package com.omega.sample;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class LessThanOrEqual<T extends Comparable<T>> extends BaseMatcher<Comparable<T>> {
	private final Comparable<T> expValue;

	public LessThanOrEqual(T expValue) {
		this.expValue = expValue;
	}

	@Override
	public boolean matches(Object item) {
		int compareTo = expValue.compareTo((T) item);
		return compareTo > -1;
	}

	@Override
	public void describeTo(Description desc) {
		desc.appendText(" less than or equal(<=)" + expValue);
	}
	
	@Factory
	public static<T extends Comparable<T>> Matcher<T>lessThanOrEqual(T t) {
		return new LessThanOrEqual(t);
	}
}
