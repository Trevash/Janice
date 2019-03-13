package com.janus;


import java.util.ArrayList;
import java.util.List;
import com.bignerdranch.android.shared.models.*;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.janus.Communication.ServerProxy;

public class ClientModel {


    private gameModel game;
    private List<gameModel> serverGameList = new ArrayList<>();
    private userModel user;
    private authTokenModel auth;
    private static ClientModel model;
    private chatboxModel chatbox;

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
        game.setClientMode(ServerProxy.getInstance());
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
            if(id.getValue().equals(game.getGameID().getValue())){
                return game;
            }
        }
        return null;
    }

    public chatboxModel getChatbox() {
        return chatbox;
    }

    public void setChatbox(chatboxModel chatbox) {
        this.chatbox = chatbox;
    }
}
