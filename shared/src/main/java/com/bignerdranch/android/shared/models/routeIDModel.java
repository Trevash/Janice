package com.bignerdranch.android.shared.models;

import java.util.UUID;

public class routeIDModel {
    private String value;

    public routeIDModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }
}