package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.playerIDModel;

public class DrawDestinationCardsRequest {

    private gameIDModel id;
    private playerIDModel playerID;

    public DrawDestinationCardsRequest(gameIDModel gameID, playerIDModel playerID) {
        this.id = gameID;
        this.playerID = playerID;
    }

    public gameIDModel getGameID() {
        return id;
    }

    public playerIDModel getPlayerID() {
        return playerID;
    }
}
