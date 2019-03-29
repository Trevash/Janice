package com.janus.Communication;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.bignerdranch.android.shared.models.userModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.resultobjects.ChatboxData;
import com.bignerdranch.android.shared.resultobjects.ClaimRouteData;
import com.bignerdranch.android.shared.resultobjects.DrawTrainCardData;
import com.bignerdranch.android.shared.resultobjects.GameStatusData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;
import com.janus.ClientFacade;
import com.bignerdranch.android.shared.resultobjects.GameListData;

public class TtRClient extends WebSocketClient{
    private static Results messageResult;

    public TtRClient(URI serverUri) {
        super(serverUri);
    }

    private ClientFacade facade = ClientFacade.getInstance();

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connection Open!");
    }

    @Override
    public void onMessage(String message) {
        Results result = Serializer.getInstance().deserializeResults(message);
        if (result.isSuccess()) {
            System.out.println("Received Message: " + result.getJSONdata());
            switch (result.getType()) {
                case "Login": {
                    facade.setUser((userModel) result.getData(userModel.class));
                    break;
                }
                case "Register": {
                    facade.setUser((userModel) result.getData(userModel.class));
                    break;
                }
                case "GameList": {
                    facade.setServerGameList((GameListData) result.getData(GameListData.class));
                    break;
                }
                case "Create": {
                    facade.setGame((gameModel) result.getData(gameModel.class));
                    break;
                }
                case "ClaimRoute":{
                    ClaimRouteData data = (ClaimRouteData) result.getData(ClaimRouteData.class);
                    facade.getGame().setRoutes(data.getRoutes());
                    facade.getGame().addToTrainDiscards(data.getDiscards());
                    facade.getGame().getPlayerModelFromID(data.getPlayerID()).setTrainCardHand(data.getHand());
                    facade.getGame().getPlayerModelFromID(data.getPlayerID()).setPoints(data.getPoints());
                    facade.getGame().getPlayerModelFromID(data.getPlayerID()).decrementTrainCars(data.getCurRoute().getLength());
                    facade.update();
                    break;
                }
                case "Join": {
                    facade.setGame((gameModel) result.getData(gameModel.class));
                    break;
                }
                case "Start": {
                    facade.setGame((gameModel) result.getData(gameModel.class));
                    break;
                }
                case "ReturnDestinationCards": {
                	//facade.setGame((gameModel) result.getData(gameModel.class));
                    // TODO what, if anything, should be in here
                    break;
                }
                case "UpdateChat": {
                    ChatboxData chatboxData = (ChatboxData) result.getData(ChatboxData.class);
                    facade.setChatbox(chatboxData.getChatbox());
                    break;
                }
                case "UpdateGameStatus": {
                    GameStatusData data = (GameStatusData) result.getData(GameStatusData.class);
                    facade.setHistory(data.getGameHistory());
                    facade.setTurnCounter(data.getTurnCounter());
                    facade.setNumTrainCards(data.getNumTrainCards());
                    break;
                }
                case "DrawFirstTrainCard": {
                    DrawTrainCardData data = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                    facade.getGame().addTrainCardToPlayersHand(data.getReturnCard(), data.getUsername());
                    break;
                }
                case "DrawSecondTrainCard": {
                    DrawTrainCardData data = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                    facade.getGame().addTrainCardToPlayersHand(data.getReturnCard(), data.getUsername());
                    break;
                }
            }
            //TODO: Generic update UI here?
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

    public void setMessageResultToNull() {
        messageResult = null;
    }
}
