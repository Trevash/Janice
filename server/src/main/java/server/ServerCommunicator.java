package server;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;
import com.bignerdranch.android.shared.models.*;
import com.sun.net.httpserver.HttpServer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerCommunicator extends WebSocketServer {
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;
    private Map<String, WebSocket> usernameWSMap = new HashMap();

    private ServerCommunicator(InetSocketAddress address){
        super(address);
    }

    public static void main(String[] args) {
        String host = "10.37.93.67";
        int port = 8087;

        WebSocketServer server = new ServerCommunicator(new InetSocketAddress(host, port));
        server.run();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        //System.out.println("Server open!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Server closed!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        GenericCommand command = Serializer.getInstance().deserializeCommand(message);
        if (command.getMethod().equals("createGame")) {
            CreateGameRequest[] request = {Serializer.getInstance().deserializeCreateCommand(command.getParamValues()[0].toString())};
            command.setParamValues(request);
        }
        Results result = command.execute();
        String resultGson = Serializer.getInstance().serializeObject(result);
        
        switch(result.getType()){
        	case "Login":
        	    broadcastOne(resultGson, conn);
        	    if(result.isSuccess()){updateAllUserGameList();}
        	    break;
        	case "Register":
        	    broadcastOne(resultGson, conn);
                if(result.isSuccess()){updateAllUserGameList();}
        	    break;
        	case "Create":
        	    broadcastOne(resultGson, conn);
        	    updateAllUserGameList();
        	    break;
        	case "Join":
        	    broadcast(resultGson);
                updateAllUserGameList();
        	    break;
        	case "Start":
        	    broadcast(resultGson);
                updateAllUserGameList();
        	    break;
            default : System.out.println("Invalid type passed to onMessage from Result!");
        }
        List<WebSocket> temp = new ArrayList<>();
        temp.add(conn);
        broadcast(resultGson, temp);
    }

    private void updateAllUserGameList() {
        Results gameListResult = new Results("GameList", true, new GameListData());
        String gameListGson = Serializer.getInstance().serializeObject(gameListResult);
        broadcast(gameListGson);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println(ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
    }
    
    public void broadcastOne(String resultGson, WebSocket conn) {
    	List<WebSocket> temp = new ArrayList<>();
        temp.add(conn);
        broadcast(resultGson, temp);
    }
    
    //to be filled out later
    public void broadcastGame(String resultGson, gameModel game) {
    	
    }
}
