package com.skrill.interns.SeleniumSabina;

public class FitnesseTest {

	public String email;
	public String password;

	public boolean registerUser() {
		RegisterUser.register(email, password);
		return RegisterUser.logout();
	}

	public void closingBrowser() {
		RegisterUser.closingBrowser();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
