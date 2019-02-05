package com.bignerdranch.android.shared;

import com.google.gson.Gson;

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

    public Results deserializeResults(String str){
        return parser.fromJson(str, Results.class);
    }

}
