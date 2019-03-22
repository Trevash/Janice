package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.playerIDModel;

public class ClaimRouteRequest {
    private authTokenModel auth;
    private gameIDModel gameID;
    private playerIDModel playerID;
    private routeColorEnum color;
    private abstractRoute route;

    public ClaimRouteRequest(authTokenModel auth, gameIDModel gameID, playerIDModel playerID, routeColorEnum color, abstractRoute route){
        this.auth = auth;
        this.gameID = gameID;
        this.playerID = playerID;
        this.color = color;
        this.route = route;
    }

    public authTokenModel getAuth() {
        return auth;
    }

    public gameIDModel getGameID() {
        return gameID;
    }

    public playerIDModel getPlayerID() {
        return playerID;
    }

    public abstractRoute getRoute() {
        return route;
    }

    public routeColorEnum getColor() {
        return color;
    }
}
