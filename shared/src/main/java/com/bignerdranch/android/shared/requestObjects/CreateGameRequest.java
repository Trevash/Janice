package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.authTokenModel;

public class CreateGameRequest {
    private String gameName;
    private authTokenModel auth;

    public CreateGameRequest(String newGameName, authTokenModel newAuth){
        gameName = newGameName;
        auth = newAuth;
    }

    public String getGameName() {
        return gameName;
    }

    public authTokenModel getAuth() {
        return auth;
    }
}
