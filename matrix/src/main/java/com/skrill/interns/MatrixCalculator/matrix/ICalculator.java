package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;

public interface ICalculator {

    public Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2);

    public BigDecimal[] sumColumn(Matrix matrix);
}
