package com.bignerdranch.android.shared.models;

import java.util.ArrayList;
import java.util.List;

public class gameModel {
    private gameIDModel gameID;
    private List<playerModel> players = new ArrayList<>();

    gameModel(){
        gameID = new gameIDModel();
    }

    public String getGameID() {
        return gameID.getValue();
    }

    public List<playerModel> getPlayers() {
        return players;
    }

    public void addPlayer(playerModel newPlayer){
        //TODO: Resolve if this should be playerModels or playerIDModels
        players.add(newPlayer);
    }

    public void removePlayer(String oldPlayer){
        for (playerModel curPlayer : this.players) {
            if(curPlayer.getId().getValue().equals(oldPlayer)){
                this.players.remove(curPlayer);
            }
        }
    }
}
