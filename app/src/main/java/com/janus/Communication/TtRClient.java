package com.janus.Communication;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;

public class TtRClient extends WebSocketClient{
    private static TtRClient client;
    private static Results messageResult;
    
    private TtRClient(URI serverUri) {
        super(serverUri);
    }
    
    static TtRClient getInstance() {
    	if (client == null){
            //TODO: Generalize from localhost
            try {
                client = new TtRClient(new URI("ws://10.24.217.239:8087"));
            } catch (URISyntaxException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return client;
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
