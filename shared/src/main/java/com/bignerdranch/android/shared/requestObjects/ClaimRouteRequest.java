package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.interfaces.IGameRequest;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.routeIDModel;

public class ClaimRouteRequest implements IGameRequest {
    private authTokenModel auth;
    private gameIDModel gameID;
    private playerIDModel playerID;
    private routeColorEnum color;
    private routeIDModel routeID;

    public ClaimRouteRequest(authTokenModel auth, gameIDModel gameID, playerIDModel playerID, routeColorEnum color, routeIDModel routeID){
        this.auth = auth;
        this.gameID = gameID;
        this.playerID = playerID;
        this.color = color;
        this.routeID = routeID;
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

    public routeIDModel getRouteID() {
        return routeID;
    }

    public routeColorEnum getColor() {
        return color;
    }
}
