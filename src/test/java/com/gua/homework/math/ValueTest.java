package com.gua.homework.math;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueTest {

	final static Logger logger = LoggerFactory.getLogger(ValueTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIt() {

		for (int i = 0; i < 1; i++) {
			Value v = new Value(0);
			assertTrue(v.it() == 0);
		}

		for (int i = 0; i < 1; i++) {
			Value v = new Value(-1);
			assertTrue(v.it() == 0);
		}

		for (int i = 0; i < 10; i++) {
			Value v = new Value(1);
			logger.debug("No.{} v.it={}", i + 1, v.it());
			assertTrue(v.it() > 0);
			assertTrue(v.it() < 9);
		}

		for (int i = 0; i < 10; i++) {
			Value v = new Value(2);
			logger.debug("No.{} v.it={}", i + 1, v.it());
			assertTrue(v.it() > 10);
			assertTrue(v.it() < 99);
		}

		for (int i = 0; i < 10; i++) {
			Value v = new Value(3);
			logger.debug("No.{} v.it={}", i + 1, v.it());
			assertTrue(v.it() > 100);
			assertTrue(v.it() < 999);
		}

		for (int i = 0; i < 10; i++) {
			Value v = new Value(4);
			logger.debug("No.{} v.it={}", i + 1, v.it());
			assertTrue(v.it() > 1000);
			assertTrue(v.it() < 9999);
		}

		for (int i = 0; i < 10; i++) {
			Value v = new Value(5);
			logger.debug("No.{} v.it={}", i + 1, v.it());
			assertTrue(v.it() > 10000);
			assertTrue(v.it() < 99999);
		}
	}

	@Test
	public void testDigit() {
		Value v = new Value("233");
		logger.debug("v.it={}, v.digit={}", v.it(), v.getDigit());
		assertTrue(v.getDigit() == 3);
	}
}
