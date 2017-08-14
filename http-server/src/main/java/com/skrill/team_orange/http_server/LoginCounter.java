package com.skrill.team_orange.http_server;

public class LoginCounter implements Subscriber{
    private static final String LOGGED_USERS = "Logged in users: ";
    Subject hub;
	public int counter;

    public LoginCounter(Subject s) {
        hub = s;
	}

	@Override
    public void update(String command) {
		if(command.equals("login"))
		{
			counter++;
            System.out.println(LOGGED_USERS + counter);
		}
		else if(command.equals("logout")){
			counter--;
            System.out.println(LOGGED_USERS + counter);
		}
	}

}
