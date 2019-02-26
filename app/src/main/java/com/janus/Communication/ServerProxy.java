package com.janus.Communication;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;
import com.bignerdranch.android.shared.requestObjects.RegisterRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ServerProxy implements IServer {

    public interface CurrentState {

    }

	private static ServerProxy scp;
    private static TtRClient client;

    private Results messageResult;
    private CurrentState state;

    private ServerProxy() {}

    public static ServerProxy getInstance() {
        if (scp == null){
            scp = new ServerProxy();
        }
        return scp;
    }

    private String className = "server.handlers";

    public void connectClient() throws InterruptedException, URISyntaxException {
        client = new TtRClient(new URI("ws://192.168.252.225:8087"));
        client.connectBlocking();
    }

    public void disconnectClient() throws InterruptedException, URISyntaxException {
        client.closeBlocking();
    }

    public Results login(LoginRequest request) throws Exception {
        Object[] paramValues = {request};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.LoginRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "login",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results register(RegisterRequest request) throws Exception {
        Object[] paramValues = {request};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.RegisterRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "register",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results createGame(CreateGameRequest request) throws Exception {
        Object[] paramValues = {request};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.CreateGameRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "createGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results startGame(StartGameRequest request) throws Exception {
        Object[] paramValues = {request};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.StartGameRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "startGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

    public Results joinGame(JoinGameRequest request) throws Exception {
        Object[] paramValues = {request};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.JoinGameRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "joinGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

	@Override
	public Results updateChatbox(UpdateChatboxRequest request) throws Exception {
        Object[] paramValues = {request};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "updateChatbox", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
	}
}
