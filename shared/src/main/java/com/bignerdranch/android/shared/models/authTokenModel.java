package com.bignerdranch.android.shared.models;

import java.util.UUID;

public class authTokenModel {
    private String value;

    public authTokenModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }
}
