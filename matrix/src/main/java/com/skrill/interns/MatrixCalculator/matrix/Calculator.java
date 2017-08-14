package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Calculator implements ICalculator {



	public Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2) {
        // TODO Auto-generated method stub
        Matrix resultMatrix;

        Queue<Future<BigDecimal>> sum = new ConcurrentLinkedQueue<Future<BigDecimal>>();
        BigDecimal[][] result = new BigDecimal[matrix1.getDimension()][matrix2.getDimension()];
        int firstMatrixRow = matrix1.getDimension();
//        int secondMatrixColumn = matrix2.getDimension();
        int c, d;
        ConcurentMultiplication multiplyingThread;


        int NTHREDS = firstMatrixRow*firstMatrixRow;
        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);

        for (c = 0; c < firstMatrixRow; c++)
        {
            for (d = 0; d < firstMatrixRow; d++)
            {

                try {
                    multiplyingThread = new ConcurentMultiplication(matrix1.getRow(c), matrix2.getCol(d));
                    sum.add(MatrixApplication.multiplicationPool.submit(multiplyingThread));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        for (c = 0; c < firstMatrixRow; c++)
        {
            for (d = 0; d < firstMatrixRow; d++)
            {

                try {
                    result[c][d] = sum.poll().get();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        resultMatrix = new Matrix(result);

        return resultMatrix;

    }

    public BigDecimal[] sumColumn(Matrix matrix) {
        // TODO Auto-generated method stub

        ConcurentAccumulation addingThread;
        int row = 0;
        Queue<Future<BigDecimal>> sum = new ConcurrentLinkedQueue<Future<BigDecimal>>();
        BigDecimal[] result = new BigDecimal[matrix.getDimension()];

        for (row = 0; row < matrix.getDimension(); row++) {

            addingThread = new ConcurentAccumulation(matrix.getCol(row));
            sum.add(MatrixApplication.accumulationPool.submit(addingThread));
        }
        for (row = 0; row < matrix.getDimension(); row++) {

            try {
                result[row] = sum.poll().get();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }
}
