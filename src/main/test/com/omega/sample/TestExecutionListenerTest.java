package com.omega.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestExecutionListenerTest.AppContext.class})
@TestExecutionListeners(value = {TestExecutionListenerTest.SysOutTestExecutionListener.class})
public class TestExecutionListenerTest {
	
	@Test
	public void someTest() throws Exception {
		System.out.println("executing someTest");
	}
	
	@Test
	public void someOtherTest() throws Exception {
		System.out.println("executing someOtherTest");
	}

	public static class SysOutTestExecutionListener implements TestExecutionListener {

		@Override
		public void afterTestClass(TestContext testContext) throws Exception {
			ApplicationContext ctx = testContext.getApplicationContext();
			System.out.println("In afterTestClass for class = " + testContext.getTestClass());
		}

		@Override
		public void afterTestMethod(TestContext testContext) throws Exception {
			System.out.println("In afterTestMethod for = " + testContext.getTestMethod().getName());
		}

		@Override
		public void beforeTestClass(TestContext testContext) throws Exception {
			System.out.println("In beforeTestClass for class = " + testContext.getTestClass());
		}

		@Override
		public void beforeTestMethod(TestContext testContext) throws Exception {
			System.out.println("In beforeTestMethod for = " + testContext.getTestMethod().getName());
		}

		@Override
		public void prepareTestInstance(TestContext testContext) throws Exception {
			System.out.println("In prepareTestInstance for = " + testContext.getTestInstance());
		}
	}

	@Configuration
	public static class AppContext {
	}
}
