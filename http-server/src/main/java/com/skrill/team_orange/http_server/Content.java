package com.skrill.team_orange.http_server;

import java.io.InputStream;
import java.util.Scanner;

public class Content {

    Scanner sc = null;
    StringBuilder homePageBuilder = new StringBuilder();
    StringBuilder writePageBuilder = new StringBuilder();
    StringBuilder readPageBuilder = new StringBuilder();
    StringBuilder messagesPageBuilder = new StringBuilder();
    StringBuilder cssBuilder = new StringBuilder();
    StringBuilder successBuilder = new StringBuilder();
    StringBuilder noSuccessLoginBuilder = new StringBuilder();
    StringBuilder noSuccessRegisterBuilder = new StringBuilder();
    InputStream stream = null;
    ClassLoader loader = this.getClass().getClassLoader();
    String cleanReadPage;

    public Content() {
        try {

            stream = loader.getResourceAsStream("nosuccessregister.html");
            sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                noSuccessRegisterBuilder.append(sc.nextLine());
            }

            stream = loader.getResourceAsStream("nosuccesslogin.html");
            sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                noSuccessLoginBuilder.append(sc.nextLine());
            }

            stream = loader.getResourceAsStream("orange_style.css");
            sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                cssBuilder.append(sc.nextLine());
            }

            stream = loader.getResourceAsStream("homepage.html");
            sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                homePageBuilder.append(sc.nextLine());
            }

            stream = loader.getResourceAsStream("Messages.html");
            sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                messagesPageBuilder.append(sc.nextLine());
            }

            stream = loader.getResourceAsStream("read.html");
            sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                readPageBuilder.append(sc.nextLine());
            }
            cleanReadPage = readPageBuilder.toString();
            stream = loader.getResourceAsStream("write.html");
            sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                writePageBuilder.append(sc.nextLine());
            }
            stream = loader.getResourceAsStream("success.html");
            sc = new Scanner(stream);
            while (sc.hasNextLine()) {
                successBuilder.append(sc.nextLine());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sc.close();
                stream.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
    }

    public String getHomePage() {
        return homePageBuilder.toString();
    }

    public String getWritePage() {
        return writePageBuilder.toString();
    }

    public String getReadpage() {
        return readPageBuilder.toString();
    }

    public String getMessagesPage() {
        return messagesPageBuilder.toString();
    }

    public String getCSS() {
        return this.cssBuilder.toString();
    }

    public StringBuilder getReadPageBuilder() {
        return readPageBuilder;
    }

    public StringBuilder getHomePageBuilder() {
        return homePageBuilder;
    }

    public String getSuccessPage() {
        return successBuilder.toString();
    }

    public String getNoSuccessLogin() {
        return noSuccessLoginBuilder.toString();
    }

    public String getNoSuccessRegister() {
        return noSuccessRegisterBuilder.toString();
    }

    public void clearReadPage() {
        readPageBuilder.replace(0, readPageBuilder.length() - 1, cleanReadPage);
    }

}
