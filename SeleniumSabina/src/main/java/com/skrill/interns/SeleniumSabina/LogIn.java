package com.skrill.interns.SeleniumSabina;

public class LogIn {

	public String email;
	public String password;

	public boolean login() {
		RegisterUser.login(email, password);
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
