package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;

public class InputTest {

    public static void main(String[] args) {
        Object[] matrices = Input.getMatrix();

        BigDecimal[][] firstMatrix = (BigDecimal[][]) matrices[0];
        BigDecimal[][] secondMatrix = (BigDecimal[][]) matrices[1];

        System.out.println("First matrix entered is: ");

        for (int c = 0; c < firstMatrix[0].length; c++)
        {
            for (int d = 0; d < firstMatrix.length; d++)
                System.out.print(firstMatrix[c][d] + "\t");

            System.out.print("\n");
        }

        System.out.println("Second matrix entered is: ");

        for (int c = 0; c < secondMatrix[0].length; c++)
        {
            for (int d = 0; d < secondMatrix.length; d++)
                System.out.print(secondMatrix[c][d] + "\t");

            System.out.print("\n");
        }
    }
}
