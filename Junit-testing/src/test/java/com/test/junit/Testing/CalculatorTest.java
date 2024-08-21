package com.test.junit.Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.test.junit.Calculator.Calculator;

public class CalculatorTest {
	private static Calculator calculator;

	@BeforeAll
	public static void init() {
		calculator = new Calculator();
		System.out.println("BeforeAll: Setup Calculator instance.");
	}

	@AfterAll
	public static void destroy() {
		System.out.println("AfterAll: Cleanup resources.");
	}

	@Test
	public void testAdd() {
		Integer actualResult = calculator.add(20, 10);
		Integer expectedResult = 30;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSubtract() {
		Integer actualResult = calculator.subtract(20, 10);
		Integer expectedResult = 10;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testMultiply() {
		Integer actualResult = calculator.multiply(20, 10);
		Integer expectedResult = 200;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testDivide() {
		Integer actualResult = calculator.divide(20, 10);
		Integer expectedResult = 2;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testDivideByZero() {
		Calculator calculator = new Calculator();
		assertThrows(ArithmeticException.class, () -> {
			calculator.divide(10, 0);
		});
	}
}
