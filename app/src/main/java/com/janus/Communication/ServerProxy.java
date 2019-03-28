package com.janus.Communication;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.Constants;
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

public class ServerProxy implements IServer {

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

    /*
    public Results login(LoginRequest request) throws Exception {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.LoginRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "login", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    } // */

    @Override
    public Results login(LoginRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.LoginRequest",
                "login");
    }

    /*
    public Results register(RegisterRequest request) throws Exception {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.RegisterRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "register", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    } // */

    @Override
    public Results register(RegisterRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.RegisterRequest",
                "register");
    }

    /*
    public Results createGame(CreateGameRequest request) throws Exception {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.CreateGameRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "createGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    } // */

    @Override
    public Results createGame(CreateGameRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.CreateGameRequest",
                "createGame");
    }

    /*
    public Results startGame(StartGameRequest request) throws Exception {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.StartGameRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "startGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    } // */

    @Override
    public Results startGame(StartGameRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.StartGameRequest",
                "startGame");
    }

    /*
    public Results joinGame(JoinGameRequest request) throws Exception {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.JoinGameRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "joinGame", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    } // */


    public Results joinGame(JoinGameRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.JoinGameRequest",
                "joinGame");
    }

    /*
    @Override
    public Results claimRoute(ClaimRouteRequest request) throws Exception {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "claimRoute", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }

        return messageResult;
    } // */


    @Override
    public Results claimRoute(ClaimRouteRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest",
                "claimRoute");
    }

    /*
    public Results updateChatbox(UpdateChatboxRequest request) throws Exception {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "updateChatbox", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
	} // */


    public Results updateChatbox(UpdateChatboxRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest",
                "updateChatbox");
    }

    public Results testSocket(UpdateChatboxRequest request) throws Exception {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "updateChatbox", paramTypes, paramValues);
        //switch this one out with yours
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        //
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            Thread.sleep(100);
        }
        return messageResult;
    }

	/*
    public Results drawDestinationCards(DrawDestinationCardsRequest request) {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "drawDestinationCards", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return messageResult;
    } // */

    public Results drawDestinationCards(DrawDestinationCardsRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest",
                "drawDestinationCards");
    }

    /*
    public Results returnDestinationCard(ReturnDestinationCardsRequest request) {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "returnDestinationCard", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return messageResult;
    } // */
    public Results returnDestinationCard(ReturnDestinationCardsRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest",
                "returnDestinationCard");
    }

    /*
    public Results drawFirstTrainCard(DrawTrainCardRequest request) {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "drawFirstTrainCard", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return messageResult;
    } // */

    public Results drawFirstTrainCard(DrawTrainCardRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest",
                "drawFirstTrainCard");
    }

    /*
    public Results drawSecondTrainCard(DrawTrainCardRequest request) {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {"com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest"};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", "drawSecondTrainCard", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return messageResult;
    } // */

    public Results drawSecondTrainCard(DrawTrainCardRequest request) {
        return sendGenericRequest(request,
                "com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest",
                "drawSecondTrainCard");
    }

    private Results sendGenericRequest(Object request, String paramType, String methodName) {
        Object[] paramValues = {Serializer.getInstance().serializeObject(request)};
        String[] paramTypes = {paramType};
        GenericCommand commandObj = new GenericCommand("server.handlers.commandHandler", methodName, paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        client.send(commandObjStr);
        messageResult = null;
        client.setMessageResultToNull();
        while (messageResult == null) {
            messageResult = client.getResults();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return messageResult;
    }

}
