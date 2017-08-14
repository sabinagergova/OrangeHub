package com.skrill.team_orange.http_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ClientManager extends Thread {

    private final static int BODY_DELIMETER = 4; // represents the length of the
                                                 // substring which splits
                                                 // the head and the body
    Socket clientSocket = null;
    Hub hub = null;
    String requestHead = null;
    String requestBody = null;
    StringBuilder respondHead = new StringBuilder();
    StringBuilder respondBody = new StringBuilder();
    String username = null;
    String password = null;
    String cookie = "";
    Scanner sc = null;
    Content content = null;

    public ClientManager(Socket s, Hub h, Content c) {

        clientSocket = s;
        hub = h;
        content = c;

    }

    @Override
    public void run() {
        OutputStream output = null;
        InputStream input = null;
        try {
            output = clientSocket.getOutputStream();
            input = clientSocket.getInputStream();
            StringBuilder builder = new StringBuilder();
            int bytesRead = 0;
            byte[] chunk = new byte[256];
            do {
                bytesRead = input.read(chunk, 0, 256);
                String str = new String(chunk, 0, bytesRead);
                builder.append(str);
            } while (bytesRead == 256);
            String message = builder.toString();
            respondHead.append("HTTP/1.1 ");
            requestHead = parseHeaders(message);
            requestBody = parseBody(message);
            cookie = parseCookie(requestHead);
            String method = catchPostGet(message);
            String url = testURL(message);

            if ("POST".equals(method)) {
                if ("login".equals(url)) {
                    if (validateBodyInput(requestBody)) {
                        username = userNamePassword("username", requestBody);
                        password = userNamePassword("password", requestBody);
                        User user = hub.validateUser(username, password);
                        if (user != null) {
                            logIn(user);
                            respondHead.append("302 \r\nLocation: http://localhost:5661/messagecenter\r\n");
                            respondHead.append("Set-Cookie: " + user.getSessionID() + "\r\n");
                        } else {
                            respondHead.append("302 \r\nLocation: http://localhost:5661/nosuccesslogin\r\n");
                        }
                    } else {
                        respondBody.append("Sorry, you provided invalid parameter input for login");
                    }
                } else if ("register".equals(url)) {
                    if (validateBodyInput(requestBody)) {
                        Map<String, String> params = parseParams(requestBody);
                        username = params.get("username");
                        password = params.get("password");
                        if (username != null && password != null) {
                            registerUser(username, password);
                        } // the check for any input the fields is made in the html
                    } else {
                        System.out.println("Invalid parameters for registration!");
                        respondHead.append("302 \r\nLocation: http://localhost:5661/\r\n");
                    }
                } else if ("write".equals(url)) {
                    if (!"".equals(cookie)) {
                        User user = hub.getUserBySessionID(cookie);
                        if (user != null) {
                            Message m = new Message(user, requestBody);
                            hub.addMessage(user, m);
                            respondHead.append("302 \r\nLocation: http://localhost:5661/messagecenter\r\n");
                        } else {
                            respondHead.append("302 \r\nLocation: http://localhost:5661/\r\n");
                        }
                    } else {
                        respondBody.append("Something wrong with the cookie!");
                    }
                } else {
                    respondHead.append("200 OK\r\n");
                    respondBody.append("Method POST does not corespond to URL");
                }
            } else if ("GET".equals(method)) {
                if ("orange_style.css".equals(url)) {
                    respondBody.append(content.getCSS());
                } else if ("".equals(url)) {
                    if (!"".equals(cookie)) {
                        if (hub.getUserBySessionID(cookie) != null) {
                            if (hub.getUserBySessionID(cookie).getLogedIn()) {
                                respondHead.append("302 \r\nLocation: http://localhost:5661/messagecenter\r\n");
                            }
                        } else {
                            respondBody.append(content.getHomePage());
                        }
                    } else {
                        respondBody.append(content.getHomePage());
                    }
                } else if ("write".contains(url)) {
                    if (hub.getUserBySessionID(cookie) != null) {
                        respondBody.append(content.getWritePage());
                    } else {
                        respondHead.append("302 \r\nLocation: http://localhost:5661/\r\n");
                    }
                } else if ("read".contains(url)) {
                    content.clearReadPage();
                    if (!cookie.equals("")) {
                        if (validateCookie(cookie)) {
                            if (hub.getUserBySessionID(cookie) != null) {
                                String unreadMessages = readUnreadMessages(hub.getUserBySessionID(cookie));
                                String s = content.getReadPageBuilder().toString();
                                System.out.println(s);
                                s = String.format(s, unreadMessages);
                                respondBody.append(s);
                            } else {
                                respondHead.append("302 \r\nLocation: http://localhost:5661/\r\n");
                            }
                        } else {
                            respondBody.append("Wrong cookie input, TRY again");
                        }
                    } else {
                        respondBody.append("Something is wrong with you cookie");
                    }
                } else if ("logout".contains(url)) {
                    if (hub.getUserBySessionID(cookie) != null) {
                        logOut();
                        respondHead.append("302 \r\nLocation: http://localhost:5661/\r\n");
                        respondHead.append("Set-Cookie: sessID=1; Max-Age: 0\r\n");
                    } else {
                        respondHead.append("302 \r\nLocation: http://localhost:5661/\r\n");
                    }
                } else if ("messagecenter".contains(url)) {
                    if (hub.getUserBySessionID(cookie) != null) {
                        respondBody.append(content.getMessagesPage());
                    } else {
                        respondHead.append("302 \r\nLocation: http://localhost:5661/\r\n");
                    }
                } else if ("success".contains(url)) {
                    respondBody.append(content.getSuccessPage());
                } else if ("nosuccesslogin".equals(url)) {
                    respondBody.append(content.getNoSuccessLogin());
                } else {
                    respondHead.append("302 \r\nLocation: http://localhost:5661/\r\n");
                }
            }

            respondHead.append("Content-Type: text/html; charset=UTF-8\r\nLink: /styles.css ;rel=stylesheet\r\nServer: Orange\r\n\r\n");
            String response = (respondHead.append(respondBody.toString())).toString();

            byte[] respond1 = response.getBytes();
            output.write(respond1);

            output.flush();

        } catch (IOException e) {
            System.out.println("Error during the connection");
        } finally {
            try {
                input.close();
                output.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, String> parseParams(String paramsString) {
        Map<String, String> result = new HashMap<String, String>();
        String[] params = paramsString.split("&");
        for (String string : params) {
            String[] keyValue = string.split("=");
            if (keyValue.length == 2) {
                try {
                    result.put(URLDecoder.decode(keyValue[0], "utf8"), URLDecoder.decode(keyValue[1], "utf8"));
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalStateException();
                }
            }
        }
        return result;
    }

    public String parseBody(String request) {

        String body = null;
        if (request.contains("\r\n\r\n")) {
            int indexStart = request.indexOf("\r\n\r\n");
            int indexEnd = request.length();
            body = request.substring(indexStart + BODY_DELIMETER, indexEnd);
        } else {
            body = "";
        }
        return body;
    }

    public String parseHeaders(String message) {
        String head = null;
        if (message.contains("\r\n\r\n")) {
            int indexStart = 0;
            int indexEnd = message.indexOf("\r\n\r\n");
            indexEnd += 4;
            head = message.substring(indexStart, indexEnd);
        } else {
            head = "";
        }

        return head;
    }

    public String parseCookie(String head) {
        String cookie = null;
        if (head.contains("Cookie: ")) {
            int indexStart = head.indexOf("Cookie: ");
            indexStart += 8;
            int indexEnd = head.indexOf("\r\n", indexStart);
            cookie = head.substring(indexStart, indexEnd);
            return cookie;
        } else {
            return "";
        }
    }

    public String catchPostGet(String message) {
        String method = null;

        if (message.startsWith("POST")) {
            method = "POST";
        } else if (message.startsWith("GET")) {
            method = "GET";
        } else
            method = "wrong";

        return method;
    }

    public String testURL(String message) {

        String url = null;
        int indexStart = message.indexOf(" /");
        int indexEnd = message.indexOf("HTTP/");
        indexStart += 2; // the length of " /"
        indexEnd -= 1;
        url = message.substring(indexStart, indexEnd);
        if (url.isEmpty()) {
            return url;
        } else {
            String[] uri = url.split("[?]", 2);
            return uri[0];
        }
    }

    public String userNamePassword(String command, String message) {
        String[] params = message.split("&");
        for (String string : params) {
            String[] keyValue = string.split("=");
            if (command.equals(keyValue[0])) {
                return keyValue[1];
            }
        }
        return "";

    }

    public void writeMessage(User u, String message) {
        if (u.getLogedIn()) {
            String message1 = new String(u.getUsername() + " wrote:\n"
                    + message);
            Message m = new Message(u, message1);
            hub.addMessage(u, m);
        } else {

            respondBody.append("Please, log in first and then try to leave a message");
        }
    }

    public String readUnreadMessages(User user) {
        ArrayList<Message> tempListOfMessages = hub.getMessages();
        StringBuilder wholeText = new StringBuilder();
        if (user.getLogedIn()) {
            for (Message message : tempListOfMessages) {
                if (!message.getStatus(user)) {
                    wholeText.append(message.getText() + "\n...............\n");
                    message.setStatus(user, true);
                }
            }
        } else {
            wholeText.append("Sorry you haven't logged in! To read a message, please first login!");
        }
        return wholeText.toString();
    }

    public boolean validateBodyInput(String userBody) {
        if (userBody.matches("username=" + "[a-zA-Z0-9]+" + "&" + "password=" + "[a-zA-Z0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateCookie(String cookie) {
        if (cookie.matches("sessID=" + "[0-9]{3}")) {
            return true;
        } else {
            return false;
        }
    }

    public void registerUser(String username, String password) {
        if (hub.getUser(username) != null) {
            respondBody.append(content.getNoSuccessRegister());
        } else {
            User newUser = new User(username, password);
            hub.addUser(newUser);
            hub.sendFeed("registration");
            respondHead.append("302 \r\nLocation: http://localhost:5661/success\r\n");
        }
    }

    public void logIn(User user) {
        if (!user.getLogedIn()) {
            Random r = new Random();
            int i = r.nextInt(900);
            user.setLogedIn(true);
            hub.sendFeed("login");
            user.setSessionID("sessID=" + (i + 99));
        } else {
            respondBody.append("You are already logged in");
        }
    }

    public void logOut() {
        if (!"".equals(cookie)) {
            User user = hub.getUserBySessionID(cookie);
            if (user.getLogedIn()) {
                user.setSessionID("");
                user.setLogedIn(false);
                hub.sendFeed("logout");
            }
        } else {
            respondBody.append(content.getHomePage());
        }
    }

}
