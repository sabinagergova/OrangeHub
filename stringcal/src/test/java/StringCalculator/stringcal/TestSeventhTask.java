package StringCalculator.stringcal;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSeventhTask {

    StringCalculator cal;

    @BeforeMethod
    public void setup() {
        cal = new StringCalculator();
    }

    @Test
    public void givenDelimeterOfAnyLengthWhenAddMethodThenSumNumbersByIt() throws Exception {
        String str = "//[***]\n1***1***1";
        int result = cal.add(str);
        assertEquals(result, 1 + 1 + 1);

    }

}
