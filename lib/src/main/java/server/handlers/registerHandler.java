package server.handlers;

import server.models.*;

public class registerHandler {
    public String register(String username, String password){
        if(serverModel.getInstance().usernameExists(username)){
            return "Username already in use!";
        }

        try {
            serverModel.getInstance().addUser(new userModel(new usernameModel(username),
                                              new passwordModel(password),
                                              new userIDModel(randomIDGenerator.getInstance().getRandomString(16))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }

        return "Register Successful!";
    }
}
