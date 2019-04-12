package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.interfaces.IGameRequest;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.authTokenModel;

public class JoinGameRequest implements IGameRequest {
    private gameIDModel gameID;
    private authTokenModel auth;

    public JoinGameRequest(gameIDModel gameID, authTokenModel auth) {
        this.gameID = gameID;
        this.auth = auth;
    }

    public authTokenModel getAuth() {
        return auth;
    }
    
    public gameIDModel getGameID() {
        return gameID;
    }
}
