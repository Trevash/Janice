package com.bignerdranch.android.shared.models;

import java.util.ArrayList;
import java.util.List;

public class userModel {
    /*
    userModel represents a row of data in the user table of the database.
    Each private variable is a difference collumn
     */

    private usernameModel userName;
    private passwordModel password;
    private userIDModel userID;
    private List<playerIDModel> playerIDs = new ArrayList<>();
    private authTokenModel authToken;

    public userModel(usernameModel newUserName,
                     passwordModel newPassword,
                     userIDModel newUserID, authTokenModel authTokenModel){
        userName = newUserName;
        password = newPassword;
        userID = newUserID;
    }

    public usernameModel getUserName() {
        return userName;
    }

    public void setUsername(usernameModel userName) {
        this.userName = userName;
    }

    public passwordModel getPassword() {
        return password;
    }

    public void setPassword(passwordModel password) {
        this.password = password;
    }

    public userIDModel getUserID() {
        return userID;
    }

    public void setUserID(userIDModel userID) {
        this.userID = userID;
    }

    public List<playerIDModel> getPlayerIDs() {
        return playerIDs;
    }

    public void addPlayerID(playerIDModel newPlayerID) {
        this.playerIDs.add(newPlayerID);
    }

    public void removePlayerID(playerIDModel test) {
        this.playerIDs.remove(test);
    }

    public authTokenModel getAuthToken() {
        return authToken;
    }

    public void setAuthToken(authTokenModel authToken) {
        this.authToken = authToken;
    }
}