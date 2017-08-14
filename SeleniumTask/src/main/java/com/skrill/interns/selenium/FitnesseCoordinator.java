package com.skrill.interns.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FitnesseCoordinator {

    String email;
    String password;
    WebDriver driver = new FirefoxDriver();
    Coordinator coordinator = new Coordinator(driver);

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean registerUser() {
        boolean result = coordinator.register(email, password);
        return result;
    }



}
