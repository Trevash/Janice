package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.randomIDGenerator;

public class gameIDModel {
    private String value;

    gameIDModel() {
        String newValue = randomIDGenerator.getInstance().getRandomString(16);
        while(serverModel.getInstance().gameIDExists(newValue)){
            newValue = randomIDGenerator.getInstance().getRandomString(16);
        }
        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
