package com.janus.Communication;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.Results;
import com.bignerdranch.android.shared.Serializer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ServerProxy extends WebSocketClient {
    private static ServerProxy scp;

    private ServerProxy(URI serverUri) {
        super(serverUri);
    }

    static ServerProxy getInstance() {
        if (scp == null){
            //TODO: Generalize from localhost
            try {
                scp = new ServerProxy(new URI("ws://localhost::8087"));
            } catch (URISyntaxException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return scp;
    }

    private String className = "server.handlers";

    public Results Login(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        GenericCommand commandObj = new GenericCommand("server.handlers.loginHandler", "login",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        return Serializer.getInstance().deserializeResults(ClientCommunicator.getInstance().sendResponse("command", commandObjStr));
    }

    public Results Register(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        GenericCommand commandObj = new GenericCommand("server.handlers.registerHandler", "register", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        return Serializer.getInstance().deserializeResults(ClientCommunicator.getInstance().sendResponse("command", commandObjStr));
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
