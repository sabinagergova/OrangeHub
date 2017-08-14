package com.skrill.interns.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FitnesseScriptCoordinator {

    WebDriver driver = new FirefoxDriver();
    Coordinator coordinator = new Coordinator(driver);

    // public FitnesseScriptCoordinator(String email, String password) {
    // this.email = email;
    // this.password = password;
    // }

    public String generateRandomEmail() {
        return UserInputNecessities.getUsername();
    }

    public String generatePassword() {
        return UserInputNecessities.PASSWORD;
    }

    public boolean registerAUser(String email, String password) {
        boolean result = coordinator.register(email, password);
        return result;
    }

    public boolean logOutUser() {
        return coordinator.logout();
    }

    public boolean logInUser(String email, String password) {
        boolean result = coordinator.login(email, password);
        return result;
    }

    public boolean closeDriver() {
        driver.close();
        return true;
    }

}
