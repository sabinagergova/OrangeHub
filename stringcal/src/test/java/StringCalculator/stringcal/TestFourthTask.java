package StringCalculator.stringcal;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestFourthTask {

    StringCalculator cal;

    @BeforeClass
    public void setup() {
        cal = new StringCalculator();
    }

    @Test
    public void givenAnExplicitDelimeterOnFirstLineWhenAddMethodThenUseItToParseTheNumbers() throws NegativesNotAllowedException {
        String str = "//[%]\n1%2%3%4";
        int result = cal.add(str);
        assertEquals(result, 1 + 2 + 3 + 4);
    }

}
