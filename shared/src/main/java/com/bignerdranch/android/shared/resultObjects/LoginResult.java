package com.bignerdranch.android.shared.resultObjects;

import java.util.List;

import com.bignerdranch.android.shared.models.gameModel;

public class LoginResult {
    private List<gameModel> games;

    public LoginResult(List<gameModel> games) {
        this.games = games;
    }

    public List<gameModel> getGames() {
        return games;
    }
}
