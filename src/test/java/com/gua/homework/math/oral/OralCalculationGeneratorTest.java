package com.gua.homework.math.oral;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OralCalculationGeneratorTest {
	
	OralCalculationGenerator generator = new OralCalculationGenerator();

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
	public void testCreatFormulaList() {
		
		generator.createExcelFile("\\list.xlsx", 7);
	}
}
