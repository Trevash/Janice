package main;

import generalCommand.GeneralCommand;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;

import com.google.gson.Gson;
import wsClient.EmptyClient;
import stringProcessor.StringProcessor;

public class Main {

	public static void main(String[] args) {

	GeneralCommand c = new GeneralCommand("stringProcessor.StringProcessor","trim",
                new String[]{ "java.lang.String" },new Object[]{"  byu d  "});

	GeneralCommand c2 = new GeneralCommand("stringProcessor.dummyTest","add",
            new String[]{ "java.lang.Integer","java.lang.Integer"  },new Object[]{new Integer(1), new Integer(2)});
	Object o = c.execute();
	Gson gson = new Gson();
	String json = gson.toJson(c);
	
	WebSocketClient client = null;
	try {
		client = new EmptyClient(new URI("ws://localhost:8887/main"));
		client.connectBlocking();
		//System.out.println(client.getReadyState());
		//System.out.println(client.getURI());
		client.send(json);
		//client.close();

	} catch (URISyntaxException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



	}

}
