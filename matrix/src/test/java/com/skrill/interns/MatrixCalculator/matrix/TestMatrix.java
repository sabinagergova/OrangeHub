package com.skrill.interns.MatrixCalculator.matrix;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestMatrix {

    Matrix m;
    BigDecimal[][] d;
    BigDecimal a[][] = new BigDecimal[3][3];
    BigDecimal b[][] = new BigDecimal[3][3];

    @BeforeMethod
    public void setup() {
        d = new BigDecimal[3][3];
        m = new Matrix(d);
    }


    @Test
    public void givenArrayOfBigDecimalWhenGetColThenReturnCol() {

        d[0][1] = BigDecimal.valueOf(300);
        d[1][1] = BigDecimal.valueOf(200);
        d[2][1] = BigDecimal.valueOf(100);

        BigDecimal[] res = m.getCol(1);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
    
   

}
