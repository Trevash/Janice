package server.serverClasses;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.resultobjects.ChatboxData;
import com.bignerdranch.android.shared.resultobjects.ClaimRouteData;
import com.bignerdranch.android.shared.resultobjects.DrawTrainCardData;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.bignerdranch.android.shared.resultobjects.GameStatusData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.resultobjects.ReturnDestinationCardData;
import com.bignerdranch.android.shared.Serializer;
import com.bignerdranch.android.shared.models.*;
import com.sun.net.httpserver.HttpServer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.bignerdranch.android.shared.Constants.Commands.*;

public class ServerCommunicator extends WebSocketServer {
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;
    private Map<String, WebSocket> usernameWSMap = new HashMap<>();

    private ServerCommunicator(InetSocketAddress address) {
        super(address);
    }

    public static void main(String[] args) {
        //String host = "10.37.93.67";
        String host = Constants.IP_ADDRESS;
        int port = Constants.PORT;

        WebSocketServer server = new ServerCommunicator(new InetSocketAddress(host, port));
        server.run();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("Server open!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Server closed!");
        Iterator<String> iterator = usernameWSMap.keySet().iterator();
        while (iterator.hasNext()) {
            String username = iterator.next();
            if (usernameWSMap.get(username).equals(conn)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        GenericCommand command = Serializer.getInstance().deserializeCommand(message);
        Results result = command.execute();
        String resultGson = Serializer.getInstance().serializeObject(result);

        switch (result.getType()) {
            case LOGIN:
                broadcastOne(resultGson, conn);
                if (result.isSuccess()) {
                    updateAllUserGameList();
                    userModel user = (userModel) result.getData(userModel.class);
                    usernameWSMap.put(user.getUserName().getValue(), conn);
                }
                break;
            case REGISTER:
                broadcastOne(resultGson, conn);
                if (result.isSuccess()) {
                    updateAllUserGameList();
                    userModel user = (userModel) result.getData(userModel.class);
                    usernameWSMap.put(user.getUserName().getValue(), conn);
                }
                break;
            case CREATE:
                broadcastOne(resultGson, conn);
                updateAllUserGameList();
                break;
            case JOIN:
                broadcast(resultGson);
                updateAllUserGameList();
                break;
            case START:
                gameModel gameStart = (gameModel) result.getData(gameModel.class);
                broadcastGame(resultGson, gameStart);
                broadcast(resultGson);
                updateAllUserGameList();
                break;
            case UPDATE_CHAT:
                //TODO: Caleb change this later
                ChatboxData chatboxData = (ChatboxData) result.getData(ChatboxData.class);
                gameIDModel gameID = chatboxData.getGameID();
                gameModel gameChat = serverModel.getInstance().getGameByID(gameID);
                broadcastGame(resultGson, gameChat);
                break;
            case CLAIM_ROUTE:
                // TODO claiming a route needs restrictions to be enforced.
                ClaimRouteData claimRouteData = (ClaimRouteData) result.getData(ClaimRouteData.class);
                broadcastGame(resultGson, serverModel.getInstance().getGameByID(claimRouteData.getGameID()));
                updateGameStatus(claimRouteData.getGameID(), claimRouteData.getUsername(),
                        "Route from " + claimRouteData.getCurRoute().getCity1().getName() +
                                " to " + claimRouteData.getCurRoute().getCity2().getName() +
                                " claimed by " + claimRouteData.getUsername().getValue());
                broadcastGameStats(serverModel.getInstance().getGameByID(claimRouteData.getGameID()));
                break;
            case DRAW_DESTINATION_CARDS:
                // TODO HOW IS this returning? TtRClient does not have an equivalent for this, but it seems to be working
                broadcastOne(resultGson, conn);
                break;
            case RETURN_DESTINATION_CARDS:
                //gameModel game = (gameModel) result.getData(gameModel.class);
                //broadcastGame(resultGson, game);
                ReturnDestinationCardData returnDestdata = (ReturnDestinationCardData)
                        result.getData(ReturnDestinationCardData.class);

                // commented out: the code at the end means that this is already getting broadcasted
                broadcastOne(resultGson, conn);

                updateGameStatus(returnDestdata.getGameID(), returnDestdata.getUsername(), "drew " +
                        Integer.toString(returnDestdata.getSelectedCards().size()) + " destination cards");
                broadcastGameStats(serverModel.getInstance().getGameByID(returnDestdata.getGameID()));

                break;
            case DRAW_FIRST_TRAIN_CARD:
                broadcastOne(resultGson, conn);
                // TODO probably would want a way to update everyone's GUI.
                break;
            case DRAW_SECOND_TRAIN_CARD:
                DrawTrainCardData data = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                // TODO this should probably be altered so that other players don't see the drawing player's hand
                broadcastGame(resultGson, serverModel.getInstance().getGameByID(data.getGameID()));
                updateGameStatus(data.getGameID(), data.getUsername(), data.getUsername().getValue() + " drew train cards");
                broadcastGameStats(serverModel.getInstance().getGameByID(data.getGameID()));
                break;
            case "ERROR":
                broadcastOne(resultGson, conn);
                break;
            default:
                System.out.println("Invalid type passed to onMessage from Result: " + result.getType());
        }
        // TODO what is this doing? It does make for a default-case. Removing as it is causing a bug with drawing train cards, and everything appears to already be broadcasting
        //List<WebSocket> temp = new ArrayList<>();
        //temp.add(conn);
        //broadcast(resultGson, temp);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println(ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
    }

    private void updateAllUserGameList() {
        Results gameListResult = new Results("GameList", true,
                new GameListData(serverModel.getInstance().getGames()));
        String gameListGson = Serializer.getInstance().serializeObject(gameListResult);
        broadcast(gameListGson);
    }

    public void broadcastOne(String resultGson, WebSocket conn) {
        List<WebSocket> temp = new ArrayList<>();
        temp.add(conn);
        broadcast(resultGson, temp);
    }

    public void broadcastGame(String resultGson, gameModel game) {
        List<WebSocket> temp = new ArrayList<>();

        for (playerModel player : game.getPlayers()) {
            String username = player.getUserName().getValue();
            if (usernameWSMap.containsKey(username)) {
                temp.add(usernameWSMap.get(username));
            }
        }

        broadcast(resultGson, temp);
    }

    // method that broadcasts all public information to everyone in the game
    public void updateGameStatus(gameIDModel gameID, usernameModel username, String historyUpdate) {
        gameModel curGame = serverModel.getInstance().getGameByID(gameID);
        //curGame.incrementTurnCounter();
        // states increment the turn counter automatically - and not everything that causes an update
        // will necessarily require incrementing the turn counter. ex: drawing first train card
        curGame.updateGameHistory(new chatMessageModel(username, historyUpdate));

        GameStatusData data = new GameStatusData(curGame.getTurnCounter(), curGame.getGameHistory(),
                curGame.getNumTrainCards(), curGame.getFaceUpCards(), curGame.getTrainCardDiscards(),
                curGame.getNumDestinationCards());
        Results result = new Results(UPDATE_GAME_STATUS, true, data);
        this.broadcastGame(Serializer.getInstance().serializeObject(result), curGame);
    }

    public void broadcastGameStats(gameModel game) {
        List<WebSocket> temp = new ArrayList<>();
        // TODO Why 0? that exclusively broadcasts to the host, and I can't think of any info that only the host would need to know
        List<int[]> gameStats = game.getStats(game.getPlayers().get(0).getUserName());
        gameStats.remove(0);
        Results r = new Results("stats", true, gameStats);
        String resultGson = Serializer.getInstance().serializeObject(r);

        for (playerModel player : game.getPlayers()) {
            String username = player.getUserName().getValue();
            if (usernameWSMap.containsKey(username)) {
                temp.add(usernameWSMap.get(username));
            }
        }

        broadcast(resultGson, temp);
    }

}
