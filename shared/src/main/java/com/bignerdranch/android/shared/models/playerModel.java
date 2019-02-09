package com.bignerdranch.android.shared.models;

public class playerModel {
    private usernameModel userName = null;
    private playerIDModel id = null;
    private boolean isReady = false;

    public playerIDModel getId() {
        return id;
    }

    public playerModel(usernameModel userName, playerIDModel id, boolean isReady) {
        this.userName = userName;
        this.id = id;
        this.isReady = isReady;
    }

    public usernameModel getUserName() {
        return userName;
    }

    public boolean isReady() {
        return isReady;
    }
}

