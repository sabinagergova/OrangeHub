package com.skrill.interns.MatrixCalculator.matrix;

import java.math.BigDecimal;

public class Matrix implements IMatrix {

    int dimension;
    BigDecimal[][] matrix;

    public Matrix(BigDecimal[][] array) {
        this.matrix = array;
        this.dimension = array.length;
    }

    public BigDecimal[] getRow(int r) {
        BigDecimal[] result = new BigDecimal[matrix[r].length];
        for (int i = 0; i < matrix[r].length; i++) {
            result[i] = matrix[r][i];
        }
        return result;
    }

    /**
     * Returns an array of BigDecimal which represents a column from the matrix. The number of the column is specified by the argument 'c'.
     * The method runs through all rows and gets the element with 'c' index.
     */
    public BigDecimal[] getCol(int c) {
        BigDecimal[] result = new BigDecimal[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i][c];
        }
        return result;
    }

    public int getDimension() {
        return this.dimension;
    }

    public BigDecimal getElement(int i, int j) {
        return matrix[i][j];
    }

}
