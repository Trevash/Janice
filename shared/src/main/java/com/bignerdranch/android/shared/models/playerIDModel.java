package com.bignerdranch.android.shared.models;

import java.util.UUID;

public class playerIDModel {
    private String value;

    playerIDModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }
}
