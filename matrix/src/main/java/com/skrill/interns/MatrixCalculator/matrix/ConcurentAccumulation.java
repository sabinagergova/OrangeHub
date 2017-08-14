package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class ConcurentAccumulation implements Callable<BigDecimal> {

    BigDecimal[] row;
    BigDecimal sum = new BigDecimal(0);

    public ConcurentAccumulation(BigDecimal[] row) {
        // TODO Auto-generated constructor stub
        this.row = row;
    }

    public BigDecimal call() throws Exception {
        // TODO Auto-generated method stub
        for (int i = 0; i < row.length; i++) {
            sum = sum.add(row[i]);
        }

        return sum;
    }

}
