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

    public GenericCommand deserializeObject(String str){ return parser.fromJson(str, GenericCommand.class); }

    public String serializeResults(Results r){
        return parser.toJson(r);
    }

    public Results deserializeResults(String str){
        return parser.fromJson(str, Results.class);
    }

}
