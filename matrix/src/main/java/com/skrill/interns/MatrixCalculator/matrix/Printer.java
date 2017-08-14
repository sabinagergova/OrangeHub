package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;

public class Printer implements IPrinter {

	public void printMatrix(Matrix matrix, int dimention) {
		System.out.println("The Matrix:");
		for (int i = 0; i < dimention; i++) {
			for (int j = 0; j < dimention; j++) {
				System.out.print(matrix.getElement(i, j) + " ");
			}
			System.out.print("\n");
		}
	}

	public void printArray(BigDecimal[] array) {
		System.out.println("The Sum of column is: ");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i] + "\n");
		}
	}
}
