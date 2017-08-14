package com.skrill.interns.SeleniumSabina;

public class FitnesseTestScripts {

	public boolean registerUser(String email, String password) {
		RegisterUser.register(email, password);
		return RegisterUser.logout();
	}

	public boolean login(String email, String password) {
		RegisterUser.login(email, password);
		return RegisterUser.logout();
	}

	public void closingBrowser() {
		RegisterUser.closingBrowser();
	}

}
