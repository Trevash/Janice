package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameIDModel;

public class StartGameRequest {
    private gameIDModel gameID;
    private authTokenModel auth;

    //public StartGameRequest(gameModel newGame, authTokenModel newAuth){
    //    gameID = newGame.getGameID();
    //    auth = newAuth;
    //}

    public StartGameRequest(gameIDModel gameID, authTokenModel authorization) {
        this.gameID = gameID;
        auth = authorization;
    }

    //public gameModel getModel() {
    //    return game;
    //}

    public gameIDModel getGameID() {
        return gameID;
    }

    public authTokenModel getAuth() {
        return auth;
    }
}
