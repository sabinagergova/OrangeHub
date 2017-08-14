package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class ConcurentMultiplication implements Callable<BigDecimal> {

    BigDecimal sum = new BigDecimal(0);
    BigDecimal[] column;
    BigDecimal[] row;

    public ConcurentMultiplication(BigDecimal[] row, BigDecimal[] column) {

        this.row = row;
        this.column = column;

    }

    public BigDecimal call() throws Exception {
        // TODO Auto-generated method stub
        for (int k = 0; k < row.length; k++)
        {
            sum = sum.add(column[k].multiply(row[k]));
        }
        // TODO Auto-generated method stub
        return sum;
    }
}
