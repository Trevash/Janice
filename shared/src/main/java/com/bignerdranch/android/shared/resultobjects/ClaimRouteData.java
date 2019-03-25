package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

public class ClaimRouteData {
    private List<abstractRoute> routes;
    private gameIDModel gameID;

    public ClaimRouteData(gameIDModel gameID, List<abstractRoute> routes){
        this.gameID = gameID;
        this.routes = routes;
    }

    public List<abstractRoute> getRoutes() {
        return routes;
    }

    public gameIDModel getGameID() {
        return gameID;
    }
}
