package com.skrill.viewpoint.test;

import java.sql.Connection;

public abstract class ViewpointJob {

    protected Connection conn;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public abstract void startJob(String[] args);

    protected void logger(String message) {
        System.out.println(message);
    }

}
