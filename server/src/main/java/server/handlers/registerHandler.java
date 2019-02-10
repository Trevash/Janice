package server.handlers;

import com.bignerdranch.android.shared.resultobjects.LoginData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.models.*;

public class registerHandler {
    public Results register(String username, String password) throws Exception{
        serverModel.getInstance().addUser(new userModel(new usernameModel(username),
                                          new passwordModel(password),
                                          new userIDModel()));

        authTokenModel auth = new authTokenModel();

        LoginData data = new LoginData(serverModel.getInstance().getGames(), auth);
        return new Results("Register", true, data);
    }
}
