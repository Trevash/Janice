package com.bignerdranch.android.shared.models;

import java.util.ArrayList;
import java.util.List;

public class gameModel {
    private gameIDModel gameID;
    private String gameName;
    private boolean gameStarted;
    private userModel hostUser;
    private List<playerModel> players = new ArrayList<>();

    public gameModel(authTokenModel auth) throws Exception {
        gameID = new gameIDModel();
        gameName = serverModel.getInstance().getUser(auth).getUserName().getValue() + "'s_Game!";
        hostUser = serverModel.getInstance().getUser(auth);
        gameStarted = false;
    }

    public String getGameID() {
        return gameID.getValue();
    }

    public void addPlayer(playerModel newPlayer) throws Exception {
        for (playerModel curPlayer : this.players) {
            if(curPlayer.getUserName().getValue().equals(newPlayer.getUserName().getValue())){
                throw new Exception("User already a player in this game");
            }
        }
        if(players.size() < 5) {
            throw new Exception("Max number of players reached!");
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

    public void startGame() throws Exception {
        if (players.size() < 2){
            throw new Exception("Insufficient number of players to start game!");
        }
        else if(players.size() > 5){
            throw new Exception("Too many players to start game!");
        }

        this.gameStarted = true;
        //TODO:Alert all players that game has begun
    }

    public List<playerModel> getPlayers() {
        return players;
    }
}
