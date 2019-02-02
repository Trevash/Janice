package server.handlers;

import com.bignerdranch.android.shared.models.*;
import com.bignerdranch.android.shared.randomIDGenerator;

public class registerHandler {
    public String register(String username, String password) throws Exception{
        serverModel.getInstance().addUser(new userModel(new usernameModel(username),
                                          new passwordModel(password),
                                          new userIDModel(randomIDGenerator.getInstance().getRandomString(16))));

        authTokenModel auth = new authTokenModel(randomIDGenerator.getInstance().getRandomString(16));

        return auth.getValue();
    }
}
