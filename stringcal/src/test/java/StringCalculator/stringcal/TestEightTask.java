package StringCalculator.stringcal;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestEightTask {

    StringCalculator cal;

    @BeforeMethod
    public void setup() {
        cal = new StringCalculator();
    }

    @Test
    public void givenMultipleDelimetersInFirstLineWhenAddMethodThenSumByThem() throws Exception {
        // GIVEN
        String str = "//[#][$]\n1#2$3";
        String str2 = "//[#@!][$]\n1#@!2$3";
        // WHEN
        int result = cal.add(str);
        int result2 = cal.add(str2);
        // THEN
        assertEquals(result, 6);
        assertEquals(result2, 6);
    }

}
