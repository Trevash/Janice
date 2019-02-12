package com.bignerdranch.android.shared.models;

import java.util.UUID;

public class userIDModel {
    private String value;

    public userIDModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }
}
