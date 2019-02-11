package com.janus.Communication;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.bignerdranch.android.shared.resultobjects.LoginData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;

public class TtRClient extends WebSocketClient{
    private static Results messageResult;

    public TtRClient(URI serverUri) {
        super(serverUri);
    }
    
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connection Open!");
    }

    @Override
    public void onMessage(String message) {
        Results result = Serializer.getInstance().deserializeResults(message);
        if (result.isSuccess()) {
            System.out.println("Received Message: " + result.getData());
            switch (result.getType()) {
                case "Login": {
                    LoginData data = Serializer.getInstance().deserializeLoginData(result.getData().toString());
                    result.setData(data);
                    break;
                }
                case "Register": {
                    LoginData data = Serializer.getInstance().deserializeLoginData(result.getData().toString());
                    result.setData(data);
                    break;
                }
            }
            messageResult = result;
        }
        else {
            System.out.println("Received Error: " + result.getData());
            messageResult = result;
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection Closed!");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println(ex.getMessage());
    }
    
    public Results getResults() {
    	return messageResult;
    }
}
