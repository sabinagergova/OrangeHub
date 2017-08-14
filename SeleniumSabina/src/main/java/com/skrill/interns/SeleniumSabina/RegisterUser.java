package com.skrill.interns.SeleniumSabina;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterUser {

    static WebElement webElement;
    static WebDriver driver = new FirefoxDriver();
    // static RemoteWebDriver driver;
    static WebDriverWait timeOut = new WebDriverWait(driver, 15);
    static List<WebElement> variable = new ArrayList<WebElement>();

    public static void register(String email, String password) {

        driver.get(IncommingOutgoingConstants.SIGNUP_URL);
        // driver.get(IncommingOutgoingConstants.SIGNUP_URL_STAGE);

        timeOut.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(IncommingOutgoingConstants.SELECT_COUNTRY_ID)));

        // webElement = setDropDownFileds(IncommingOutgoingConstants.SELECT_COUNTRY_ID);
        // webElement.click();

        // webElement = setDropDownFileds(IncommingOutgoingConstants.SELECT_CURRENCY_ID);
        // webElement.click();

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.FIRST_NAME_ID));
        webElement.sendKeys(IncommingOutgoingConstants.INPUT_FIRST_NAME);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.LAST_NAME_ID));
        webElement.sendKeys(IncommingOutgoingConstants.INPUT_LAST_NAME);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.ADDRESS_1_ID));
        webElement.sendKeys(IncommingOutgoingConstants.INPUT_ADDRESS_1);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.ADDRESS_2_ID));
        webElement.sendKeys(IncommingOutgoingConstants.INPUT_ADDRESS_2);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.CITY_ID));
        webElement.sendKeys(IncommingOutgoingConstants.INPUT_CITY);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.POST_CODE_ID));
        webElement.sendKeys(IncommingOutgoingConstants.INPUT_POST_CODE);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.PHONE_NUMBER_ID));
        webElement.sendKeys(IncommingOutgoingConstants.INPUT_PHONE_NUMBER);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.BUTTON_CONTINUE_CLASS));
        webElement.click();

        timeOut.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(IncommingOutgoingConstants.REGISTRATION_EMIAL_ID)));

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.REGISTRATION_EMIAL_ID));
        webElement.sendKeys(email);

        webElement = setDropDownDates(IncommingOutgoingConstants.BIRTH_DAY_ID);
        webElement.click();

        webElement = setDropDownDates(IncommingOutgoingConstants.BIRTH_MONTH_ID);
        webElement.click();

        webElement = setDropDownDates(IncommingOutgoingConstants.BIRTH_YEAR_ID);
        webElement.click();

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.REGISTRATION_PASSWORD_ID));
        webElement.sendKeys(password);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.CONFIRM_REGISTRATION_PASSWORD_ID));
        webElement.sendKeys(password);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.HUMAN_TEST_ID));
        webElement.sendKeys(IncommingOutgoingConstants.INPUT_HUMAN_TEST);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.BUTTON_CREATE_ACCOUNT_CLASS));
        webElement.click();

    }

    public static void login(String email, String password) {
        driver.get(IncommingOutgoingConstants.LOGIN_URL);

        timeOut.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(IncommingOutgoingConstants.LOGIN_EMIAL_ID)));

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.LOGIN_EMIAL_ID));
        webElement.sendKeys(email);

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.LOGIN_PASSWORD_ID));
        webElement.sendKeys(password);

        timeOut.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(IncommingOutgoingConstants.BUTTON_LOGIN_CLASS)));

        webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.BUTTON_LOGIN_CLASS));
        webElement.click();

    }

    public static boolean logout() {
        try {
            timeOut.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(IncommingOutgoingConstants.BUTTON_LOGOUT_CLASS)));
            webElement = driver.findElement(By.cssSelector(IncommingOutgoingConstants.BUTTON_LOGOUT_CLASS));
            webElement.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public static WebElement setDropDownFileds(String elements) {
        Random choice = new Random();
        variable = driver.findElements(By.cssSelector(elements));
        int number = choice.nextInt(variable.size());
        return variable.get(number);
    }

    public static WebElement setDropDownDates(String elements) {
        Random choice = new Random();
        variable = driver.findElements(By.cssSelector(elements));
        int number = (choice.nextInt(variable.size()) - 1);
        return variable.get(number + 1);

    }

    public static void closingBrowser() {
        driver.close();
    }

    public static void main(String[] args) {

        // StringBuilder email = IncommingOutgoingConstants.emailBuilder();
        // String password = IncommingOutgoingConstants.passwordBuilder(email);

        // try {
        // driver = new RemoteWebDriver(new URL(
        // IncommingOutgoingConstants.REMOTE_URL),
        // DesiredCapabilities.firefox());
        // } catch (MalformedURLException e) {
        // e.printStackTrace();
        // }

        // timeOut = new WebDriverWait(driver, 10);
        String email = "sabina.test.skrill.entity03@sun-fish.com";
        String password = "sabina.test.skrill.entity03";

        register(email, password);

        System.out.println("register");
        // logout();
        // System.out.println("log out");
        // login(email.toString(), password);
        // System.out.println("log in");
        // logout();
        // System.out.println("log out");
        closingBrowser();
        System.out.println("Browser closed");
    }
}
