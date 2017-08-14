package StringCalculator.stringcal;

import java.util.Random;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSecondTask {

    StringCalculator cal;
    Random r;
    int[] input;
    StringBuilder builder;

    @BeforeMethod
    public void setup() {
        cal = new StringCalculator();
        r = new Random();
        builder = new StringBuilder();
    }

    @Test
    public void givenUnknownAmountOfNumbersWhenAddMethodThenReturnTheirSum() throws NegativesNotAllowedException {
        int amount = r.nextInt(10); // get unknown amount
        input = new int[amount]; // generate array of integers with this size
        int actualSum = 0;
        String[] stringInput = new String[amount]; // generate array of strings with the same size
        for (int i = 0; i < amount; i++) { // iterate over the array of integers and assign a random int to 1000
            input[i] = r.nextInt(1000);
            actualSum += input[i]; // adds up to the overall sum
            stringInput[i] = String.valueOf(input[i]);
            builder.append(stringInput[i] + ","); // building the input for the add method
        }
        int result = cal.add(builder.toString());
        assertEquals(actualSum, result);

    }

}
