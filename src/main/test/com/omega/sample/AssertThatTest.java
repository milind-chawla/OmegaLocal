package com.omega.sample;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import static com.omega.sample.LessThanOrEqual.lessThanOrEqual;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AssertThatTest {

	@Test
	public void test_matcher_behavior() throws Exception {
		int myAge = 30;

		// examine the exact match with equalTo and is
		assertThat(myAge, equalTo(30));
		assertThat(myAge, is(30));

		// examine partial match with not()
		assertThat(myAge, not(equalTo(33)));
		assertThat(myAge, is(not(33)));
	}

	@Test
	public void verify_multiple_values() throws Exception {
		double myMarks = 100.00;

		assertThat(myMarks, either(is(100.00)).or(is(90.9)));
		assertThat(myMarks, both(not(99.99)).and(not(60.00)));
		assertThat(myMarks, anyOf(is(100.00), is(1.00), is(55.00), is(88.00), is(67.8)));
		assertThat(myMarks, not(anyOf(is(0.00), is(200.00))));
		assertThat(myMarks, not(allOf(is(1.00), is(100.00), is(30.00))));
	}

	@Test
	public void verify_collection_values() throws Exception {
		List<Double> salary = Arrays.asList(50.0, 200.0, 500.0);

		assertThat(salary, hasItem(50.00));
		assertThat(salary, hasItems(50.00, 200.00));
		assertThat(salary, not(hasItem(1.00)));
	}

	@Test
	public void verify_Strings() throws Exception {
		String myName = "John Jr Dale";

		assertThat(myName, startsWith("John"));
		assertThat(myName, endsWith("Dale"));
		assertThat(myName, containsString("Jr"));
	}

	@Test
	public void lessthanOrEquals_matcher() throws Exception {
		int actualGoalScored = 2;
		int expGoalScored = 4;
		
		assertThat(actualGoalScored, lessThanOrEqual(expGoalScored));
		
		expGoalScored = 2;
		
		assertThat(actualGoalScored, lessThanOrEqual(expGoalScored));
		
		double actualDouble = 3.14;
		double expDouble = 9.00;
		
		assertThat(actualDouble, lessThanOrEqual(expDouble));
		
		String authorName = "Sujoy";
		String expAuthName = "Zachary";
		
		assertThat(authorName, lessThanOrEqual(expAuthName));
	}
}
