package com.bignerdranch.android.shared.models;

import java.util.ArrayList;
import java.util.List;

public class gameModel {
    private gameIDModel gameID;
    private String gameName;
    private boolean gameStarted;
    private List<playerModel> players = new ArrayList<>();
    // private playerModel hostPlayer;

    // host is the first player in the list
    public playerModel getHostPlayer() {
        return players.get(0);
    }

    public gameModel(authTokenModel auth) throws Exception {
        gameID = new gameIDModel();
        gameName = serverModel.getInstance().getUser(auth).getUserName().getValue() + "'s_Game!";
        playerModel hostPlayer = new playerModel(serverModel.getInstance().getUser(auth).getUserName(), false, true);
        gameStarted = false;
        players.add(hostPlayer);
    }

    public gameIDModel getGameID() {
        return gameID;
    }

    public void addPlayer(playerModel newPlayer) throws Exception {
        for (playerModel curPlayer : this.players) {
            if(curPlayer.getUserName().getValue().equals(newPlayer.getUserName().getValue())){
                throw new Exception("User is already a player in this game");
            }
        }
        if(players.size() >= 5) {
            throw new IllegalStateException("Max number of players reached!");
        }

        players.add(newPlayer);
    }

    public int numPlayers(){
        return players.size();
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isGameStarted(){return gameStarted;}

    public void startGame() {
        if (players.size() < 2){
            throw new IllegalStateException("Insufficient number of players to start game!");
        }
        else if(players.size() > 5){
            throw new IllegalStateException("Too many players to start game!");
        }

        this.gameStarted = true;
        //TODO:Alert all players that game has begun
    }

    public List<playerModel> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        } else {
            return this.getGameID() == ((gameModel) o).getGameID();
        }
    }
}
