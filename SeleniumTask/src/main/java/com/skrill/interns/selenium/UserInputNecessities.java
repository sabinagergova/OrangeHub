package com.skrill.interns.selenium;

import java.awt.RadialGradientPaint;
import java.util.Random;
import java.util.UUID;

public class UserInputNecessities {

    // USER NECESSITIES
    public static final String USERNAME = "adichev.";
    public static final String PASSWORD = "sElEniuMt234eSt1nG";
    public static final String RECAPTCHA = "*(_)$skrill98712=";
    public static final String LOGIN_PAGE = "https://my-integr.dev.moneybookers.net/login";
    public static final String SIGN_UP_PAGE = "https://my-integr.dev.moneybookers.net/signup";
    public static final String DASHBOARD = "https://my-integr.dev.moneybookers.net/dashboard";
    public static final String EMAIL_SUFFIX = "@sun-fish.com";
    public static final String REMOTE_HUB = "http://automationvl1.dev.moneybookers.net:4444/wd/hub";
    private static final String[] FIRST_NAMES = { "Atanas", "Boyan", "Mahmud", "Mustafa", "Gilbert", "Johnatan", "David", "Makelele", "Ozgul", "Kevin" };
    private static final String[] LAST_NAMES = { "Dimitrov", "Simbakala", "Osman", "Hsi-Chung", "Meleeka", "Bozhkov", "Perez", "Carlos" };
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";

    public static String getUsername() {
        return generateUserName() + EMAIL_SUFFIX;
    }

    public static String generateUserName() {
        UUID random = UUID.randomUUID();
        String randomString = random.toString();
        String result = randomString.substring(0, randomString.indexOf("-"));
        System.out.println("User email: " + USERNAME + result + EMAIL_SUFFIX);
        return USERNAME + result;
    }

    public static String generateNames(String type) {
        if (type.equals(FIRST_NAME)) {
            return FIRST_NAMES[new Random().nextInt(FIRST_NAMES.length)];
        } else if (type.equals(LAST_NAME)) {
            return LAST_NAMES[new Random().nextInt(LAST_NAMES.length)];
        } else {
            return "";
        }
    }

    public static String getPassword() {
        return PASSWORD;
    }

}
