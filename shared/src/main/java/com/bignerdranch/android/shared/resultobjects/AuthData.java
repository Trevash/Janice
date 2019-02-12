package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.*;

public class AuthData {
    private authTokenModel authToken;

    public AuthData(authTokenModel authToken) {
        this.authToken = authToken;
    }

    public authTokenModel getAuthToken() {
        return authToken;
    }
}
