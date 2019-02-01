package server.handlers;

import server.models.*;

public class loginHandler {
    public String login(String username, String password) throws Exception {
        if(!serverModel.getInstance().usernameExists(username)){
            return "Username does not exist!";
        }

        userModel curUser = serverModel.getInstance().getUser(username);

        if(curUser.getPassword().getValue() != password){
            return "Password incorrect!";
        }

        return "Login Successful!";
    }
}
