package com.skrill.interns.SeleniumSabina;

import java.util.Random;

public class IncommingOutgoingConstants {

    public static final String LOGIN_URL = "https://my-integr.dev.moneybookers.net/login";
    public static final String SIGNUP_URL = "https://my-integr.dev.moneybookers.net/signup";
    public static final String SIGNUP_URL_STAGE = "https://my-stage.dev.moneybookers.net/signup";
    public static final String REMOTE_URL = "http://automationvl1.dev.moneybookers.net:4444/wd/hub";
    public static final String SELECT_COUNTRY_ID = "#user_registration_address_country_id option";
    public static final String SELECT_CURRENCY_ID = "#user_registration_account_currency_id option";
    public static final String FIRST_NAME_ID = "#user_registration_profile_first_name";
    public static final String LAST_NAME_ID = "#user_registration_profile_last_name";
    public static final String ADDRESS_1_ID = "#user_registration_address_address_line_1";
    public static final String ADDRESS_2_ID = "#user_registration_address_address_line_2";
    public static final String CITY_ID = "#user_registration_address_city";
    public static final String POST_CODE_ID = "#user_registration_address_postal_code";
    public static final String PHONE_NUMBER_ID = "#user_registration_address_phone_phone";
    public static final String BUTTON_CONTINUE_CLASS = ".btn.btn-primary.pull-right";
    public static final String REGISTRATION_EMIAL_ID = "#user_registration_email";
    public static final String BIRTH_DAY_ID = "#user_registration_profile_birth_date_3i option";
    public static final String BIRTH_MONTH_ID = "#user_registration_profile_birth_date_2i option";
    public static final String BIRTH_YEAR_ID = "#user_registration_profile_birth_date_1i option";
    public static final String REGISTRATION_PASSWORD_ID = "#user_registration_credentials_password";
    public static final String CONFIRM_REGISTRATION_PASSWORD_ID = "#user_registration_credentials_password_confirmation";
    public static final String HUMAN_TEST_ID = "#recaptcha_response_field";
    public static final String BUTTON_CREATE_ACCOUNT_CLASS = ".btn.btn-primary.pull-right";
    public static final String LOGIN_EMIAL_ID = "#user_authentication_email";
    public static final String LOGIN_PASSWORD_ID = "#user_authentication_password";
    public static final String BUTTON_LOGIN_CLASS = ".btn.btn-primary";
    public static final String BUTTON_LOGOUT_CLASS = ".btn.logout-btn.btn-header-grey.hidden-mobile";

    public static final String INPUT_FIRST_NAME = "Test";
    public static final String INPUT_LAST_NAME = "Test";
    public static final String INPUT_ADDRESS_1 = "Slavia";
    public static final String INPUT_ADDRESS_2 = "Sl";
    public static final String INPUT_CITY = "Sofia";
    public static final String INPUT_POST_CODE = "1000";
    public static final String INPUT_PHONE_NUMBER = "7896541230";
    public static final String INPUT_HUMAN_TEST = "*(_)$skrill98712=";

    static StringBuilder emailBuilder = new StringBuilder();
    public static Random randomGenerator = new Random();
    public static final int LENGTH = 4;
    public static final String POSIBLE_RANDOM_VALUES = "1234567890";

    public static StringBuilder emailBuilder() {
        StringBuilder randomString = new StringBuilder();
        emailBuilder.append("sabina.intern.test.selenium1.");
        for (int i = 0; i < LENGTH; i++) {
            int position = randomGenerator.nextInt(POSIBLE_RANDOM_VALUES.length());
            char value = POSIBLE_RANDOM_VALUES.charAt(position);
            randomString.append(value);
        }
        emailBuilder.append(randomString);
        emailBuilder.append("@sun-fish.com");
        return emailBuilder;
    }

    public static String passwordBuilder(StringBuilder email) {
        int endOfUserName = email.indexOf("@");
        String password = email.substring(0, endOfUserName);
        return password;

    }
}