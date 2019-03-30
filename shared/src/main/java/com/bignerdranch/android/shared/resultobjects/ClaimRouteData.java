package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.bignerdranch.android.shared.models.usernameModel;

import java.util.LinkedList;
import java.util.List;

public class ClaimRouteData {
    private List<abstractRoute> routes;
    private List<trainCardModel> hand;
    private List<trainCardModel> discards;
    private abstractRoute curRoute;
    private int points;
    private gameIDModel gameID;
    private playerIDModel playerID;
    private usernameModel username;
    private int locomotives;

    public ClaimRouteData(gameIDModel gameID, playerIDModel playerID, List<abstractRoute> routes,
                          List<trainCardModel> hand, List<trainCardModel> discards,
                          abstractRoute curRoute, int points, usernameModel username, int locomotives) {
        this.gameID = gameID;
        this.playerID = playerID;
        this.routes = routes;
        this.hand = hand;
        this.discards = discards;
        this.curRoute = curRoute;
        this.points = points;
        this.username = username;
        this.locomotives = locomotives;
    }

    public List<abstractRoute> getRoutes() {
        return routes;
    }

    public gameIDModel getGameID() {
        return gameID;
    }

    public abstractRoute getCurRoute() {
        return curRoute;
    }

    public usernameModel getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public List<trainCardModel> getDiscards() {
        return discards;
    }

    public List<trainCardModel> getHand() {
        return hand;
    }

    public playerIDModel getPlayerID() {
        return playerID;
    }

    public int getNumLocomotives() {
        return locomotives;
    }
}
