package com.skrill.platform.selenium;

public class RegistrationProcess {

    public boolean register(String email, String pass) throws InterruptedException {

        boolean result = RegistrationFlow.register(email, pass);
        return result;
    }

    public void logout() throws InterruptedException {
        RegistrationFlow.logout();
    }

    public boolean login(String email, String pass) throws InterruptedException {

        boolean result = RegistrationFlow.login(email, pass);
        return result;
    }

}
