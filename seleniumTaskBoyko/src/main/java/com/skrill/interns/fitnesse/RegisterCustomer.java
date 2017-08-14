package com.skrill.interns.fitnesse;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.skrill.interns.selenium.RegisterNewUserTest;

public class RegisterCustomer {

    private String email;
    private String password;

    private static String[] emailAndPassword;

    private static String randomEmail;
    private static String randomPassword;

    public RegisterCustomer() {
        emailAndPassword = RegisterNewUserTest.generateUserAndPassword(RegisterNewUserTest.EMAIL_PREFIX, RegisterNewUserTest.EMAIL_SUFFIX);
        email = "";
        password = "";
        randomEmail = emailAndPassword[0];
        randomPassword = emailAndPassword[1];
    }

    public void setRandomEmail(String randomEmail) {
        RegisterCustomer.randomEmail = randomEmail;
    }

    public void setRandomPassword(String randomPassword) {
        RegisterCustomer.randomPassword = randomPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean registered(String username, String password) {
        String[] countryAndCurrency = RegisterNewUserTest.generateRandomCountryAndCurrency();
        String country = countryAndCurrency[0];
        String currency = countryAndCurrency[1];

        GregorianCalendar birthDate = RegisterNewUserTest.generateRandomBirthDate();
        boolean result = RegisterNewUserTest.registerUser(country, currency, RegisterNewUserTest.FIRST_NAME, RegisterNewUserTest.LAST_NAME,
                RegisterNewUserTest.ADDRESS, RegisterNewUserTest.CITY,
                RegisterNewUserTest.ZIP, RegisterNewUserTest.PHONE_NUMBER, username,
                birthDate.get(Calendar.DAY_OF_MONTH), birthDate.get(Calendar.MONTH),
                birthDate.get(Calendar.YEAR), password);
        try {
            RegisterNewUserTest.logout();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return result;
    }
	
	public boolean registered() {
        return registered(this.email, this.password);
    }

    public String getRandomEmail() {
        return randomEmail;
    }

    public String getRandomPassword() {
        return randomPassword;
    }

    public void closeDriver() {
        RegisterNewUserTest.closeDriver();
    }

    public boolean registerCustomerWithUsernameAndPassword(String username, String password) {
        return registered(username, password);
    }

    public boolean customerLogsInWithUsernameAndPassword(String username, String password) {
        return RegisterNewUserTest.login(username, password);
    }
}

/*---------TEST TABLE-------------
 * 
 * !contents -R2 -g -p -f -h
 * 
 * !define TEST_SYSTEM {slim}
 * 
 * |import |
 * |com.skrill.interns.selenium |
 * |com.skrill.interns.fitnesse|
 * 
 * |Register Customer|
 * |get random email?|get random password?|
 * |$EMAIL= |$PASSWORD= |
 * 
 * #|Register Customer|
 * #|get random email?|
 * #|$EMAIL2= |
 * 
 * #|Register Customer |
 * #|get random password?|
 * #|$PASSWORD= |
 * 
 * #!|Register Customer |
 * #|email |password |registered?|
 * #|$EMAIL |$PASSWORD|true |
 * #|$EMAIL2|$EMAIL2 |false |
 * 
 * !|script |Register Customer |
 * |register customer with username|$EMAIL |and password|$PASSWORD |
 * |reject |customer logs in with username|$EMAIL |and password|aaa |
 * |ensure |customer logs in with username|$EMAIL |and password|$PASSWORD|
 * 
 * |Register Customer|
 * |close driver? |
 * | |
 */
