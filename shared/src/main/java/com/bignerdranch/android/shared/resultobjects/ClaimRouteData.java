package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.usernameModel;

import java.util.List;

public class ClaimRouteData {
    private List<abstractRoute> routes;
    private abstractRoute curRoute;
    private gameIDModel gameID;
    private usernameModel username;

    public ClaimRouteData(gameIDModel gameID, List<abstractRoute> routes, abstractRoute curRoute, usernameModel username){
        this.gameID = gameID;
        this.routes = routes;
        this.curRoute = curRoute;
        this.username = username;
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
}
