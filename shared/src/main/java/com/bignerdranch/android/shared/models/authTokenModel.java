package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.randomIDGenerator;

public class authTokenModel {
    private String value;

    public authTokenModel() {
        String newValue = randomIDGenerator.getInstance().getRandomString(16);
        while(serverModel.getInstance().authTokenExists(newValue)){
            newValue = randomIDGenerator.getInstance().getRandomString(16);
        }
        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
