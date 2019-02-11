package com.janus.Communication;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ServerProxy {
	private static ServerProxy scp;
    private static TtRClient client;

    private Results messageResult;

    private ServerProxy() {
        try {
			client = new TtRClient(new URI("ws://10.24.217.239:8087"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    static ServerProxy getInstance() {
        if (scp == null){
            scp = new ServerProxy();
        }
        return scp;
    }

    
    private String className = "server.handlers";

    public void connectClient() throws InterruptedException {
        client.connectBlocking();
    }

    public void disconnectClient() throws InterruptedException {
        client.closeBlocking();
    }
    
    public Results Login(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        GenericCommand commandObj = new GenericCommand("server.handlers.loginHandler", "login",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results Register(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        GenericCommand commandObj = new GenericCommand("server.handlers.registerHandler", "register",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results CreateGame(authTokenModel authToken) throws Exception {
        Object[] paramValues = {authToken};
        String[] paramTypes = {"java.com.bignerdranch.android.models.authTokenModel"};
        GenericCommand commandObj = new GenericCommand("server.handlers.createGameHandler", "createGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results JoinGame(JoinGameRequest request) throws Exception {
        JoinGameRequest[] paramValues = {request};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.JoinGameRequest"};
        GenericCommand command = new GenericCommand("server.handlers.JoinGameHandler", "joinGame", paramTypes, paramValues);
        String commandString = Serializer.getInstance().serializeObject(command);
        client.send(commandString);
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }
}
