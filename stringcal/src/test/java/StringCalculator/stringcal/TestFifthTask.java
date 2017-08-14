package StringCalculator.stringcal;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestFifthTask {

    StringCalculator cal;

    @BeforeMethod
    public void setup() {
        cal = new StringCalculator();
    }

    @Test(expectedExceptions = NegativesNotAllowedException.class)
    public void givenNegativeNumberWhenAddMethodThrowNegativesNotAllowedException() throws Exception {
        String str = "123, 432, -5,20";
        cal.add(str);


    }

}
