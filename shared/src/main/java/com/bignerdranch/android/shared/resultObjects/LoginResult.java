package com.bignerdranch.android.shared.resultObjects;

import java.util.List;

import com.bignerdranch.android.shared.models.*;

public class LoginResult {
    private List<gameModel> games;
    private authTokenModel authToken;

    public LoginResult(List<gameModel> games, authTokenModel authToken) {
        this.games = games;
        this.authToken = authToken;
    }

    public List<gameModel> getGames() {
        return games;
    }

    public authTokenModel getAuthToken() {
        return authToken;
    }
}
