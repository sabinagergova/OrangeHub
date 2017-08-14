package com.skrill.interns.unit_test.calculator;

import org.mockito.internal.matchers.StartsWith;

/**
 * Calculator
 * 
 */
public class Calculator {
	public String number;

	public Calculator() {
		number = null;
	}

	public Calculator(String number) {
		this.number = number;
	}

	public int add(String number) {
		int result = 0;
		String newNumber = null;
		final String numberTester = "^([\\d]+[,\n])*[\\d]+$";
		final String numberTesterForNegatives = "^([-]*[\\d]+[,\n])*([-]*[\\d])+$";
		final String predefinedNumberTester = "^//[^0-9]+\n+([-0-9]+[^-0-9]+)+[\\d]+$";
		final String openCloseBracket = "][";
		final String firstTypeDelimiter = "[,||\\n]";
		String preDefinedDelimiter;
		StringBuilder sBuild = new StringBuilder();
		String delimiter;
		StringBuilder minusValues = new StringBuilder();
		if (number.equals("") || number.equals(null)) {
			result = 0;
		} else if (number.startsWith("//") && number.contains("\n")) {
			int resultInt = 0;
			if (number.startsWith("//[") && number.contains("]\n")
					&& number.matches(predefinedNumberTester)) {
				if (!(number.contains(openCloseBracket))) {
					delimiter = number.substring((number.indexOf("[") + 1),
							number.indexOf("]\n"));
					preDefinedDelimiter = tinyLittleBuilder(delimiter);
					newNumber = number.substring(number.indexOf("\n") + 1);
					String numbers[] = newNumber.split(preDefinedDelimiter);
					resultInt = checkForNegatives(numbers);

				} else if (number.contains(openCloseBracket)) {
					delimiter = number.substring((number.indexOf("[") + 1),
							number.indexOf("\n") - 1);
					String delimiters[] = delimiter.split("\\]\\[");
					int numberOfDelimiters = delimiters.length;
					String formatedDelimiters[] = new String[numberOfDelimiters];
					for (int i = 0; i < numberOfDelimiters; i++) {
						formatedDelimiters[i] = tinyLittleBuilder(delimiters[i]);
					}
					for (int i = 0; i < numberOfDelimiters - 1; i++) {
						sBuild.append(formatedDelimiters[i] + "|");
					}
					sBuild.append(formatedDelimiters[numberOfDelimiters - 1]);
					newNumber = number.substring(number.indexOf("\n") + 1);
					String numbers[] = newNumber.split(sBuild.toString());
					resultInt = checkForNegatives(numbers);
				} else {
					return -1;
				}
			} else {
				newNumber = number.substring(number.indexOf("\n") + 1);
				preDefinedDelimiter = number.substring(
						(number.indexOf("//") + 2), number.indexOf("\n"));
				String numbers[] = newNumber.split(preDefinedDelimiter);
				resultInt = checkForNegatives(numbers);
			}
			result = resultInt;
		} else if (number.matches(numberTester)) {
			String numbers[] = number.split(firstTypeDelimiter);
			int amountOfNumbers = numbers.length;
			int addends[] = new int[amountOfNumbers];
			if (number.matches(numberTester)) {
				if (amountOfNumbers == 1) {
					addends[0] = Integer.parseInt(numbers[0]);
					result = addends[0];
				} else if (amountOfNumbers >= 2) {
					result = checkForNegatives(numbers);
				}
			}
		} else if (number.matches(numberTesterForNegatives)) {
			String numbers[] = number.split(firstTypeDelimiter);
			for (int i = 0; i < numbers.length; i++) {
				if (numbers[i].contains("-")) {
					minusValues.append(numbers[i]);
				}
			}
			throw new IllegalArgumentException("Negatives not allowed!!!"
					+ minusValues.toString());
		} else {
			return -1;
		}
		return result;
	}

	public String tinyLittleBuilder(String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < delimiter.length(); i++) {
			sb.append("\\Q" + delimiter.charAt(i) + "\\E");
		}
		return sb.toString();
	}

	public int checkForNegatives(String[] numbers) {
		int result = 0;
		int addends[] = new int[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			addends[i] = Integer.parseInt(numbers[i]);
			if (addends[i] > 1000) {
				result += 0;
			} else {
				result += addends[i];
			}
		}

		return result;
	}
}