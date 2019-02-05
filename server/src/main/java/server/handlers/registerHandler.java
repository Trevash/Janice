package server.handlers;

import com.bignerdranch.android.shared.Results;
import com.bignerdranch.android.shared.models.*;
import com.bignerdranch.android.shared.randomIDGenerator;

public class registerHandler {
    public Results register(String username, String password) throws Exception{
        serverModel.getInstance().addUser(new userModel(new usernameModel(username),
                                          new passwordModel(password),
                                          new userIDModel(randomIDGenerator.getInstance().getRandomString(16))));

        authTokenModel auth = new authTokenModel(randomIDGenerator.getInstance().getRandomString(16));

        return new Results(true, auth.getValue(), "");
    }
}
