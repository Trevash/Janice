package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.playerIDModel;

public class DrawTrainCardRequest {
    private int index; // 0 is deck, 1-5 is face-up card
    // Note: server will need to translate the face-up cards into a 0-based index
    private playerIDModel playerID;
    private gameIDModel gameID;
    private authTokenModel authtoken;

    public DrawTrainCardRequest(authTokenModel authtoken, int index, playerIDModel playerID, gameIDModel gameID){
        this.authtoken = authtoken;
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

    public authTokenModel getAuthtoken() {
        return authtoken;
    }
}
