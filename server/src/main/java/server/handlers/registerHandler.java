package server.handlers;

import com.bignerdranch.android.shared.Results;
import com.bignerdranch.android.shared.models.*;

public class registerHandler {
    public Results register(String username, String password) throws Exception{
        serverModel.getInstance().addUser(new userModel(new usernameModel(username),
                                          new passwordModel(password),
                                          new userIDModel()));

        authTokenModel auth = new authTokenModel();

        return new Results("register", true, auth.getValue(), "");
    }
}
