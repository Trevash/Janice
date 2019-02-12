package com.bignerdranch.android.shared;

import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.resultobjects.AuthData;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.google.gson.Gson;

import com.bignerdranch.android.shared.resultobjects.Results;

public class Serializer {
    private static Serializer sr = new Serializer();
    public static Serializer getInstance() {
        if (sr == null){
            sr = new Serializer();
        }
        return sr;
    }
    private Gson parser = new Gson();

    public String serializeObject(Object obj){
        return parser.toJson(obj);
    }

    public GenericCommand deserializeCommand(String str){ return parser.fromJson(str, GenericCommand.class); }

    public Results deserializeResults(String str) {
        return parser.fromJson(str, Results.class);
    }

    public AuthData deserializeAuthData(String str) {
        return parser.fromJson(str, AuthData.class);
    }

    public CreateGameRequest deserializeCreateCommand(String str) {
        return parser.fromJson(str, CreateGameRequest.class);
    }

    public JoinGameRequest deserializeJoinCommand(String str) {
        return parser.fromJson(str, JoinGameRequest.class);
    }

    public StartGameRequest deserializeStartCommand(String str) {
        return parser.fromJson(str, StartGameRequest.class);
    }

    public GameListData deserializeGameListData(String str){
        return parser.fromJson(str, GameListData.class);
    }
}
