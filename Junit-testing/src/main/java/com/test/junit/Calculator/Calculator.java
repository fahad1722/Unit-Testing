package com.test.junit.Calculator;

public class Calculator {
	public Integer add(Integer a, Integer b) {
		return a + b;
	}

	public Integer subtract(Integer a, Integer b) {
		return a - b;
	}

	public Integer multiply(Integer a, Integer b) {
		return a * b;
	}

	public Integer divide(Integer a, Integer b) {
		if (b == 0) {
			throw new ArithmeticException("Cannot divide by zero");
		}
		return a / b;
	}
}
