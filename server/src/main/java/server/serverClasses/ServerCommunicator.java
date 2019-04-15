package server.serverClasses;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.interfaces.IGameRequest;
import com.bignerdranch.android.shared.resultobjects.ChatboxData;
import com.bignerdranch.android.shared.resultobjects.ClaimRouteData;
import com.bignerdranch.android.shared.resultobjects.DrawTrainCardData;
import com.bignerdranch.android.shared.resultobjects.FinalStatsData;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.bignerdranch.android.shared.resultobjects.GameStatusData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.resultobjects.ReturnDestinationCardData;
import com.bignerdranch.android.shared.Serializer;
import com.bignerdranch.android.shared.models.*;

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
    private Map<String, WebSocket> usernameWSMap = new HashMap<>();

    private ServerCommunicator(InetSocketAddress address) {
        super(address);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        String host = Constants.IP_ADDRESS;
        int port = Constants.PORT;
        // set the deltas for the plugin
        if (args.length > 1) {
            serverModel.getInstance().setDeltas(Integer.parseInt(args[1]));
        } else {
            serverModel.getInstance().setDeltas(5);
        }

        // set the plugIns for the serverModel
        if (args.length > 0) {
            serverModel.getInstance().setPlugIn(args[0]);
        } else {
            serverModel.getInstance().setPlugIn("Dummy");
        }


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

        //Handles commands as saved in database
        if (IGameRequest.class.isAssignableFrom(command.getRequest().getClass())) {
            this.sendCommandToDatabase(command);
        }

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
                ClaimRouteData claimRouteData = (ClaimRouteData) result.getData(ClaimRouteData.class);
                gameModel curGame = serverModel.getInstance().getGameByID(claimRouteData.getGameID());
                broadcastGame(resultGson, curGame);
                updateGameStatus(claimRouteData.getGameID(), claimRouteData.getUsername(),
                        "Route from " + claimRouteData.getCurRoute().getCity1().getName() +
                                " to " + claimRouteData.getCurRoute().getCity2().getName() +
                                " claimed by " + claimRouteData.getUsername().getValue());
                broadcastGameStats(curGame);

                //Check if last turn
                curGame.checkIfLastTurn();
                if (curGame.isLastTurn()) {
                    broadcastEndGame(curGame);
                }
                //Check if last round
                else if (curGame.isLastRound()) {
                    broadcastLastRound(curGame);
                }
                break;
            case DRAW_DESTINATION_CARDS:
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

                //Check if last turn
                gameModel game = serverModel.getInstance().getGameByID(returnDestdata.getGameID());
                game.checkIfLastTurn();
                if (game.isLastTurn()) {
                    broadcastEndGame(game);
                }
                break;
            case DRAW_FIRST_TRAIN_CARD:
                DrawTrainCardData fData = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                gameModel currentGame = serverModel.getInstance().getGameByID(fData.getGameID());
                currentGame.updateGameHistory(new chatMessageModel(fData.getUsername(), fData.getUsername().getValue() + " drew a " + fData.getHand().get(fData.getHand().size() - 1).getColor().name() + "card"));
                broadcastOne(resultGson, conn);
                break;
            case DRAW_SECOND_TRAIN_CARD:
                DrawTrainCardData data = (DrawTrainCardData) result.getData(DrawTrainCardData.class);
                broadcastGame(resultGson, serverModel.getInstance().getGameByID(data.getGameID()));
                updateGameStatus(data.getGameID(), data.getUsername(), data.getUsername().getValue() + " drew a " + data.getHand().get(data.getHand().size() - 1).getColor().name() + "card");
                broadcastGameStats(serverModel.getInstance().getGameByID(data.getGameID()));

                //Check if last turn
                gameModel Game = serverModel.getInstance().getGameByID(data.getGameID());
                Game.checkIfLastTurn();
                if (Game.isLastTurn()) {
                    broadcastEndGame(Game);
                }
                break;
            case "ERROR":
                broadcastOne(resultGson, conn);
                break;
            default:
                System.out.println("Invalid type passed to onMessage from Result: " + result.getType());
        }
    }

    private void sendCommandToDatabase(GenericCommand command) {
        gameModel curGame = serverModel.getInstance().getGameByID(((IGameRequest) command.getRequest()).getGameID());
        curGame.addCommand(command);

        if (curGame.numCommands() > serverModel.getInstance().getDeltas()) {
            //TODO: Send game blob to database
            curGame.clearCommands();
            //TODO: Clear this games' list of commands in the database
        } else {
            //TODO: Send commands linked list blob to database, save by gameID
        }
    }

    private void broadcastEndGame(gameModel curGame) {
        Results results = new Results("EndGame", true, new FinalStatsData(curGame.getFinalStats()));
        broadcastGame(Serializer.getInstance().serializeObject(results), curGame);
    }

    private void broadcastLastRound(gameModel curGame) {
        Results results = new Results("LastRound", true, null);
        broadcastGame(Serializer.getInstance().serializeObject(results), curGame);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println(ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        //TODO: Retrieve all game blobs from database
        //TODO: Retrieve all commands from database
        //TODO: Execute all commands in order for all games
    }

    private void updateAllUserGameList() {
        Results gameListResult = new Results("GameList", true,
                new GameListData(serverModel.getInstance().getGames()));
        String gameListGson = Serializer.getInstance().serializeObject(gameListResult);
        broadcast(gameListGson);
    }

    private void broadcastOne(String resultGson, WebSocket conn) {
        List<WebSocket> temp = new ArrayList<>();
        temp.add(conn);
        broadcast(resultGson, temp);
    }

    private void broadcastGame(String resultGson, gameModel game) {
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
    private void updateGameStatus(gameIDModel gameID, usernameModel username, String historyUpdate) {
        gameModel curGame = serverModel.getInstance().getGameByID(gameID);
        //curGame.incrementTurnCounter();
        // states increment the turn counter automatically - and not everything that causes an update
        // will necessarily require incrementing the turn counter. ex: drawing first train card
        curGame.updateGameHistory(new chatMessageModel(username, historyUpdate));

        // TODO add in a list containing the number of dest. cards each player has
        GameStatusData data = new GameStatusData(curGame.getTurnCounter(), curGame.getGameHistory(),
                curGame.getNumTrainCards(), curGame.getFaceUpCards(), curGame.getTrainCardDiscards(),
                curGame.getNumDestinationCards());
        Results result = new Results(UPDATE_GAME_STATUS, true, data);
        this.broadcastGame(Serializer.getInstance().serializeObject(result), curGame);
    }

    // What is this method supposed to be doing? Please add explanatory comments
    // as it is not apparent what this method is supposed to be sending
    private void broadcastGameStats(gameModel game) {
        List<WebSocket> temp = new ArrayList<>();
        List<int[]> gameStats = game.getStats(game.getPlayers().get(0).getUserName());
        gameStats.remove(0);
        Results r = new Results("Stats", true, gameStats);
        String resultGson = Serializer.getInstance().serializeObject(r);

        this.broadcastGame(resultGson, game);
    }

}
