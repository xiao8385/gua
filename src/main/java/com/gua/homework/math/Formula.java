package com.gua.homework.math;

public class Formula {

	enum Operator {
		ADD, SUB
	}

	private Operator operator;
	private int firstValue;
	private int secondValue;
	private int result;
	private StringBuffer buf = new StringBuffer();

	public Formula(int firstValue, Operator o, int secondValue) {

		this.operator = o;
		this.firstValue = firstValue;
		this.secondValue = secondValue;

		this.calculate();
	}

	private void calculate() {

		this.validate();

		buf.append(this.firstValue);
		buf.append(" ");
		switch (this.operator) {
		case ADD:
			buf.append("+");
			this.result = this.firstValue + this.secondValue;
			break;
		case SUB:
			buf.append("-");
			this.result = this.firstValue - this.secondValue;
			break;
		}
		buf.append(" ");
		buf.append(this.secondValue);
	}

	// public Formula(String firstValue, Operator o, String secondValue) {
	//
	// this.operator = o;
	// this.firstValue = Integer.parseInt(firstValue);
	// this.secondValue = Integer.parseInt(secondValue);
	// }

	private void validate() {

		switch (this.operator) {
		case ADD:
			break;
		case SUB:
			if (this.firstValue < this.secondValue) {
				int transfer = this.firstValue;
				this.firstValue = this.secondValue;
				this.secondValue = transfer;
			}
			break;
		}
	}

	public String toString() {
		return buf.toString();
	}

	public int getResult() {
		return this.result;
	}
}
