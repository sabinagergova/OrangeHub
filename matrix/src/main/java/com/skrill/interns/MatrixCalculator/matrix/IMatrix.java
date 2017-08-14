package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;

public interface IMatrix {


    public BigDecimal[] getRow(int i);
    public BigDecimal[] getCol(int i);
    public BigDecimal getElement(int i, int j);
    public int getDimension();

}
