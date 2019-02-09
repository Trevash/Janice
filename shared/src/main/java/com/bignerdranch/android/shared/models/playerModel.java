package com.bignerdranch.android.shared.models;

public class playerModel {
    private usernameModel userName = null;
    private playerIDModel id = null;

    public playerModel(usernameModel userName, playerIDModel id, boolean isReady, boolean isHost) {
        this.userName = userName;
        this.id = id;
        this.isReady = isReady;
        this.isHost = isHost;
    }

    public boolean isHost() {
        return isHost;
    }

    private boolean isReady = false;
    private boolean isHost = false;

    public playerIDModel getId() {
        return id;
    }

    public usernameModel getUserName() {
        return userName;
    }

    public boolean isReady() {
        return isReady;
    }
}

