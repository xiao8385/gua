package com.gua.homework.math;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class Value {

	private int digit;
	private int it;
	private int min;
	private int max;
	
	public static final int MAX_DIGIT = 5;
	
	public Value(String value) {
		
		if (value == null || value.trim().length()==0) return;
		
		this.it = Integer.parseInt(value);
		this.digit = String.valueOf(this.it).length();
	}

	public Value(int digit) {

		if (digit > MAX_DIGIT || digit <= 0)
			return;

		this.digit = digit;

		this.min = this.min();
		this.max = this.max();
		this.it = new Random().nextInt(max - min) + min;
	}

	private int max() {
		
		return Integer.parseInt(StringUtils.repeat("9", (this.digit)));
	}

	private int min() {

		if (this.digit == 0)
			return 0;

		return Integer.parseInt(1 + StringUtils.repeat("0", (this.digit - 1)));
	}

	public String toString() {

		return "" + this.it;
	}
	
	public int it() {
		return this.it;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + it;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Value)) {
			return false;
		}
		Value other = (Value) obj;
		if (it != other.it) {
			return false;
		}
		return true;
	}

	public int getDigit() {
		return this.digit;
	}
}
