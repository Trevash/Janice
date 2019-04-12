package com.janus.Communication;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.bignerdranch.android.shared.gameStates.AbstractClientGameState;
import com.bignerdranch.android.shared.gameStates.ClientGameOverState;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.userModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.resultobjects.ChatboxData;
import com.bignerdranch.android.shared.resultobjects.ClaimRouteData;
import com.bignerdranch.android.shared.resultobjects.DrawTrainCardData;
import com.bignerdranch.android.shared.resultobjects.GameStatusData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;
import com.bignerdranch.android.shared.resultobjects.ReturnDestinationCardData;
import com.janus.ClientFacade;
import com.bignerdranch.android.shared.resultobjects.GameListData;

import static com.bignerdranch.android.shared.Constants.Commands.*;

public class TtRClient extends WebSocketClient{

    public TtRClient(URI serverUri) {
        super(serverUri);
    }

    private ClientFacade facade = ClientFacade.getInstance();
    private ServerProxy proxy = ServerProxy.getInstance();

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
                // case DRAW_DESTINATION_CARDS - doesn't do anything
                case JOIN: {
                    facade.setGame((gameModel) result.getData(gameModel.class));
                    break;
                }
                case START: {
                    facade.setGame((gameModel) result.getData(gameModel.class));
                    break;
                }
                case RETURN_DESTINATION_CARDS: {
                    // get ReturnDestinationCardData, add to the client's hand
                    ReturnDestinationCardData data = (ReturnDestinationCardData) result.getData(ReturnDestinationCardData.class);
                    facade.addDestinationCardsToHand(data.getSelectedCards());
                    break;
                }
                case UPDATE_CHAT: {
                    ChatboxData chatboxData = (ChatboxData) result.getData(ChatboxData.class);
                    facade.setChatbox(chatboxData.getChatbox());
                    facade.update();
                    break;
                }
                case UPDATE_GAME_STATUS: {
                    GameStatusData data = (GameStatusData) result.getData(GameStatusData.class);
                    facade.setHistory(data.getGameHistory());
                    facade.setTurnCounter(data.getTurnCounter());
                    facade.setNumTrainCards(data.getNumTrainCards());
                    facade.setNumDestinationCards(data.getNumDestinationCards());
                    facade.setTrainCardDiscards(data.getTrainCardDiscard());
                    facade.setFaceUpTrainCards(data.getFaceUpTrainCards());
                    facade.update();
                    break;
                }
                case DRAW_FIRST_TRAIN_CARD: {
                    DrawTrainCardData data = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                    facade.getGame().setPlayersHand(data.getHand(), data.getUsername());
                    facade.getGame().setFaceUpCards(data.getFaceUpCards());
                    facade.getGame().setNumTrainCards(data.getNumTrainCards());
                    facade.notifyTrainCardDrawn(); // this allows the state to update properly
                    facade.update();
                    break;
                }
                case DRAW_SECOND_TRAIN_CARD: {
                    DrawTrainCardData data = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                    facade.getGame().setPlayersHand(data.getHand(), data.getUsername());
                    facade.getGame().setFaceUpCards(data.getFaceUpCards());
                    facade.getGame().setNumTrainCards(data.getNumTrainCards());
                    facade.notifyTrainCardDrawn();
                    facade.update();
                    break;
                }
                case STATS: {
                	List<List<Double>> s = (ArrayList) result.getData(ArrayList.class);
                	List<int[]> convertedStats = new ArrayList<>();
                	for (int i = 0; i < s.size(); i++) {
                	    int[] arr = new int[s.get(i).size()];
                	    for (int j = 0; j < s.get(i).size(); j++) {
                	        arr[j] = s.get(i).get(j).intValue();
                        }
                        convertedStats.add(arr);
                    }
                	usernameModel username = facade.getPlayer().getUserName();
                	facade.getGame().setStats(convertedStats, username);
                    facade.update();
                    break;
                }
                case LAST_ROUND: {
                    facade.lastRound();
                    break;
                }
                case END_GAME: {
                    IGameState currentState = facade.getGame().getState();
                    facade.getGame().setState(new ClientGameOverState( (AbstractClientGameState) currentState));
//                    List<Double> temp = (ArrayList) result.getData(ArrayList.class);
//                    List<Integer> convertedFinalStats = new ArrayList<>();
//                    for (int i = 0; i < temp.size(); i++) {
//                        convertedFinalStats.add(temp.get(i).intValue());
//                    }
//                    facade.getGame().setStats(convertedFinalStats, facade.getUser().getUserName());
                    List<Integer> finalStats = (List<Integer>) result.getData(List.class);
                    facade.getGame().setFinalStats(finalStats);
                    facade.endGame();
                    break;
                }
            }
            proxy.checkMessageResult(result);
        }
        else {
            System.out.println("Received Error: " + result.getData(String.class));
            proxy.checkMessageResult(result);
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
}
