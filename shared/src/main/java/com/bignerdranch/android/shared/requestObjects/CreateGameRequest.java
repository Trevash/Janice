package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.authTokenModel;

public class CreateGameRequest {
    private authTokenModel auth;

    public CreateGameRequest(authTokenModel newAuth){
        auth = newAuth;
    }

    public authTokenModel getAuth() {
        return auth;
    }
}
