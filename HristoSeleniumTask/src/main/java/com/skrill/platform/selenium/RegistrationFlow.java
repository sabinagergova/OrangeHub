package com.skrill.platform.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationFlow {

    static WebDriver driver;
    static RandomValuesGenerator generator = new RandomValuesGenerator();

    public static boolean register(String regEmail, String regPass) throws InterruptedException {

        driver = new FirefoxDriver();
        driver.get(ConstantsForRegistrationForm.LOGIN_SITE);

        WebElement signUp = driver.findElement(By.cssSelector(".user-actions a"));
        signUp.click();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        WebElement chooseCountry = generator.getRandomValueFromWebElementList(ConstantsForRegistrationForm.COUNTRY_SELECTION_OPTION, driver);
        chooseCountry.click();

        WebElement chooseCurrency = generator.getRandomValueFromWebElementList(ConstantsForRegistrationForm.CURRENCY_SELECTION_OPTION, driver);
        chooseCurrency.click();

        WebElement chooseLanguage = generator.getRandomValueFromWebElementList(ConstantsForRegistrationForm.LANGUAGE_SELECTION_OPTION, driver);
        chooseLanguage.click();

        WebElement firstName = driver.findElement(By.xpath(ConstantsForRegistrationForm.FIRST_NAME_SELECTION_XPATH));
        firstName.click();
        firstName.sendKeys(ConstantsForRegistrationForm.MY_FIRST_NAME);

        WebElement lastName = driver.findElement(By.xpath(ConstantsForRegistrationForm.LAST_NAME_SELECTION_XPATH));
        lastName.click();
        lastName.sendKeys(ConstantsForRegistrationForm.MY_LAST_NAME);

        WebElement addressLine1 = driver.findElement(By.xpath(ConstantsForRegistrationForm.ADDRESS_LINE1_SELECTION_XPATH));
        addressLine1.click();
        addressLine1.sendKeys(ConstantsForRegistrationForm.MY_ADDRESS_LINE1);

        WebElement city = driver.findElement(By.xpath(ConstantsForRegistrationForm.CITY_SELECTION_XPATH));
        city.click();
        city.sendKeys(ConstantsForRegistrationForm.MY_CITY_OF_RESIDENCE);

        WebElement zipCode = driver.findElement(By.xpath(ConstantsForRegistrationForm.ZIPCODE_SELECTION_XPATH));
        zipCode.click();
        zipCode.sendKeys(ConstantsForRegistrationForm.MY_POSTAL_CODE);

        WebElement choosePhoneType = driver.findElement(By.xpath(ConstantsForRegistrationForm.PHONETYPE_SELECTION_XPATH));
        choosePhoneType.click();
        choosePhoneType.sendKeys("Mobile Number");

        WebElement phoneNumber = driver.findElement(By.xpath(ConstantsForRegistrationForm.PHONENUMBER_SELECTION_XPATH));
        phoneNumber.click();
        phoneNumber.sendKeys(ConstantsForRegistrationForm.MY_MOBILE_NUMBER, Keys.ENTER);
        System.out.println("finnished with step 1");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        WebElement email = driver.findElement(By.xpath(ConstantsForRegistrationForm.EMAIL_FOR_REGISTRATION_XPATH));
        email.click();
        email.sendKeys(regEmail);

        WebElement yearOfBirth = generator.getRandomValueFromWebElementList(ConstantsForRegistrationForm.YEAR_OF_BIRTH_DROPDOWN_OPTION, driver);
        yearOfBirth.click();

        WebElement monthOfBirth = generator.getRandomValueFromWebElementList(ConstantsForRegistrationForm.MONTH_OF_BIRTH_DROPDOWN_OPTION, driver);
        monthOfBirth.click();

        WebElement dayOfBirth = generator.getRandomValueFromWebElementList(ConstantsForRegistrationForm.DAY_OF_BIRTH_DROPDOWN_OPTION, driver);
        dayOfBirth.click();

        WebElement password = driver.findElement(By.xpath(ConstantsForRegistrationForm.PASSWORD_FOR_REGISTRATION_XPATH));
        password.click();
        password.sendKeys(regPass);

        WebElement repeatPassword = driver.findElement(By.xpath(ConstantsForRegistrationForm.CONFIRM_PASSWORD_FOR_REGISTRATION_XPATH));
        repeatPassword.click();
        repeatPassword.sendKeys(regPass);

        WebElement captcha = driver.findElement(By.xpath(ConstantsForRegistrationForm.CAPTCHA_XPATH));
        captcha.click();
        captcha.sendKeys("*(_)$skrill98712=", Keys.ENTER);
        System.out.println("finnished with step 2");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        if (driver.findElements(By.cssSelector(ConstantsForRegistrationForm.LOGOUT_BUTTON)).size() == 0) {
            return false;
        }

        generator.logInCredentials.put(regEmail, regPass);
        generator.emailList.add(regEmail);
        return true;

    }

    public static boolean login(String email, String pass) throws InterruptedException {

        driver = new FirefoxDriver();
        driver.get(ConstantsForRegistrationForm.LOGIN_SITE);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        WebElement enterEmail = driver.findElement(By.xpath(ConstantsForRegistrationForm.LOGIN_EMAIL_XPATH));
        enterEmail.sendKeys(email);
        enterEmail.click();
        WebElement enterPass = driver.findElement(By.xpath(ConstantsForRegistrationForm.LOGIN_PASS_XPATH));
        enterPass.sendKeys(pass, Keys.ENTER);
        System.out.println("finnished with step 3");
        WebDriverWait waitForLogOut = new WebDriverWait(driver, 10);
        try {
            waitForLogOut.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ConstantsForRegistrationForm.LOGOUT_BUTTON)));
        } catch (TimeoutException e) {
            driver.close();
            return false;
        }

        return true;

    }

    public static void logout() throws InterruptedException {
        WebDriverWait waitForLogOut = new WebDriverWait(driver, 30);
        waitForLogOut.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ConstantsForRegistrationForm.LOGOUT_BUTTON)));
        WebElement logout = driver.findElement(By.cssSelector(ConstantsForRegistrationForm.LOGOUT_BUTTON));
        logout.click();
        System.out.println("finnished with step 4");
        driver.close();
    }
}
