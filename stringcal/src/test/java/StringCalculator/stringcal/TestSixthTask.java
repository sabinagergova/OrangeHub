package StringCalculator.stringcal;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSixthTask {

    StringCalculator cal;

    @BeforeMethod
    public void setup() {
        cal = new StringCalculator();
    }

    @Test
    public void givenNumberGreaterThan1000WhenAddMethodThenIgnoreIt() throws Exception{
        String str = "4, 1000, 1004";
        int result = cal.add(str);
        assertEquals(result, 1004);
    }

}
