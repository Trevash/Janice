package com.bignerdranch.android.shared;

import com.bignerdranch.android.shared.models.abstractDoubleRoute;
import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.doubleRouteModelFew;
import com.bignerdranch.android.shared.models.doubleRouteModelMany;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.singleRouteModel;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.resultobjects.AuthData;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.bignerdranch.android.shared.resultobjects.Results;

public class Serializer {
    private static Serializer sr = new Serializer();
    public static Serializer getInstance() {
        if (sr == null){
            sr = new Serializer();
        }
        return sr;
    }
    private static Gson parser = new Gson();
    static RuntimeTypeAdapterFactory<abstractRoute> adapter = 
    		RuntimeTypeAdapterFactory
    		.of(abstractRoute.class)
    		.registerSubtype(singleRouteModel.class)
    		.registerSubtype(abstractDoubleRoute.class)
    		.registerSubtype(doubleRouteModelFew.class)
    		.registerSubtype(doubleRouteModelMany.class);
    private static Gson parser2 = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(adapter).create();

    
    public String serializeObject(Object obj){
        return parser2.toJson(obj);
    }

    public GenericCommand deserializeCommand(String str){ return parser.fromJson(str, GenericCommand.class); }

    public Results deserializeResults(String str) {
        return parser2.fromJson(str, Results.class);
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

    public GameListData deserializeGameListData(String str){
        return parser2.fromJson(str, GameListData.class);
    }
//    public static void main(String args[]){
//    	gameModel game = new gameModel("gameName", null);
//    	String json1 = parser.toJson(game);
//    	String json2 = parser2.toJson(game);
//
//    	System.out.println(json1);
//    	System.out.println(json2);
//    	//this will break, as expected
//    	//System.out.println(parser.fromJson(json1, gameModel.class));
//    	System.out.println(parser2.fromJson(json2, gameModel.class));
//
//    
//    }
}
