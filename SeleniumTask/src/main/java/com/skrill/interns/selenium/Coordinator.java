package com.skrill.interns.selenium;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Coordinator {

    private final WebDriver driver;
    Random r = new Random();
    private final WebDriverWait waiter;

    public Coordinator(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 10);
    }

    void setEmail(String email) {
    }

    void setPassword(String password) {
    }

    /**
     * A scenario for registering a new user.
     *
     * @param driver
     *            A WebDriver which conducts the scenario
     */
    public boolean register(String email, String password) {

        List<WebElement> countriesCurrenciesAndLanguageList;
        List<WebElement> birthControlList;
        WebElement element;

        driver.get(UserInputNecessities.SIGN_UP_PAGE);

        // Now in the register page

        // Opening the drop down menu for country
        driver.findElement(By.xpath(HtmlElements.DROP_DOWN_COUNTRY_XPATH)).click();

        // Selecting a country
        countriesCurrenciesAndLanguageList = driver.findElements(By.cssSelector(HtmlElements.COUNTRY_ENTITY_FROM_LIST_CSS_SELECTOR));
        randomizeDropDownMenus(countriesCurrenciesAndLanguageList).click();

        // Selecting a currency
        countriesCurrenciesAndLanguageList = driver.findElements(By.xpath(HtmlElements.CURRENCY_LIST_XPATH));
        randomizeDropDownMenus(countriesCurrenciesAndLanguageList).click();

        // Selecting a language
        countriesCurrenciesAndLanguageList = driver.findElements(By.cssSelector(HtmlElements.LANGUAGE_LIST_ID));
        randomizeDropDownMenus(countriesCurrenciesAndLanguageList).click();

        // Locating the text field for first name
        element = driver.findElement(By.id(HtmlElements.FIRST_NAME_FIELD_ID));
        element.sendKeys(UserInputNecessities.generateNames(UserInputNecessities.FIRST_NAME));

        // Locating the text field for last name
        element = driver.findElement(By.id(HtmlElements.LAST_NAME_FIELD_ID));
        element.sendKeys(UserInputNecessities.generateNames(UserInputNecessities.LAST_NAME));

        // Locating the text field for address
        driver.findElement(By.id(HtmlElements.ADDRESS_LINE_1_FIELD_ID)).sendKeys("32 Moon str");

        // Locating the text field for city
        driver.findElement(By.id(HtmlElements.CITY_FIELD_ID)).sendKeys("Capital city");

        // Locating the text field for zip code
        driver.findElement(By.id(HtmlElements.ZIP_CODE_FIELD_ID)).sendKeys("123456");

        // Locating the text field for phone number
        driver.findElement(By.id(HtmlElements.TELEPHONE_FIELD_ID)).sendKeys("00000000");


        // Locating the Continue to step 2 button
        driver.findElement(By.xpath(HtmlElements.NEXT_TO_STEP_2_BUTTON_XPATH)).click();

        // Now in step 2 page
        try {
        element = driver.findElement(By.id(HtmlElements.RECAPTCHA_WIDGET_BOX_ID));
        } catch (NoSuchElementException exc) {
            System.out.println("Couldn't find the recaptcha box");
            return false;
        }

        // Enter the email
        element = driver.findElement(By.id(HtmlElements.EMAIL_FIELD_ID));
        element.sendKeys(email);

        // Selecting date of birth
        birthControlList = driver.findElements(By.xpath(HtmlElements.DATE_OF_BIRTH_LIST_XPATH));
        randomizeBirthControlDropDownMenus(birthControlList).click();

        // Selecting month of birth
        birthControlList = driver.findElements(By.xpath(HtmlElements.MONTH_OF_BIRTH_LIST_XPATH));
        randomizeBirthControlDropDownMenus(birthControlList).click();

        // Selecting year of birth
        birthControlList = driver.findElements(By.xpath(HtmlElements.YEAR_OF_BIRTH_LIST_XPATH));
        randomizeBirthControlDropDownMenus(birthControlList).click();

        driver.findElement(By.id(HtmlElements.PASSWORD_FIELD_ID)).sendKeys(password); // filling the password
        driver.findElement(By.id(HtmlElements.PASSWORD_FIELD_CONFIRMATION_ID)).sendKeys(password); // confirming the password
        driver.findElement(By.xpath(HtmlElements.RECAPTCHA_FIELD_XPATH)).sendKeys(UserInputNecessities.RECAPTCHA); // filling the recaptcha


        driver.findElement(By.xpath(HtmlElements.REGISTER_BUTTON_XPATH)).click(); // registering the user



        try {
            driver.findElement(By.xpath(HtmlElements.SUCCESSFUL_REGISTRATION_MESSAGE_PATH));
        } catch (NoSuchElementException e) {
            System.out.println("Unsuccessful registration");
            return false;
        }
        return true;
    }

    public boolean login(String email, String password) {
        driver.get(UserInputNecessities.LOGIN_PAGE);

        driver.findElement(By.xpath(HtmlElements.EMAIL_FIELD_LOGIN)).sendKeys(email);
        driver.findElement(By.xpath(HtmlElements.PASSWORD_FIELD_LOGIN)).sendKeys(password);
        driver.findElement(By.xpath(HtmlElements.LOGIN_BUTTON)).click();

        try {
            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HtmlElements.BALANCE_BOX_XPATH)));
        } catch (TimeoutException exc) {
            return false;
        }
        return true;

    }

    public boolean logout() {
        try {
            driver.findElement(By.xpath(HtmlElements.LOGOUT_BUTTON_XPATH)).click();
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("You are not logged in.");
            return false;
        }

    }

    /**
     * A scenario for logging in and out
     *
     * @param driver
     *            A WebDriver object which will operate the scenario
     * @param secondsLoggedIn
     *            A long parameter which indicates the seconds to stay logged in
     */
    public void logInAndOutScenario(String username, String password, long secondsLoggedIn) {

        login(username, password);
        if (secondsLoggedIn < 5) {
            secondsLoggedIn = 5000;
        } else {
            secondsLoggedIn *= 1000;
        }
        try {
            Thread.sleep(secondsLoggedIn);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logout();
    }

    /**
     * Randomizes the choice of day, month or year of birth. Since the 0 index of the list in the drop down menu is not a possible option for selection, it is ignored.
     *
     * @param elements
     *            A list of WebElements which a random choice will be picked out of.
     * @return A random option of the provided list
     */
    private WebElement randomizeBirthControlDropDownMenus(List<WebElement> elements) {
        int randomChoice = r.nextInt(elements.size());
        if (randomChoice == 0) {
            randomChoice = 1;
        }
        return elements.get(randomChoice);
    }

    /**
     * Randomizes the choice of country, currency or language.
     *
     * @param elements
     *            A list of WebElements which a random choice will be picked out of.
     * @return A random option of the provided list
     */
    private WebElement randomizeDropDownMenus(List<WebElement> elements) {
        return elements.get(r.nextInt(elements.size()));
    }


}
