package com.janus;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import com.bignerdranch.android.shared.models.*;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.janus.Communication.TtRClient;

public class ClientModel {
    private gameModel game;
    private List<gameModel> serverGameList;
    private userModel user;
    private authTokenModel auth;
    private static ClientModel model;

    private ClientModel() {}

    public static ClientModel getInstance() {
        if (model == null){
            model = new ClientModel();
        }
        return model;
    }

    public gameModel getGame() {
        return game;
    }

    public void setGame(gameModel game) {
        this.game = game;
    }

    public userModel getUser() {
        return user;
    }

    public void setUser(userModel user) {
        this.user = user;
    }

    public authTokenModel getAuth() {
        return auth;
    }

    public void setAuth(authTokenModel auth) {
        this.auth = auth;
    }

    public List<gameModel> getServerGameList() {
        return serverGameList;
    }

    public void setServerGameList(GameListData serverGameList) {
        this.serverGameList = serverGameList.getGames();
    }
}
