package com.janus.Communication;

import java.net.URI;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.userModel;
import com.bignerdranch.android.shared.resultobjects.AuthData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;
import com.janus.ClientModel;
import com.bignerdranch.android.shared.resultobjects.GameListData;

public class TtRClient extends WebSocketClient{
    private static Results messageResult;

    public TtRClient(URI serverUri) {
        super(serverUri);
    }

    private ClientModel client = ClientModel.getInstance();

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connection Open!");
    }

    @Override
    public void onMessage(String message) {
        Results result = Serializer.getInstance().deserializeResults(message);
        if (result.isSuccess()) {
            System.out.println("Received Message: ");
            switch (result.getType()) {
                case "Login": {
                    client.setUser((userModel) result.getData(userModel.class));
                }
                case "Register": {
                    client.setUser((userModel) result.getData(userModel.class));
                }
                case "GameList": {
                    client.setServerGameList((GameListData) result.getData(GameListData.class));
                }
                case "Create": {
                    client.setGame((gameIDModel) result.getData(gameIDModel.class));
                }
            }
            messageResult = result;
        }
        else {
            System.out.println("Received Error: " + result.getData(String.class));
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
