package com.skrill.team_orange.http_server;

public interface Subject {
    public void subscribe(Subscriber s);
    public void unsubscribe(Subscriber s);
    public void sendFeed(String string);
}
