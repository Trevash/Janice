package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.playerIDModel;

public class DrawTrainCardRequest {
    private int index;
    private playerIDModel playerID;
    private gameIDModel gameID;

    public DrawTrainCardRequest(int index, playerIDModel playerID, gameIDModel gameID){
        this.index = index;
        this.playerID = playerID;
        this.gameID = gameID;
    }

    public int getIndex() {
        return index;
    }

    public playerIDModel getPlayerID() {
        return playerID;
    }

    public gameIDModel getGameID() {
        return gameID;
    }
}
