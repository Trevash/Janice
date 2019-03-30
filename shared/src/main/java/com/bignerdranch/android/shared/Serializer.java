package com.bignerdranch.android.shared;

import com.bignerdranch.android.shared.gameStates.AbstractClientGameState;
import com.bignerdranch.android.shared.gameStates.AbstractGameState;
import com.bignerdranch.android.shared.gameStates.AbstractServerGameState;
import com.bignerdranch.android.shared.gameStates.ClientActivePlayerState;
import com.bignerdranch.android.shared.gameStates.ClientChooseDestCardState;
import com.bignerdranch.android.shared.gameStates.ClientDrawTrainCardState;
import com.bignerdranch.android.shared.gameStates.ClientGameOverState;
import com.bignerdranch.android.shared.gameStates.ClientInactiveState;
import com.bignerdranch.android.shared.gameStates.ServerInGameState;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.abstractDoubleRoute;
import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.doubleRouteModelFew;
import com.bignerdranch.android.shared.models.doubleRouteModelMany;
import com.bignerdranch.android.shared.models.singleRouteModel;
import com.bignerdranch.android.shared.proxy.DestinationCardDeckProxy;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.resultobjects.AuthData;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.bignerdranch.android.shared.gameStates.ClientInitialGameState;

import com.bignerdranch.android.shared.models.DestinationCardDeck;
import com.bignerdranch.android.shared.gameStates.ServerInitialGameState;

import com.bignerdranch.android.shared.resultobjects.Results;

public class Serializer {
    private static Serializer sr = new Serializer();

    public static Serializer getInstance() {
        if (sr == null) {
            sr = new Serializer();
        }
        return sr;
    }

    private static Gson parser = new Gson();
    private static RuntimeTypeAdapterFactory<abstractRoute> routeAdapter =
            RuntimeTypeAdapterFactory
                    .of(abstractRoute.class)
                    .registerSubtype(singleRouteModel.class)
                    .registerSubtype(abstractDoubleRoute.class)
                    .registerSubtype(doubleRouteModelFew.class)
                    .registerSubtype(doubleRouteModelMany.class);
    private static RuntimeTypeAdapterFactory<IGameState> iGameStateAdapter =
            RuntimeTypeAdapterFactory
                    .of(IGameState.class)
                    .registerSubtype(AbstractGameState.class)
                    .registerSubtype(ClientActivePlayerState.class)
                    .registerSubtype(ClientInactiveState.class)
                    .registerSubtype(ClientChooseDestCardState.class)
                    .registerSubtype(ClientDrawTrainCardState.class)
                    .registerSubtype(ClientGameOverState.class)
                    .registerSubtype(ClientInitialGameState.class)
                    .registerSubtype(ServerInGameState.class)
                    .registerSubtype(ServerInitialGameState.class);
    private static RuntimeTypeAdapterFactory<AbstractGameState> abGameStateAdapter =
            RuntimeTypeAdapterFactory
                    .of(AbstractGameState.class)
                    .registerSubtype(AbstractClientGameState.class)
                    .registerSubtype(AbstractServerGameState.class)
                    .registerSubtype(ClientActivePlayerState.class)
                    .registerSubtype(ClientInactiveState.class)
                    .registerSubtype(ClientChooseDestCardState.class)
                    .registerSubtype(ClientDrawTrainCardState.class)
                    .registerSubtype(ClientGameOverState.class)
                    .registerSubtype(ClientInitialGameState.class)
                    .registerSubtype(ServerInGameState.class)
                    .registerSubtype(ServerInitialGameState.class);
    private static RuntimeTypeAdapterFactory<AbstractClientGameState> abClientGameStateAdapter =
            RuntimeTypeAdapterFactory
                    .of(AbstractClientGameState.class)
                    .registerSubtype(ClientActivePlayerState.class)
                    .registerSubtype(ClientInactiveState.class)
                    .registerSubtype(ClientChooseDestCardState.class)
                    .registerSubtype(ClientDrawTrainCardState.class)
                    .registerSubtype(ClientGameOverState.class)
                    .registerSubtype(ClientInitialGameState.class);
    private static RuntimeTypeAdapterFactory<AbstractServerGameState> abServerGameStateAdapter =
            RuntimeTypeAdapterFactory
                    .of(AbstractServerGameState.class)
                    .registerSubtype(ServerInGameState.class)
                    .registerSubtype(ServerInitialGameState.class);

    private static RuntimeTypeAdapterFactory<IDestinationCardDeck> destinationCardAdapter =
            RuntimeTypeAdapterFactory
                    .of(IDestinationCardDeck.class)
                    .registerSubtype(DestinationCardDeck.class)
                    .registerSubtype(DestinationCardDeckProxy.class);


    private static Gson parser2 = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapterFactory(routeAdapter)
            .registerTypeAdapterFactory(iGameStateAdapter)
            .registerTypeAdapterFactory(abGameStateAdapter)
            .registerTypeAdapterFactory(abClientGameStateAdapter)
            .registerTypeAdapterFactory(abServerGameStateAdapter)
            .registerTypeAdapterFactory(destinationCardAdapter)
            .create();

    public String serializeObject(Object obj) {
        return parser2.toJson(obj);
    }

    public GenericCommand deserializeCommand(String str) {
        return parser.fromJson(str, GenericCommand.class);
    }

    public Results deserializeResults(String str) {
        return parser2.fromJson(str, Results.class);
    }

    public Object deserializeObject(String object, Class<?> cName) {
        return parser2.fromJson(object, cName);
    }

    public AuthData deserializeAuthData(String str) {
        return parser2.fromJson(str, AuthData.class);
    }

    public CreateGameRequest deserializeCreateCommand(String str) {
        return parser2.fromJson(str, CreateGameRequest.class);
    }

    public JoinGameRequest deserializeJoinCommand(String str) {
        return parser2.fromJson(str, JoinGameRequest.class);
    }

    public StartGameRequest deserializeStartCommand(String str) {
        return parser2.fromJson(str, StartGameRequest.class);
    }

    public GameListData deserializeGameListData(String str) {
        return parser2.fromJson(str, GameListData.class);
    }

    /*
    public static void main(String args[]){
    	IGameState gameState = new ClientInitialGameState(new IServer() {
            @Override
            public Results login(LoginRequest request) {
                return null;
            }
            @Override
            public Results register(RegisterRequest request) {
                return null;
            }
            @Override
            public Results createGame(CreateGameRequest request) {
                return null;
            }
            @Override
            public Results startGame(StartGameRequest request)  {
                return null;
            }
            @Override
            public Results joinGame(JoinGameRequest request)  {
                return null;
            }

            @Override
            public Results claimRoute(ClaimRouteRequest request) {
                return null;
            }

            @Override
            public Results updateChatbox(UpdateChatboxRequest request) {
                return null;
            }
            @Override
            public Results drawDestinationCards(DrawDestinationCardsRequest request) {
                return null;
            }
            @Override
            public Results returnDestinationCard(ReturnDestinationCardsRequest request) {
                return null;
            }
        }, new gameModel(), 30);
    	gameModel game = new gameModel("gameName", null, gameState);
    	String json1 = parser.toJson(game);
    	String json2 = parser2.toJson(game);

    	System.out.println(json1);
    	System.out.println(json2);
    	//this will break, as expected
    	//System.out.println(parser.fromJson(json1, gameModel.class));
    	System.out.println(parser2.fromJson(json2, gameModel.class));

    } // */
}
