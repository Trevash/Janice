package com.janus.Communication;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

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

    public void connectClient() {
    	try {
			client.connectBlocking();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Results Login(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "login",paramTypes, paramValues);
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
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "register",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results CreateGame(String newGameName, authTokenModel auth) throws Exception {
        Object[] paramValues = {new CreateGameRequest(newGameName, auth)};
        String[] paramTypes = {"shared.requestObjects.CreateGameRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "createGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results StartGame(gameModel game, authTokenModel auth) throws Exception {
        Object[] paramValues = {new StartGameRequest(game, auth)};
        String[] paramTypes = {"shared.requestObjects.StartGameRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "startGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }
}
