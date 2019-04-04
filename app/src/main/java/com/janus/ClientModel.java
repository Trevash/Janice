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
    private static ClientModel model;
    private chatboxModel chatbox = new chatboxModel();

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
        game.setClientMode(ServerProxy.getInstance(), getPlayerNumber(game));
        this.game = game;
    }

    private int getPlayerNumber(gameModel game) {
        return game.getPlayers().indexOf(game.getPlayerByUsername(getUser().getUserName()));
    }

    public userModel getUser() {
        return user;
    }

    public void setUser(userModel user) {
        this.user = user;
    }

    public List<gameModel> getServerGameList() {
        return serverGameList;
    }

    public void setServerGameList(GameListData serverGameList) {
        this.serverGameList = serverGameList.getGames();
    }

    public chatboxModel getChatbox() {
        return chatbox;
    }

    public void setChatbox(chatboxModel chatbox) {
        this.chatbox = chatbox;
    }

    public chatboxModel getHistory() {
        return game.getGameHistory();
    }

    public void setGameHistory(chatboxModel gameHistory) {
        this.game.setGameHistory(gameHistory);
    }

    public void setTurnCounter(int turnCounter) {
        this.game.setTurnCounter(turnCounter);
    }
}
