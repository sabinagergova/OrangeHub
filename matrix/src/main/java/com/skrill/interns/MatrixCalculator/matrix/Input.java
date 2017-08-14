package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input implements IInput {
    static public Object[] getMatrix() {
        Object[] matrices = new Object[2];
        BigDecimal[][] firstMarix;
        BigDecimal[][] secondMatrix;
        int dimention = 0;
        boolean error = true;

        Scanner in = new Scanner(System.in);
        while (error) {
            try {
                System.out.println("Enter the dimension of the matrices");
                dimention = Integer.parseInt(in.next());
                if (dimention < 0) {
                    System.out.println("Wrong input! Negative number not allowed!");
                    continue;
                }
                error = false;
            } catch (NumberFormatException e) {
                System.out.println("You must enter integer number!");
                continue;
            }

            firstMarix = new BigDecimal[dimention][dimention];

            System.out.println("Enter the elements of first matrix");

            for (int c = 0; c < dimention; c++)
                for (int d = 0; d < dimention; d++)
                    try {
                        firstMarix[c][d] = new BigDecimal(in.nextDouble());
                        error = false;
                    } catch (InputMismatchException e) {
                        error = true;
                        System.out.println("You must enter only numbers!");
                        continue;
                    }

            error = false;
            secondMatrix = new BigDecimal[dimention][dimention];

            System.out.println("Enter the elements of second matrix");

            for (int c = 0; c < dimention; c++)
                for (int d = 0; d < dimention; d++)
                    try {
                        secondMatrix[c][d] = new BigDecimal(in.nextDouble());
                        error = false;
                    } catch (InputMismatchException e) {
                        error = true;
                        System.out.println("You must enter only numbers!");
                        continue;
                    }

            matrices[0] = firstMarix;
            matrices[1] = secondMatrix;
        }

        return matrices;
    }
}
