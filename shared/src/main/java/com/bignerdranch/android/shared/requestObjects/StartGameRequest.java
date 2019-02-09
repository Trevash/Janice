package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameModel;

public class StartGameRequest {
    private gameModel game;
    private authTokenModel auth;

    public StartGameRequest(gameModel newGame, authTokenModel newAuth){
        game = newGame;
        auth = newAuth;
    }

    public gameModel getModel() {
        return game;
    }

    public authTokenModel getAuth() {
        return auth;
    }
}
