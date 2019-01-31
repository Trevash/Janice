package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import server.handlers.*;

public class ServerCommunicator {
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    public static void main(String[] args) {
        String portNumber = "8080";
        new ServerCommunicator().run(portNumber);
    }

    public void run(String portNumber) {
        System.out.println("Initializing HTTP ServerCommunicator");
        try {
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");
        //Create Handler contexts
        server.createContext("/", new handlerBase());
        server.createContext("/command", new commandHandler());

        server.start();
        System.out.println("ServerCommunicator started");

    }

    public void closeServer(){
        server.stop(0);
        server = null;
    }
}
