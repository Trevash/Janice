package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.randomIDGenerator;

public class userIDModel {
    private String value;

    public userIDModel() {
        String newValue = randomIDGenerator.getInstance().getRandomString(16);
        while(serverModel.getInstance().userIDExists(newValue)){
            newValue = randomIDGenerator.getInstance().getRandomString(16);
        }
        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
