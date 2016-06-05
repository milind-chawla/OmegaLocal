package com.omega.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

	@Test
	public void assert_boolean_conditions() throws Exception {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
	}

	@Test
	public void assert_null_and_not_null_object_values() throws Exception {
		Object object = null;
		Assert.assertNull(object);

		object = new String("String value");
		Assert.assertNotNull(object);
	}

	@Test
	public void assert_equals_test() throws Exception {
		Integer anInteger = 5;
		Integer anotherInteger = 5;

		assertEquals(anInteger, anotherInteger);
		assertSame(anInteger, anotherInteger);
	}

	@Test
	public void assert_not_same_test() throws Exception {
		Integer anInt = new Integer("5");
		Integer anotherInt = new Integer("5");

		assertNotSame(anInt, anotherInt);
	}

	@Test
	public void assert_same_test() throws Exception {
		Integer anInt = new Integer("5");
		Integer anotherInt = anInt;

		assertSame(anInt, anotherInt);
	}
}
