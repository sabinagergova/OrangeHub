/* 
 * Write a simple Selenium code, that will register a new user here:
https://my-integr.dev.moneybookers.net/login

Verify successful registration. Fill in the captcha with:
 *(_)$skrill98712= 

For bonus points:
Write a login/logout methods.

For extra bonus points:
Use remote driver – Firefox;
URL of the remote hub: http://automationvl1.dev.moneybookers.net:4444/wd/hub
 */
package com.skrill.interns.seleniumBoyko;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterNewUserTest {
    static WebDriver driver = new FirefoxDriver();
    // static WebDriver driver;
    static WebDriverWait wait = new WebDriverWait(driver, 10);
    // static WebDriverWait wait;
    public static final String FIRST_NAME = "Pesho";
    public static final String LAST_NAME = "Ivanov";
    public static final String ADDRESS = "23 Some Streetname";
    public static final String CITY = "Sofia";
    public static final String ZIP = "1000";
    public static final String PHONE_NUMBER = "887223344";
    public static final String EMAIL_PREFIX = "boyko.test";
    public static final String EMAIL_SUFFIX = "@sun-fish.com";

    // ////// Locators for web elements ////////////
    public static final String LOGIN_URL = "https://my-integr.dev.moneybookers.net/login";
    public static final String SIGN_UP_URL = "https://my-integr.dev.moneybookers.net/signup";
    public static final String DROPDOWN_COUNTRY_ID = "user_registration_address_country_id";
    public static final String OPTIONS_FOR_DROPDOWN_CURRENCY_XPATH = "//select[@id=\"user_registration_account_currency_id\"]/option";
    public static final String DROPDOWN_CURRENCY_ID = "user_registration_account_currency_id";
    public static final String FIRST_NAME_ID = "user_registration_profile_first_name";
    public static final String LAST_NAME_XPATH = "//*[@id=\"user_registration_profile_last_name\"]";
    public static final String ADDRESS_XPATH = "//*[@id=\"user_registration_address_address_line_1\"]";
    public static final String CITY_XPATH = "//*[@id=\"user_registration_address_city\"]";
    public static final String ZIP_CODE_XPATH = "//*[@id=\"user_registration_address_postal_code\"]";
    public static final String PHONE_NUMBER_ID = "user_registration_address_phone_phone";
    public static final String LINK_TO_STEP_TWO_XPATH = "//*[@id=\"new_user_registration\"]/div[4]/input";
    public static final String USER_EMAIL_ID = "user_registration_email";
    public static final String BIRTH_DAY_XPATH = "//select[@id=\"user_registration_profile_birth_date_3i\"]/option";
    public static final String BIRTH_MONTH_XPATH = "//select[@id=\"user_registration_profile_birth_date_2i\"]/option";
    public static final String BIRTH_YEAR_XPATH = "//select[@id=\"user_registration_profile_birth_date_1i\"]/option";
    public static final String PASSWORD_ID = "user_registration_credentials_password";
    public static final String PASSWORD_CONFIRMATION_ID = "user_registration_credentials_password_confirmation";
    public static final String CAPTCHA_XPATH = "//input[@id=\"recaptcha_response_field\"]";
    public static final String CREATE_ACCOUNT_XPATH = "//div[@class=\"inputs-inline\"]/input[@class=\"btn btn-primary pull-right\"]";
    public static final String SUCCESSFUL_REGISTRATION_DIV_XPATH = "//div[contains(text(), \"Registration successful\")]";
    public static final String LOGIN_EMAIL_XPATH = "//*[@id=\"user_authentication_email\"]";
    public static final String LOGIN_PASSWORD_XPATH = "//*[@id=\"user_authentication_password\"]";
    public static final String LOGIN_BUTTON_XPATH = "//*[@id=\"new_user_authentication\"]/div[3]/input";
    public static final String LOGOUT_BUTTON_CSS = ".btn.logout-btn.btn-header-grey.hidden-mobile";

    public static void main(String[] args) {
        
        String[] countryAndCurrency = generateRandomCountryAndCurrency();
        String country = countryAndCurrency[0];
        String currency = countryAndCurrency[1];

        // generate a random email and password whose value is the email without the stuff beginning with @
        String[] emailAndPassword = generateUserAndPassword(EMAIL_PREFIX, EMAIL_SUFFIX);

        // generate a random birth date between 1950 and 1995
        GregorianCalendar birthDate = generateRandomBirthDate();

        // register user and store the result of the registration (successful or not) in a variable
        boolean result = registerUser(LOGIN_URL, country, currency, FIRST_NAME, LAST_NAME, ADDRESS, CITY, ZIP, PHONE_NUMBER, emailAndPassword[0],
                birthDate.get(Calendar.DAY_OF_MONTH), birthDate.get(Calendar.MONTH),
                birthDate.get(Calendar.YEAR), emailAndPassword[1]);

        // check if registration was successful
        if (result) {
            System.out.println("Registration was successful.\n The newly registered user has an email:" + emailAndPassword[0] + "\n" +
                    "and a password: " + emailAndPassword[1]);

            // log out
            logout();

            // login the already registered user
            login(emailAndPassword[0], emailAndPassword[1]);
        } else
            System.out.println("Registration was unsuccessful.");

        driver.close();

    }

    private static boolean registerUser(String loginUrl, String country, String currency, String firstName, String lastName, String address,
            String city, String zipCode, String phoneNumber, String email, int birthDay, int birthMonth, int birthYear, String password) {

        // get sign up page
        driver.get(SIGN_UP_URL);

        // find and select country field
        Select dropdownCountry = new Select(driver.findElement(By.id(DROPDOWN_COUNTRY_ID)));
        dropdownCountry.selectByVisibleText(country);

        // wait until currencies for that country are loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OPTIONS_FOR_DROPDOWN_CURRENCY_XPATH)));

        // select country
        Select dropdownCurrency = new Select(driver.findElement(By.id(DROPDOWN_CURRENCY_ID)));
        dropdownCurrency.selectByVisibleText(currency);

        // enter first name
        driver.findElement(By.id(FIRST_NAME_ID)).click();
        driver.findElement(By.id(FIRST_NAME_ID)).sendKeys(firstName);

        // enter last name
        driver.findElement(By.xpath(LAST_NAME_XPATH)).click();
        driver.findElement(By.xpath(LAST_NAME_XPATH)).sendKeys(lastName);

        // enter address
        driver.findElement(By.xpath(ADDRESS_XPATH)).click();
        driver.findElement(By.xpath(ADDRESS_XPATH)).sendKeys(address);

        // enter city
        driver.findElement(By.xpath(CITY_XPATH)).click();
        ;
        driver.findElement(By.xpath(CITY_XPATH)).sendKeys(city);

        // enter zip code
        driver.findElement(By.xpath(ZIP_CODE_XPATH)).click();
        ;
        driver.findElement(By.xpath(ZIP_CODE_XPATH)).sendKeys(zipCode);

        // enter phone number
        driver.findElement(By.id(PHONE_NUMBER_ID)).sendKeys(phoneNumber);

        // //////////////////////// click on step 2 link////////////////
        driver.findElement(By.xpath(LINK_TO_STEP_TWO_XPATH)).click();
        ;

        // enter email
        driver.findElement(By.id(USER_EMAIL_ID)).sendKeys(email);
        ;

        // enter birth day
        driver.findElements(By.xpath(BIRTH_DAY_XPATH)).get(birthDay).click();
        ;

        // enter birth month
        driver.findElements(By.xpath(BIRTH_MONTH_XPATH)).get(birthMonth).click();
        ;

        // enter birth year
        driver.findElements(By.xpath(BIRTH_YEAR_XPATH)).get(1995 - birthYear).click();
        ;

        // enter password
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(password);
        driver.findElement(By.id(PASSWORD_CONFIRMATION_ID)).sendKeys(password);

        // enter catpcha
        driver.findElement(By.xpath(CAPTCHA_XPATH)).sendKeys("*(_)$skrill98712=");

        // click accept and create account
        driver.findElement(By.xpath(CREATE_ACCOUNT_XPATH)).click();

        // check for successful registration
        try {
            driver.findElement(By.xpath(SUCCESSFUL_REGISTRATION_DIV_XPATH));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private static void login(String userEmail, String password) {
        // get login page
        driver.get(LOGIN_URL);

        // enter user email
        driver.findElement(By.xpath(LOGIN_EMAIL_XPATH)).sendKeys(userEmail);
        ;

        // enter password
        driver.findElement(By.xpath(LOGIN_PASSWORD_XPATH)).sendKeys(password);

        // click login button
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).submit();
        ;

    }

    private static void logout() {
        // driver.findElement(By.xpath("/html/body/div[2]/div/div[4]/div/div[1]/a[1]")).click();
        driver.findElement(By.cssSelector(LOGOUT_BUTTON_CSS)).click();
        ;
    }

    private static String[] generateUserAndPassword(String fixedPrefix, String fixedSuffix) {
        String email;
        String password;
        Random rand = new Random();

        String emailBeforeAtSymbol = fixedPrefix + (rand.nextInt(2000000000) + 1);
        email = emailBeforeAtSymbol + fixedSuffix;
        password = emailBeforeAtSymbol;

        return new String[] { email, password };
    }

    private static GregorianCalendar generateRandomBirthDate() {
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1950, 1995);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return gc;
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private static String[] generateRandomCountryAndCurrency() {
        Random rand = new Random();

        // get sign up page
        driver.get(SIGN_UP_URL);

        // get country options list and its length
        Select dropdownCountry = new Select(driver.findElement(By.id(DROPDOWN_COUNTRY_ID)));
        List<WebElement> options = dropdownCountry.getOptions();
        int optionsListLength = options.size();

        // choose random country, store it in a variable and try to get the currencies available for that country in order to choose random currency
        String country = options.get(rand.nextInt(optionsListLength)).getText();
        dropdownCountry.selectByVisibleText(country);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OPTIONS_FOR_DROPDOWN_CURRENCY_XPATH)));

        // choose random currency for the already selected country and store it in a variable
        Select dropdownCurrency = new Select(driver.findElement(By.id(DROPDOWN_CURRENCY_ID)));
        options = dropdownCurrency.getOptions();
        optionsListLength = options.size();

        String currency = options.get(rand.nextInt(optionsListLength + 1)).getText();

        return new String[] { country, currency };
    }
}
