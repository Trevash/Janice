package com.bignerdranch.android.shared.models;

public class authTokenModel {
    String value = null;

    public authTokenModel(String newValue) throws Exception {
        if(serverModel.getInstance().userIDExists(newValue)){
            throw new Exception("AuthToken exists!");
        }

        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
