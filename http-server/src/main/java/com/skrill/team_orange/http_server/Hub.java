package com.skrill.team_orange.http_server;

import java.util.ArrayList;

public class Hub implements Subject {
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Message> messages = new ArrayList<Message>();
    LoginCounter loginCounter = new LoginCounter(this);
    RegisterCounter registerCounter = new RegisterCounter(this);
    ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();

    public Hub() {
        this.subscribe(loginCounter);
        this.subscribe(registerCounter);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(User u, Message m) {
    	messages.add(m);
        m.setStatus(u, true);

    }

    public void addUser(User u) {
        users.add(u);
    }

    public User validateUser(String username, String password) {

        User user = getUser(username);
        if (user != null) {
            String pass = user.getPassword();
            if (password.equals(pass)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Returns a user object by the username and password
     *
     * @param username
     *            - the username of the User
     * @param password
     *            - the password of the User
     * @return An object of type {@link}User by the provided username and password
     */

    public User getUser(String username) {
        User result = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                result = users.get(i);
                break;
            }
        }

        return result;
    }

    public User getUserBySessionID(String cookie) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getSessionID().equals(cookie)) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public void sendFeed(String string) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).update(string);
        }
    }

    @Override
    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    @Override
    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

}
