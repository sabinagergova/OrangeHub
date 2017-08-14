package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixApplication {

    static ExecutorService multiplicationPool = Executors.newCachedThreadPool();
    static ExecutorService accumulationPool = Executors.newCachedThreadPool();

    public void run() {
        // TODO Auto-generated method stub
        Matrix matrix1;
        Matrix matrix2;
        Matrix resultMatrix;
        Calculator calculator = new Calculator();
        BigDecimal[] resultArray;
        Printer printer = new Printer();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 'q' to quit or everything else to continue: ");
            if (sc.next().equalsIgnoreCase("q")) {
                break;
            }
            Object[] matrices = Input.getMatrix();
            matrix1 = new Matrix((BigDecimal[][]) matrices[0]);
            matrix2 = new Matrix((BigDecimal[][]) matrices[1]);
            resultMatrix = calculator.multiplyMatrix(matrix1, matrix2);
            resultArray = calculator.sumColumn(resultMatrix);
            printer.printMatrix(resultMatrix, resultMatrix.getDimension());
            printer.printArray(resultArray);
        }
        MatrixApplication.multiplicationPool.shutdown();
        MatrixApplication.accumulationPool.shutdown();
    }
}
