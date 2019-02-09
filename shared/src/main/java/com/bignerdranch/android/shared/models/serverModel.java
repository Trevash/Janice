package com.bignerdranch.android.shared.models;

import java.util.ArrayList;
import java.util.List;

public class serverModel {
    private static serverModel sm = null;

    public static serverModel getInstance() {
        if(sm == null){
            sm = new serverModel();
        }
        return sm;
    }

    private List<userModel> users = new ArrayList<>();

    private List<authTokenModel> authTokens = new ArrayList<>();

    private List<gameModel> games = new ArrayList<>();

    public void addUser(userModel newUser){
        this.users.add(newUser);
    }

    public boolean userExists(String test){
        for (userModel user : this.users) {
            if(test.equals(user.getUserName().getValue())){
                return true;
            }
        }
        return false;
    }

    public boolean userExists(userModel test){
        for (userModel user : this.users) {
            if(test == user){
                return true;
            }
        }
        return false;
    }

    public boolean userExists(userIDModel test){
        for (userModel user : this.users) {
            if(test == user.getUserID()){
                return true;
            }
        }
        return false;
    }

    public boolean userIDExists(String test) {
        for (userModel user : this.users) {
            if(test.equals(user.getUserID().getValue())){
                return true;
            }
        }
        return false;
    }

    public boolean playerIDExists(String test) {
        for (userModel user : this.users) {
            for (playerIDModel playerID : user.getPlayerIDs()) {
                if (test.equals(playerID.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public userModel getUser(String username) throws Exception{
        for(userModel user : this.users) {
            if(user.getUserName().getValue().equals(username)) {
                return user;
            }
        }
        throw new Exception("User not found!");
    }

    public boolean authTokenExists(String newValue) {
        for (authTokenModel auth : this.authTokens) {
            if(auth.getValue().equals(newValue))
                return true;
        }
        return false;
    }

    public boolean gameIDExists(String newValue) {
        for(gameModel game : this.games){
            if(game.getGameID().equals(newValue)){
                return true;
            }
        }
        return false;
    }

    public List<gameModel> getGames() {
        return games;
    }

    public gameModel getGameByID(gameIDModel id) {
        for(gameModel game: this.games){
            if(id.getValue().equals(game.getGameID())){
                return game;
            }
        }
        return null;
    }

    public void addGame(gameModel newGame) {
        games.add(newGame);
    }
}
