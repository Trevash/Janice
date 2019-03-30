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

import static com.bignerdranch.android.shared.Constants.Commands.*;

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
                case LOGIN: {
                    facade.setUser((userModel) result.getData(userModel.class));
                    break;
                }
                case REGISTER: {
                    facade.setUser((userModel) result.getData(userModel.class));
                    break;
                }
                case GAME_LIST: {
                    facade.setServerGameList((GameListData) result.getData(GameListData.class));
                    break;
                }
                case CREATE: {
                    facade.setGame((gameModel) result.getData(gameModel.class));
                    break;
                }
                case CLAIM_ROUTE:{
                    ClaimRouteData data = (ClaimRouteData) result.getData(ClaimRouteData.class);
                    facade.getGame().setRoutes(data.getRoutes());
                    facade.getGame().setTrainCardDiscards(data.getDiscards());
                    facade.getGame().getPlayerModelFromID(data.getPlayerID()).setTrainCardHand(data.getHand());
                    facade.getGame().getPlayerModelFromID(data.getPlayerID()).setPoints(data.getPoints());
                    facade.getGame().getPlayerModelFromID(data.getPlayerID()).setTrainCars(data.getNumLocomotives());
                    facade.update();
                    break;
                }
                case JOIN: {
                    facade.setGame((gameModel) result.getData(gameModel.class));
                    break;
                }
                case START: {
                    facade.setGame((gameModel) result.getData(gameModel.class));
                    break;
                }
                case RETURN_DESTINATION_CARDS: {
                	//facade.setGame((gameModel) result.getData(gameModel.class));
                    // TODO what, if anything, should be in here
                    break;
                }
                case UPDATE_CHAT: {
                    ChatboxData chatboxData = (ChatboxData) result.getData(ChatboxData.class);
                    facade.setChatbox(chatboxData.getChatbox());
                    facade.update();
                    break;
                }
                case UPDATE_GAME_STATUS: {
                    // TODO somehow it is failing to deserialize the gameStatusData - GSON gets IllegalStateException
                    GameStatusData data = (GameStatusData) result.getData(GameStatusData.class);
                    facade.setHistory(data.getGameHistory());
                    facade.setTurnCounter(data.getTurnCounter());
                    facade.setNumTrainCards(data.getNumTrainCards());
                    // Is there a way to update the number of destination cards?
                    facade.update();
                    break;
                }
                case DRAW_FIRST_TRAIN_CARD: {
                    DrawTrainCardData data = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                    facade.getGame().setPlayersHand(data.getHand(), data.getUsername());
                    facade.getGame().setFaceUpCards(data.getFaceUpCards());
                    facade.getGame().setNumTrainCards(data.getNumTrainCards());
                    facade.update();
                    break;
                }
                case DRAW_SECOND_TRAIN_CARD: {
                    DrawTrainCardData data = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                    facade.getGame().setPlayersHand(data.getHand(), data.getUsername());
                    facade.getGame().setFaceUpCards(data.getFaceUpCards());
                    facade.getGame().setNumTrainCards(data.getNumTrainCards());
                    break;
                }
            }
            //TODO: Generic UI update here maybe? caused problems earlier
            messageResult = result; // TODO do we want every messageResult getting stored here? Even if they aren't getting used here?
            // could potentially overwrite stuff.
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
