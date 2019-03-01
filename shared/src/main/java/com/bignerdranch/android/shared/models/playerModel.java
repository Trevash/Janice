package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.playerColorEnum;

import java.util.ArrayList;
import java.util.List;

public class playerModel {
    private usernameModel userName;
    private playerIDModel id;
    private boolean isReady;
    private boolean isHost;


    // train hand
    // destination hand
    private List<destinationCardModel> destinationCards = new ArrayList<>();

    // claimed routes
    private List<abstractRoute> claimedRoutes = new ArrayList<>();

    // color
    private playerColorEnum playerColor;

    // Locomotives left
    private int locomotives = 45;

    // points
    private int points = 0;

    public playerModel(usernameModel userName, boolean isReady, boolean isHost, playerColorEnum playerColor) {
        this.userName = userName;
        this.id = new playerIDModel();
        this.isReady = isReady;
        this.isHost = isHost;
        this.playerColor = playerColor;
    }

    public boolean isHost() {
        return isHost;
    }

    public playerIDModel getId() {
        return id;
    }

    public usernameModel getUserName() {
        return userName;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setIsReady(boolean ready) {isReady = ready;}

    public playerColorEnum getPlayerColor() {
        return playerColor;
    }
}

