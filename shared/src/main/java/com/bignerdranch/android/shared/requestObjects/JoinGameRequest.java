package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.gameModel;

public class JoinGameRequest {
    private gameModel model;

    public JoinGameRequest(gameModel model) {
        this.model = model;
    }

    public gameModel getModel() {
        return model;
    }

    public void setModel(gameModel model) {
        this.model = model;
    }
}
