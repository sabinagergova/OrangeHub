package com.skrill.interns.selenium;

public class HtmlElements {

    // ELEMENTS FOR REGISTERING
    public static final String DROP_DOWN_COUNTRY_XPATH = "//*[@id=\"s2id_user_registration_address_country_id\"]/a/div/b";
    public static final String COUNTRY_ENTITY_FROM_LIST_CSS_SELECTOR = "#user_registration_address_country_id option";
    public static final String LANGUAGE_LIST_ID = "#user_registration_profile_language_id option";
    public static final String CURRENCY_LIST_XPATH = "//*[@id=\"user_registration_account_currency_id\"]/option";
    public static final String FIRST_NAME_FIELD_ID = "user_registration_profile_first_name";
    public static final String LAST_NAME_FIELD_ID = "user_registration_profile_last_name";
    public static final String ADDRESS_LINE_1_FIELD_ID = "user_registration_address_address_line_1";
    public static final String CITY_FIELD_ID = "user_registration_address_city";
    public static final String ZIP_CODE_FIELD_ID = "user_registration_address_postal_code";
    public static final String TELEPHONE_FIELD_ID = "user_registration_address_phone_phone";
    public static final String NEXT_TO_STEP_2_BUTTON_XPATH = "//*[@id=\"new_user_registration\"]/div[4]/input";
    public static final String EMAIL_FIELD_ID = "user_registration_email";
    public static final String DATE_OF_BIRTH_LIST_XPATH = "//select[@id=\"user_registration_profile_birth_date_3i\"]/option";
    public static final String MONTH_OF_BIRTH_LIST_XPATH = "//select[@id=\"user_registration_profile_birth_date_2i\"]/option";
    public static final String YEAR_OF_BIRTH_LIST_XPATH = "//select[@id=\"user_registration_profile_birth_date_1i\"]/option";
    public static final String PASSWORD_FIELD_ID = "user_registration_credentials_password";
    public static final String PASSWORD_FIELD_CONFIRMATION_ID = "user_registration_credentials_password_confirmation";
    public static final String RECAPTCHA_FIELD_XPATH = "//input[@id=\"recaptcha_response_field\"]";
    public static final String RECAPTCHA_WIDGET_BOX_ID = "recaptcha_widget_div";
    public static final String REGISTER_BUTTON_XPATH = "//div[@class=\"inputs-inline\"]/input[@class=\"btn btn-primary pull-right\"]";

    // ELEMENTS FOR LOGGING IN
    public static final String EMAIL_FIELD_LOGIN = "//*[@id=\"user_authentication_email\"]";
    public static final String PASSWORD_FIELD_LOGIN = "//*[@id=\"user_authentication_password\"]";
    public static final String LOGIN_BUTTON = "//*[@id=\"new_user_authentication\"]/div[3]/input";
    public static final String BALANCE_BOX_XPATH = "//div[@class=\"two-box\"]/div[@class=\"box balance_panel box-up double-padding\"]";
    public static final String UNSUCCESSFUL_LOGIN_ATTEMPT_XPATH = "//div[@class=\"error-messages\"]/div[@class=\"alert alert-error\"]";

    // ELEMENTS FOR LOGGING OUT
    public static final String LOGOUT_BUTTON_XPATH = "//div[@class=\"user-actions\"]/a[@class=\"btn logout-btn btn-header-grey hidden-mobile\"]";

    // ELEMENTS IN SUCCESSFUL REGISTRATION PAGE
    public static final String SUCCESSFUL_REGISTRATION_MESSAGE_PATH = "//div[@id=\"success-messages\"]/div[@class=\"alert alert-success\"]";


}
