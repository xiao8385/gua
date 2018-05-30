package com.gua.homework.math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeWorkTest {

	final static Logger logger = LoggerFactory.getLogger(HomeWorkTest.class);
	
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
	public void testGenerateAdd() {
		
		for (int i=0; i<100; i++) {
			
			logger.info("({}). \t{}", i+1, HomeWork.generateAddFormula(HomeWork.DIGIT, HomeWork.DIGIT));
		}
	}
	
	@Test
	public void testGenerateSub() {
		
		for (int i=0; i<100; i++) {
			
			logger.info("({}). {}", i+1, HomeWork.generateSubFormula(HomeWork.DIGIT, HomeWork.DIGIT-1));
		}
	}

}
