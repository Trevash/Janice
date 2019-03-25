package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.gameIDModel;

public class UpdateGameStatusRequest {
    private gameIDModel gameID;
    private String historyString;

    public UpdateGameStatusRequest(gameIDModel gameID, String historyString){
        this.gameID = gameID;
        this.historyString = historyString;
    }

    public gameIDModel getGameID() {
        return gameID;
    }

    public String getHistoryString() {
        return historyString;
    }
}
