package com.skrill.team_orange.http_server;

public class RegisterCounter implements Subscriber {

    Subject s;
    int counter = 0;
    public RegisterCounter(Subject hub) {
        s = hub;
    }
    @Override
    public void update(String s) {
        if ("registration".equals(s)) {
            counter++;
            System.out.println("Registered users: " + counter);
        }
    }

}
