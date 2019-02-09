package com.bignerdranch.android.shared.resultobjects;

import java.util.List;

import com.bignerdranch.android.shared.models.*;

public class LoginData {
    private List<gameModel> games;
    private authTokenModel authToken;

    public LoginData(List<gameModel> games, authTokenModel authToken) {
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
