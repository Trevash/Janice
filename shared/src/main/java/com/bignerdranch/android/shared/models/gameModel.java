package com.bignerdranch.android.shared.models;

import java.util.ArrayList;
import java.util.List;

public class gameModel {
    private gameIDModel gameID;
    private List<playerIDModel> players = new ArrayList<>();

    gameModel(){
        gameID = new gameIDModel();
    }

    public String getGameID() {
        return gameID.getValue();
    }

    public void addPlayer(playerIDModel newPlayer){
        //TODO: Resolve if this should be playerModels or playerIDModels
        players.add(newPlayer);
    }

    public void removePlayer(String oldPlayer){
        for (playerIDModel curPlayer : this.players) {
            if(curPlayer.getValue().equals(oldPlayer)){
                this.players.remove(curPlayer);
            }
        }
    }
}
