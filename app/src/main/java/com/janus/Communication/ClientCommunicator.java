package com.janus.Communication;

import com.bignerdranch.android.shared.Serializer;
import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.chatMessageModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;
import com.bignerdranch.android.shared.requestObjects.RegisterRequest;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;

import org.java_websocket.client.WebSocketClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by coy on 1/22/2019.
 */

public class ClientCommunicator {
    private static ClientCommunicator cc = new ClientCommunicator();
    static ClientCommunicator getInstance() {
        if (cc == null){
            cc = new ClientCommunicator();
        }
        return cc;
    }

    public static void main(String args[]){
        try {
            System.out.println("Starting ClientCommunicator main function");
            ServerProxy server = ServerProxy.getInstance();
            System.out.println("Beginning attempt to connect to Server");
            server.connectClient();
//            System.out.println("REGISTER FAIL");
//            server.register(new RegisterRequest("illegal Username", "somePassword"));
//            server.register(new RegisterRequest("legalUsername", "illegal Password"));
//            System.out.println("");
//            System.out.println("LOGIN FAIL");
//            server.login(new LoginRequest("illegal Username", "somePassword"));
//            server.login(new LoginRequest("legalUsername", "illegal Password"));
//
//            System.out.println("");
            System.out.println("SUCCESSFUL REGISTER");
            server.register(new RegisterRequest("legalUsername", "legalPassword"));
//            authTokenModel curAuthToken = (authTokenModel) registerResult.getData(authTokenModel.class);
////            System.out.println("");
//            System.out.println("ANOTHER FAILED LOGIN");
//            server.login(new LoginRequest("legal Username", "legalPassword"));
//            server.login(new LoginRequest("legalUsername", "legal Password"));
//
//            System.out.println("");
//            System.out.println("SUCCESSFUL LOGIN");
//            server.login(new LoginRequest("legalUsername", "legalPassword"));

            //Step 1: Create Game
            server.createGame(new CreateGameRequest(ClientFacade.getInstance().getUser().getAuthToken()));

            //Step 2: Send messages
            gameIDModel gameID = ClientFacade.getInstance().getServerGameList().get(0).getGameID();
            UpdateChatboxRequest request = new UpdateChatboxRequest(
                    gameID,
                    ClientFacade.getInstance().getUser().getAuthToken(),
                    new chatMessageModel(
                            new usernameModel("legalUsername"),
                            "We are testing the chat!"
                    )
            );
            server.updateChatbox(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String serverHost = Constants.IP_ADDRESS;
    private int serverPort = Constants.PORT;

    // The Register method calls the server's /user/register handler
    String sendResponse(String context, String data) { //throws Exception {

        String registerUrl = "http://" + serverHost + ":" + serverPort + "/" + context;

        try {
            HttpURLConnection http = openConnectionToServer(registerUrl);

            // Get the output stream containing the HTTP request body
            OutputStream reqBody = http.getOutputStream();

            writeString(data, reqBody);
            // Close the request body output stream, indicating that the
            // request is complete
            reqBody.close();

            return getResponse(http);
        }
        catch (IOException e) {
            // An exception was thrown, so display the exception's stack trace
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection openConnectionToServer(String URL) throws IOException {
        // Create a URL indicating where the server is running, and which web API operation we want to call
        java.net.URL url = new URL(URL);

        // Start constructing our HTTP request
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        //Set output body
        http.setDoOutput(true);

        // Connect to the server and send the HTTP request
        http.connect();

        return http;
    }

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    private String getResponse(HttpURLConnection http) throws IOException {
        // Check the HTTP response has been received from the server.
        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

            // Get the input stream containing the HTTP response body
            InputStream respBody = http.getInputStream();
            // Extract JSON data from the HTTP response body

            return readString(respBody);
        } else {
            // The HTTP response status code indicates an error
            // occurred, so print out the message from the HTTP response
            String respData = readString(http.getErrorStream());
            System.out.println(http.getResponseMessage());
            return respData;
        }
    }
}
