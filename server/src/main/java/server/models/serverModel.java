package server.models;

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

    private List<userModel> users = new ArrayList<userModel>();

    public void addUser(userModel newUser){
        this.users.add(newUser);
    }

    public boolean usernameExists(String test){
        for (userModel user : this.users) {
            if(test == user.getUserName().getValue()){
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
            if(test == user.getUserID().getValue()){
                return true;
            }
        }
        return false;
    }

    public boolean playerIDExists(String test) {
        for (userModel user : this.users) {
            for (playerIDModel playerID : user.getPlayerIDs()) {
                if (test == playerID.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public userModel getUser(String username) {
        for(userModel user : this.users) {
            if(user.getUserName().getValue() == username) {
                return user;
            }
        }
        return null;
    }
}
