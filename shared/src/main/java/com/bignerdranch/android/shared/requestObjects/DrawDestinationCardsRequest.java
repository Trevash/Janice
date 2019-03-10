package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.gameIDModel;

public class DrawDestinationCardsRequest {

    gameIDModel id;

    public DrawDestinationCardsRequest(gameIDModel gameID) {
        id = gameID;
    }

    public gameIDModel getGameID() {
        return id;
    }
}
