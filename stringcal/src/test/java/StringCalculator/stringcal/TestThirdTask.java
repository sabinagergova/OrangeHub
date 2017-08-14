package StringCalculator.stringcal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
public class TestThirdTask {

    StringCalculator cal;
    String str;
    int result;

    @BeforeMethod
    public void setup() {
        cal = new StringCalculator();
        str = new String();
    }

    @Test
    public void givenNewLineInInputStringWhenAddMethodThenUseNewLineAsDelimeterAndReturnTheSum() throws NegativesNotAllowedException {
        str = "100,200\n300";
        result = cal.add(str);
        assertEquals(result, 100 + 200 + 300);
    }

    @Test
    public void givenNewLineAndCommaNextToEachOtherInInputWhenAddMethodThenReturnMinusOne() throws NegativesNotAllowedException {
        str = "100,200\n,300";
        result = cal.add(str);
        assertEquals(result, -1);
    }

}
