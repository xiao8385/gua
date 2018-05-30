package com.gua.homework.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gua.homework.math.Formula.Operator;

public class HomeWork {

	final static Logger logger = LoggerFactory.getLogger(HomeWork.class);

	public static final int DIGIT = 3;
	public static final boolean DISPLAY_RESULT = false;

	private static String generateFormula(int digit, int resultDigit, Operator op) {

		Formula f = null;
		Value v = null;

		do {
			
			Value v1 = new Value(digit);
			Value v2 = new Value(digit);

			f = new Formula(v1.it(), op, v2.it());
			v = new Value(f.getResult() + "");
			// logger.debug("{} = {}", f.toString(), f.getResult());
			
		} while ((op == Operator.ADD && v.getDigit() > resultDigit)
				|| (op == Operator.SUB && f.getResult() < 0 || v.getDigit() > resultDigit));

		String formula = "";
		if (DISPLAY_RESULT) {
			formula = f.toString() + " = " + f.getResult();
		} else {
			formula = f.toString() + " = ";
		}
		return formula;
	}

	public static String generateAddFormula(int digit, int resultDigit) {

		return generateFormula(digit, resultDigit, Operator.ADD);
	}

	public static String generateSubFormula(int digit, int resultDigit) {

		return generateFormula(digit, resultDigit, Operator.SUB);
	}
}
