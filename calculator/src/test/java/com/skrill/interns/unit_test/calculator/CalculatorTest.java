package com.skrill.interns.unit_test.calculator;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import org.testng.annotations.*;

public class CalculatorTest {
	private Calculator calculator;
	private String number;
	private int result;

	@BeforeMethod
	public void setUp() {
		calculator = new Calculator();
	}

	@Test
	public void givenEmptyStringWhenCallingAddMethodThenZero() throws Exception {
		// GIVEN
		number = "";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 0);
	}

	@Test
	public void givenOneNotEmptyStringWhenCallingAddMethodThen2()
			throws Exception {
		// GIVEN
		number = "2";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 2);

	}

	@Test
	public void givenTwoNotEmptyStringsWhenCallingAddMethodThen5()
			throws Exception {
		// GIVEN
		number = "2,3";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 5);
	}

	@Test
	public void givenMoreThanTwoNumbersWhenCallingAddMethodThen20()
			throws Exception {
		// GIVEN
		number = "2,3,4,5,6";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 20);
	}

	@Test
	public void givenNewLineDelimiterWhenCallingAddMethodThen10()
			throws Exception {
		// GIVEN
		number = "2\n3,5";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 10);
	}

	@Test
	public void givenDifferentDelimitersWhenCallingAddMethodThen3()
			throws Exception {
		// GIVEN
		number = "//:\n1:2";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 3);
	}

	@Test
	public void givenNumberBiggerThan1000WhenCallingAddMethodThen4()
			throws Exception {
		// GIVEN
		number = "4,1001";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 4);
	}

	@Test
	public void givenDelimitersFromAnyLenghtAndFormatWhenCallingAddMethodThen6()
			throws Exception {
		// GIVEN
		number = "//[***]\n1***2***3";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 6);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void givenNegativeNumbersWhenCallingAddMethod() throws Exception {
		// GIVEN
		number = "-2,5,-4";
		// WHEN
		result = calculator.add(number);
		// THEN
	}

	@Test
	public void givenMultipleDelimitersWhenCallingAddMethodThen15()
			throws Exception {
		// GIVEN
		number = "//[*][%]\n4*5%6";
		// WHEN
		result = calculator.add(number);
		// THEN
		assertEquals(result, 15);
	}

}
