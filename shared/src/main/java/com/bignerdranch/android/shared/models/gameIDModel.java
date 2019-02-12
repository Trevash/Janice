package com.bignerdranch.android.shared.models;

import java.util.UUID;

public class gameIDModel {
    private String value;

    gameIDModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }
}
