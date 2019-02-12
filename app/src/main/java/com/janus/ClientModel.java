package com.janus;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import com.bignerdranch.android.shared.models.*;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.janus.Communication.TtRClient;

public class ClientModel {

    // TODO answer: What implements CurrentView?
    public interface CurrentView {
        void updateUI();
    }

    private gameModel game;
    private List<gameModel> serverGameList = new ArrayList<>();
    private userModel user;
    private authTokenModel auth;
    private static ClientModel model;
    private CurrentView currentView;

    private ClientModel() {}

    public static ClientModel getInstance() {
        if (model == null){
            model = new ClientModel();
        }
        return model;
    }

    public void setCurrentView(CurrentView view) {
        this.currentView = view;
    }

    public gameModel getGame() {
        return game;
    }

    public void setGame(gameModel game) {
        this.game = game;
        currentView.updateUI();
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
        currentView.updateUI();
    }

    public boolean gameIDExists(gameIDModel id) {
        for(gameModel game : serverGameList){
            if(game.getGameID().equals(id)){
                return true;
            }
        }
        return false;
    }

    public gameModel getGameByID(gameIDModel id) {
        for(gameModel game: serverGameList){
            if(id.equals(game.getGameID())){
                return game;
            }
        }
        return null;
    }
}
