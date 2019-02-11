package com.bignerdranch.android.shared.resultobjects;

import java.util.List;

import com.bignerdranch.android.shared.models.*;

public class LoginData {
    private GameListData games;
    private authTokenModel authToken;

    public LoginData(GameListData games, authTokenModel authToken) {
        this.games = games;
        this.authToken = authToken;
    }

    public GameListData getGames() {
        return games;
    }

    public authTokenModel getAuthToken() {
        return authToken;
    }
}
