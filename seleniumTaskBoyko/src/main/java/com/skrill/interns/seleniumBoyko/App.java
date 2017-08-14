/* 
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

public class App {
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

    public static void main(String[] args) {
        // wait = new WebDriverWait(driver, 10);

        String loginUrl = "https://my-integr.dev.moneybookers.net/login";
        Random rand = new Random();

        // get login page
        driver.get(loginUrl);

        // click on sign up button
        driver.findElement(By.linkText("Sign up")).click();

        // get country options list and its length
        Select dropdownCountry = new Select(driver.findElement(By.id("user_registration_address_country_id")));
        List<WebElement> options = dropdownCountry.getOptions();
        int optionsListLength = options.size();

        // choose random country, store it in a variable and try to get the currencies available for that country in order to choose random currency
        String country = options.get(rand.nextInt(optionsListLength + 1)).getText();
        dropdownCountry.selectByVisibleText(country);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id=\"user_registration_account_currency_id\"]/option")));

        // choose random currency for the already selected country and store it in a variable
        Select dropdownCurrency = new Select(driver.findElement(By.id("user_registration_account_currency_id")));
        options = dropdownCurrency.getOptions();
        optionsListLength = options.size();

        String currency = options.get(rand.nextInt(optionsListLength + 1)).getText();

        // generate a random email and password whose value is the email without the stuff beginning with @
        String[] emailAndPassword = generateUserAndPassword(EMAIL_PREFIX, EMAIL_SUFFIX);

        // generate a random birth date between 1950 and 1995
        GregorianCalendar birthDate = generateRandomBirthDate();



        // register user and store the result of the registration (successful or not) in a variable
        boolean result = registerUser(loginUrl, country, currency, FIRST_NAME, LAST_NAME, ADDRESS, CITY, ZIP, PHONE_NUMBER, emailAndPassword[0],
                birthDate.get(Calendar.DAY_OF_MONTH), birthDate.get(Calendar.MONTH),
                birthDate.get(Calendar.YEAR), emailAndPassword[1]);

        // report success
        System.out.println("Registration was: " + (result ? "successful" : "unsuccessful"));

        // log out
        logout();

        // login the already registered user
        login(loginUrl, emailAndPassword[0], emailAndPassword[1]);

        driver.close();

    }

    private static boolean registerUser(String loginUrl, String country, String currency, String firstName, String lastName, String address,
            String city, String zipCode, String phoneNumber, String email, int birthDay, int birthMonth, int birthYear, String password) {

        WebElement element;
        // get login page
        driver.get(loginUrl);

        // click on sign up button
        driver.findElement(By.linkText("Sign up")).click();

        // find and select country field
        Select dropdownCountry = new Select(driver.findElement(By.id("user_registration_address_country_id")));
        dropdownCountry.selectByVisibleText(country);

        // wait until currencies for that country are loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id=\"user_registration_account_currency_id\"]/option")));

        // select country
        Select dropdownCurrency = new Select(driver.findElement(By.id("user_registration_account_currency_id")));
        dropdownCurrency.selectByVisibleText(currency);

        // enter first name
        element = driver.findElement(By.id("user_registration_profile_first_name"));
        element.click();
        element.sendKeys(firstName);

        // enter last name
        element = driver.findElement(By.xpath("//*[@id=\"user_registration_profile_last_name\"]"));
        element.click();
        element.sendKeys(lastName);

        // enter address
        element = driver.findElement(By.xpath("//*[@id=\"user_registration_address_address_line_1\"]"));
        element.click();
        element.sendKeys(address);

        // enter city
        element = driver.findElement(By.xpath("//*[@id=\"user_registration_address_city\"]"));
        element.click();
        element.sendKeys(city);

        // enter zip code
        element = driver.findElement(By.xpath("//*[@id=\"user_registration_address_postal_code\"]"));
        element.click();
        element.sendKeys(zipCode);

        // enter phone number
        element = driver.findElement(By.id("user_registration_address_phone_phone"));
        element.sendKeys(phoneNumber);

        // //////////////////////// click on step 2 link////////////////
        element = driver.findElement(By.xpath("//*[@id=\"new_user_registration\"]/div[4]/input"));
        element.click();

        // enter email
        element = driver.findElement(By.id("user_registration_email"));
        element.sendKeys(email);

        // enter birth day
        element = driver.findElements(By.xpath("//select[@id=\"user_registration_profile_birth_date_3i\"]/option")).get(birthDay);
        element.click();

        // enter birth month
        element = driver.findElements(By.xpath("//select[@id=\"user_registration_profile_birth_date_2i\"]/option")).get(birthMonth);
        element.click();

        // enter birth year
        element = driver.findElements(By.xpath("//select[@id=\"user_registration_profile_birth_date_1i\"]/option")).get(1995 - birthYear);
        element.click();

        // enter password
        driver.findElement(By.id("user_registration_credentials_password")).sendKeys(password);
        driver.findElement(By.id("user_registration_credentials_password_confirmation")).sendKeys(password);

        // enter catpcha
        driver.findElement(By.xpath("//input[@id=\"recaptcha_response_field\"]")).sendKeys("*(_)$skrill98712=");

        // click accept and create account
        driver.findElement(By.xpath("//div[@class=\"inputs-inline\"]/input[@class=\"btn btn-primary pull-right\"]")).click();

        // check for successful registration
        try {
            element = driver.findElement(By.xpath("//div[contains(text(), \"Registration successful\")]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private static void login(String loginPageUrl, String userEmail, String password) {
        // get login page
        driver.get(loginPageUrl);
        WebElement element;

        // enter user email
        element = driver.findElement(By.xpath("//*[@id=\"user_authentication_email\"]"));
        element.sendKeys(userEmail);

        // enter password
        element = driver.findElement(By.xpath("//*[@id=\"user_authentication_password\"]"));
        element.sendKeys(password);

        // click login button
        element = driver.findElement(By.xpath("//*[@id=\"new_user_authentication\"]/div[3]/input"));
        element.submit();

    }

    private static void logout() {
        driver.findElement(By.xpath("/html/body/div[2]/div/div[4]/div/div[1]/a[1]")).click();
    }

    private static String[] generateUserAndPassword(String fixedPrefix, String fixedSuffix) {
        String email;
        String password;

        String emailBeforeAtSymbol = fixedPrefix + (1 + (int) Math.round(Math.random() * 2000000000));
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
}
