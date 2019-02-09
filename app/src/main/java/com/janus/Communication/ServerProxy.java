package com.janus.Communication;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.Results;
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
        client.getInstance();
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
        GenericCommand commandObj = new GenericCommand("server.handlers.loginHandler", "login",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        while (messageResult == null) {
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
            Thread.sleep(100);
        }
        return messageResult;
    }
}
