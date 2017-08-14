package com.skrill.team_orange.http_server;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class Message {

    private final String text;
    private final HashMap<User, Boolean> status = new HashMap<User, Boolean>();

    public Message(User user, String text) {
        User test = new User(user.getUsername(), user.getPassword());
        String text0 = user.getUsername().concat(" wrote: ");
        if (text.contains("message=")){
			 int indexStart = text.indexOf("message=");
			 indexStart += 8;//length of message:
			 text = text.substring(indexStart);
		 }
        this.text = text0.concat(text);
        status.put(test, true);
    }

    public String getText() {
    	String finalMessage = null;
    	try {
    		finalMessage = URLDecoder.decode(text, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			finalMessage = "something went wrong!";
			e.printStackTrace();
		}
    	return finalMessage;
    }

    public boolean getStatus(User user) {
        Boolean result = status.get(user);
        if (result == null) {
            return false;
        }
        return result;
    }

    public void setStatus(User u, Boolean st) {
        status.put(u, st);
    }

}
