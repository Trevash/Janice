package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;

import java.util.ArrayList;
import java.util.List;

public class serverModel {
    private static serverModel sm = null;

    private List<userModel> users = new ArrayList<>();

    private List<gameModel> games = new ArrayList<>();

    public static serverModel getInstance() {
        if(sm == null){
            sm = new serverModel();
        }
        return sm;
    }

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

    public userModel getUser(authTokenModel auth) throws Exception{
        for(userModel user : this.users) {
            if(user.getAuthToken().getValue().equals(auth.getValue())) {
                return user;
            }
        }
        throw new Exception("User not found!");
    }

    public boolean authTokenExists(authTokenModel auth) {
        for (userModel curUser : this.users) {
            if(curUser.getAuthToken().getValue().equals(auth.getValue()))
                return true;
        }
        return false;
    }

    public boolean authTokenExists(String newValue) {
        for (userModel curUser : this.users) {
            if(curUser.getAuthToken().getValue().equals(newValue))
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

    public gameModel joinGame(JoinGameRequest request) throws Exception {
        for (gameModel curGame : this.games) {
            if(curGame.getGameID().getValue().equals(request.getModel().getGameID().getValue())) {
                curGame.addPlayer(this.makeNewPlayer(this.getUserByAuth(request.getAuth())));
                return curGame;
            }
        }
        throw new Exception("Join game failed, game not found");
    }

    private playerModel makeNewPlayer(userModel userByAuth) {
        return new playerModel(userByAuth.getUserName(), false, false);
    }

    private userModel getUserByAuth(authTokenModel auth) throws Exception {
        for (userModel curUser : this.users) {
            if(curUser.getAuthToken().getValue().equals(auth.getValue()))
                return curUser;
        }
        throw new Exception("User not found by auth token to find game!");
    }

    public void startGame(StartGameRequest request) throws Exception {
        for (gameModel curGame : this.games) {
            if(curGame.getGameID().equals(request.getModel().getGameID())) {
                curGame.startGame();
            }
        }
        throw new Exception("Game not found to start!");
    }
}
