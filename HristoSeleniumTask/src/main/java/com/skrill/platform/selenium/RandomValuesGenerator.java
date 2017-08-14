package com.skrill.platform.selenium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RandomValuesGenerator {

    Random random = new Random();
    Map<String, String> logInCredentials = new HashMap<String, String>();
    public List<String> emailList = new ArrayList<String>();

    public WebElement getRandomValueFromWebElementList(String option, WebDriver driver) {

        List<WebElement> valuesList = new ArrayList<WebElement>();
        valuesList = driver.findElements(By.cssSelector(option));
        System.out.println(valuesList.size());
        int index = random.nextInt(valuesList.size());

        return valuesList.get(index);
    }

    public String generateEmail() {

        String firstPart = "hdimitrov";
        String lastPart = "@sun-fish.com";
        int randomNumber = random.nextInt(1000);
        StringBuilder sb = new StringBuilder();
        sb.append(firstPart);
        sb.append(randomNumber);
        sb.append(lastPart);
        return sb.toString();
    }

    public String generatePassword() {

        char[] chars = new char[] { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'k', 'j', 'h', 'g', 's', 'f', 's', 'd', 'a', 'n', 'z', 'm', 'x', 'v', 'b', 'n' };
        char[] upCaseCharsAndSpecial = new char[] { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'L', 'K', 'J', 'H', 'G', 'F', 'D', 'S', 'A', 'X', 'Z', 'C', 'V', 'B', 'N', 'M', '!',
                '@', '$', '^', '&', '*' };
        char[] numbersChar = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuilder passBuild = new StringBuilder();
        int lenght = random.nextInt(10) + 10;
        char randomChar;
        for (int i = 0; i < lenght / 3; i++) {
            randomChar = chars[random.nextInt(chars.length)];
            passBuild.append(randomChar);
            randomChar = upCaseCharsAndSpecial[random.nextInt(upCaseCharsAndSpecial.length)];
            passBuild.append(randomChar);
            randomChar = numbersChar[random.nextInt(numbersChar.length)];
            passBuild.append(randomChar);
        }

        System.out.println(passBuild.toString());
        return passBuild.toString();
    }
}
