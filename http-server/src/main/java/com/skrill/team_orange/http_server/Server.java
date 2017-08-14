package com.skrill.team_orange.http_server;

/**
 *
 * @author sabinagergova
 *
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    ServerSocket serverSocket = null;
    Hub hub = null;
    Content content = null;

    public Server() {

        System.out.println("Server ORANGE start :P ...");

        try {
            serverSocket = new ServerSocket(5661);
        } catch (IOException e) {
            System.out.println("Could not initiate a server socket ");
            System.exit(0);
        }

        hub = new Hub();
        content = new Content();

        while (true) {
            try {

                Socket clientSocket = serverSocket.accept();
                ClientManager thread = new ClientManager(clientSocket, hub, content);
                thread.start();
            } catch (IOException e) {
                System.out.println("Error while accepting connection");
            }

        }
    }

}
