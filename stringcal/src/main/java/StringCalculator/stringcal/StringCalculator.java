package StringCalculator.stringcal;

import java.util.ArrayList;

public class StringCalculator {

    public int add(String numbers) throws NegativesNotAllowedException {
        String exceptionDigits = new String();
        String delimeter = new String();
        StringBuilder delimeterBuilder = new StringBuilder();
        ArrayList<Integer> negatives = new ArrayList<Integer>();
        if (numbers.contains("\n,") || numbers.contains(",\n")) {
            return -1;
        }
        if (numbers.startsWith("//[")) {
            if (numbers.contains("]\n")) {
                delimeter = numbers.substring((numbers.indexOf("//") + 3), (numbers.indexOf("]\n")));
                numbers = numbers.substring(delimeter.length() + 5);
                if (!delimeter.contains("][")) {
                    for (int i = 0; i < delimeter.length(); i++) {
                        delimeterBuilder.append("\\" + delimeter.charAt(i));
                    }
                    delimeter = delimeterBuilder.toString();
                } else {
                    String[] dels = delimeter.split("\\]\\[");
                    StringBuilder delsBuilder;
                    for (int i = 0; i < dels.length; i++) {
                        delsBuilder = new StringBuilder();
                        delsBuilder.append("");
                        for (int j = 0; j < dels[i].length(); j++) {
                            delsBuilder.append("\\" + dels[i].charAt(j));
                        }
                        delimeterBuilder.append(delsBuilder + "|");
                    }
                    delimeterBuilder.deleteCharAt(delimeterBuilder.length() - 1);
                    delimeter = delimeterBuilder.toString();
                }
            }
        } else {
            delimeter = "\\n|,";
        }
        System.out.println("The number is delimeted by: " + delimeter);
        String[] s = numbers.split(delimeter);
        int[] n = new int[s.length];
        int sum = 0;
        for (int i = 0; i < s.length; i++) {
            try {
                s[i] = s[i].replaceAll(" ", "");
                n[i] = Integer.parseInt(s[i]);
            } catch (NumberFormatException e) {
            }
            if (n[i] <= 1000) {
                sum += n[i];
            }
        }
        for (int i = 0; i < n.length; i++) {
            if (n[i] < 0) {
                negatives.add(n[i]);
                exceptionDigits = exceptionDigits + " " + String.valueOf(n[i]);
            }
        }
        if (!negatives.isEmpty()) {
            throw new NegativesNotAllowedException(exceptionDigits);
        }
        return sum;
    }

}
