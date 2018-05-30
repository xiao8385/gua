package com.gua.homework.math;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gua.homework.math.Formula.Operator;

public class FormulaTest {

	final static Logger logger = LoggerFactory.getLogger(FormulaTest.class);

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
	public void testGetResult() {

		for (int i = 0; i < 10; i++) {

			int f1 = new Value(1).it();
			int f2 = new Value(1).it();
			Operator op = Operator.ADD;
			int result = f1 + f2;
			Formula f = new Formula(f1, op, f2);
			logger.debug("No.[{}/{}] {}+{}={}, formula={}", i + 1, 10, f1, f2, result, f.getResult());
			assertEquals(f.getResult(), result);
		}
		
		for (int i = 0; i < 10; i++) {
			
			int f1 = new Value(2).it();
			int f2 = new Value(2).it();
			Operator op = Operator.ADD;
			int result = f1 + f2;
			Formula f = new Formula(f1, op, f2);
			logger.debug("No.[{}/{}] {}+{}={}, formula={}", i + 1, 10, f1, f2, result, f.getResult());
			assertEquals(f.getResult(), result);
		}
		
		for (int i = 0; i < 10; i++) {
			
			int f1 = new Value(3).it();
			int f2 = new Value(3).it();
			Operator op = Operator.ADD;
			int result = f1 + f2;
			Formula f = new Formula(f1, op, f2);
			logger.debug("No.[{}/{}] {}+{}={}, formula={}", i + 1, 10, f1, f2, result, f.getResult());
			assertEquals(f.getResult(), result);
		}
	}

}
