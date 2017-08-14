package com.skrill.team_orange.http_server;


public class User {

    private String username;
    private String password;
    private boolean logedIn;
    private String sessionID = "";

    public User() {
        super();
    }

    public User(String name, String pass) {
        this.username = name;
        this.password = pass;
        this.logedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setLogedIn(boolean login) {
        this.logedIn = login;
    }

    public boolean getLogedIn() {
        return this.logedIn;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }

}
