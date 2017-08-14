package com.skrill.interns.selenium;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * $Id$
 *
 * Copyright 2013 Moneybookers Ltd. All Rights Reserved.
 * MONEYBOOKERS PROPRIETARY/CONFIDENTIAL. For internal use only.
 */

public class Scenario {
    public static void main(String[] args) {

        Coordinator coordinator = new Coordinator(new FirefoxDriver());
        String email = UserInputNecessities.getUsername();
        String password = UserInputNecessities.PASSWORD;
        coordinator.register(email, password);
        // coordinator.logInAndOutScenario("adichev.seleniumtest@sun-fish.com", "sElEniuMteSt1nG", 10);

    }
}
