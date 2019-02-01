package main;

import generalCommand.GeneralCommand;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;

import com.google.gson.Gson;
import wsClient.EmptyClient;

public class Main {

	public static void main(String[] args) {

	GeneralCommand c = new GeneralCommand("StringProcessor.StringProcessor","trim",
                new String[]{ "String" },new Object[]{"  byu d  "});
	
	Gson gson = new Gson();
	String json = gson.toJson(c);
	WebSocketClient client = null;
	try {
		client = new EmptyClient(new URI("ws://localhost:8887/main"));
		client.connectBlocking();
		System.out.println(client.getReadyState());
		System.out.println(client.getURI());
		client.send(json);
		//client.close();

	} catch (URISyntaxException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	
	}

}
