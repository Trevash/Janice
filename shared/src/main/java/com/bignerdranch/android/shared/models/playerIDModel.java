package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.randomIDGenerator;

public class playerIDModel {
    private String value;

    playerIDModel() {
        String newValue = randomIDGenerator.getInstance().getRandomString(16);
        while(serverModel.getInstance().playerIDExists(newValue)){
            newValue = randomIDGenerator.getInstance().getRandomString(16);
        }
        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
