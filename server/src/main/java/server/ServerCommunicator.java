package server;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.Results;
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
        String host = "localhost";
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
        Results result = command.execute();
        switch(result.getType()){
        	case "login": System.out.println("login"); 
        	case "register": System.out.println("register");  
        	case "create": System.out.println("create");  
        	case "join": System.out.println("join");  
        	case "start": System.out.println("start");  
        	case "ERROR": System.out.println("ERROR");
        }
        String resultGson = Serializer.getInstance().serializeObject(result);
        List<WebSocket> temp = new ArrayList<WebSocket>();
        temp.add(conn);
        broadcast(resultGson, temp);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println(ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
    }
}
