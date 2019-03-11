package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;

public class StartGameRequest {
    //private gameModel game;
    private gameIDModel gameID;
    private authTokenModel auth;

    public StartGameRequest(gameModel newGame, authTokenModel newAuth){
        gameID = newGame.getGameID(); // TODO switch to passing in a gameIDModel
        auth = newAuth;
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
