package com.janus.Communication;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest;
import com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;
import com.bignerdranch.android.shared.requestObjects.RegisterRequest;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.exceptions.WebsocketNotConnectedException;

import static com.bignerdranch.android.shared.Constants.Commands.CLAIM_ROUTE;
import static com.bignerdranch.android.shared.Constants.Commands.CREATE;
import static com.bignerdranch.android.shared.Constants.Commands.DRAW_DESTINATION_CARDS;
import static com.bignerdranch.android.shared.Constants.Commands.DRAW_FIRST_TRAIN_CARD;
import static com.bignerdranch.android.shared.Constants.Commands.DRAW_SECOND_TRAIN_CARD;
import static com.bignerdranch.android.shared.Constants.Commands.JOIN;
import static com.bignerdranch.android.shared.Constants.Commands.LOGIN;
import static com.bignerdranch.android.shared.Constants.Commands.REGISTER;
import static com.bignerdranch.android.shared.Constants.Commands.RETURN_DESTINATION_CARDS;
import static com.bignerdranch.android.shared.Constants.Commands.START;
import static com.bignerdranch.android.shared.Constants.Commands.UPDATE_CHAT;

public class ServerProxy implements IServer {

    private String expectedResultType = null;

    public interface CurrentState {

    }

    private static ServerProxy scp;
    private static TtRClient client;

    private Results messageResult;
    private CurrentState state;

    private ServerProxy() {
    }

    public static ServerProxy getInstance() {
        if (scp == null) {
            scp = new ServerProxy();
        }
        return scp;
    }

    //private String className = "server.handlers";

    public void connectClient() throws InterruptedException, URISyntaxException {
        String webSocketAddress = "ws://" + Constants.IP_ADDRESS + ":" + Constants.PORT;
        client = new TtRClient(new URI(webSocketAddress));
        client.connectBlocking();
    }

    public void disconnectClient() throws InterruptedException {
        client.closeBlocking();
    }

    @Override
    public Results login(LoginRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.LoginRequest",
                "login",
                LOGIN);
    }

    @Override
    public Results register(RegisterRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.RegisterRequest",
                "register",
                 REGISTER);
    }

    @Override
    public Results createGame(CreateGameRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.CreateGameRequest",
                "createGame",
                 CREATE);
    }

    @Override
    public Results startGame(StartGameRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.StartGameRequest",
                "startGame",
                 START);
    }

    public Results joinGame(JoinGameRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.JoinGameRequest",
                "joinGame",
                 JOIN);
    }

    @Override
    public Results claimRoute(ClaimRouteRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest",
                "claimRoute",
                 CLAIM_ROUTE);
    }

    public Results updateChatbox(UpdateChatboxRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest",
                "updateChatbox",
                 UPDATE_CHAT);
    }

    public Results drawDestinationCards(DrawDestinationCardsRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest",
                "drawDestinationCards",
                 DRAW_DESTINATION_CARDS);
    }

    public Results returnDestinationCard(ReturnDestinationCardsRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest",
                "returnDestinationCard",
                 RETURN_DESTINATION_CARDS);
    }

    public Results drawFirstTrainCard(DrawTrainCardRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest",
                "drawFirstTrainCard",
                 DRAW_FIRST_TRAIN_CARD);
    }

    public Results drawSecondTrainCard(DrawTrainCardRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest",
                "drawSecondTrainCard",
                 DRAW_SECOND_TRAIN_CARD);
    }

    private Results sendGenericRequest(Object request, String paramType, String methodName, String expectedResultType) {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {paramType};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", methodName, paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        this.messageResult = null;
        this.expectedResultType = expectedResultType;
        try {
        if (client.isClosed()) {
        	try {
				this.connectClient();
			} catch (InterruptedException e) {
	        	return new Results("disconnected",false,"Server Down, Try Again");
			} catch (URISyntaxException e) {
	        	return new Results("disconnected",false,"Server Down, Try Again");
			}
        }
        client.send(commandObjStr);
        return waitForMessageResult();
        } catch(WebsocketNotConnectedException e) {
        	return new Results("disconnected",false,"Server Down, Try Again");
        }
    }

    public void checkMessageResult(Results result) {
        if(result.getType().equals(this.expectedResultType) || result.getType().equals("ERROR")){
            this.messageResult = result;
        }
    }

    private Results waitForMessageResult() {
        while (this.messageResult == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return messageResult;
    }
}
