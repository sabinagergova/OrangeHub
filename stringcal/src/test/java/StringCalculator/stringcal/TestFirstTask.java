package StringCalculator.stringcal;

import static org.testng.Assert.*;
import org.testng.annotations.*;



public class TestFirstTask {

    StringCalculator cal;

    @BeforeMethod
    public void setup(){
        cal = new StringCalculator();
    }

    @Test
    public void givenEmptyStringWhenAddMethodThenReturnZero() throws NegativesNotAllowedException {
        String str = "";
        int result = cal.add(str);

        assertEquals(result, 0);
    }

    @Test
    public void givenOneNumberWhenAddMethodThenReturnTheSameNumber() throws NegativesNotAllowedException {
        String str = "15";
        int result = cal.add(str);
        assertEquals(result, 15);
    }

    @Test
    public void givenTwoNumbersWhenAddMethodThenReturnTheirSum() throws NegativesNotAllowedException {
        int i = 250;
        int j = 40;
        String first = String.valueOf(i);
        String second = String.valueOf(j);
        String input = first + "," + second;
        int result = cal.add(input);
        assertEquals(result, 290);
    }


}
