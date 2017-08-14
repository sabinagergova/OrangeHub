package StringCalculator.stringcal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {

        String var1 = "//[$$$]\n1$$$1$$$1";
        String var2 = "//[**][%]\n1*2%3";
        String var3 = "1\n1,1";

        StringCalculator cal = new StringCalculator();
        int result;
        try {
            result = cal.add(var2);
            System.out.println(result);
        } catch (NegativesNotAllowedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
