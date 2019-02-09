package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.authTokenModel;

public class JoinGameRequest {
    private gameModel model;
    private authTokenModel auth;

    public JoinGameRequest(gameModel model, authTokenModel auth) {
        this.model = model;
        this.auth = auth;
    }

    public gameModel getModel() {
        return model;
    }

    public void setModel(gameModel model) {
        this.model = model;
    }

    public authTokenModel getAuth() {
        return auth;
    }

    public void setAuth(authTokenModel auth) {
        this.auth = auth;
    }
}
