package server.handlers;

import com.bignerdranch.android.shared.resultobjects.LoginData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.models.*;

public class loginHandler {
    public Results login(String username, String password) throws Exception {
        userModel curUser = serverModel.getInstance().getUser(username);

        if (!curUser.getPassword().getValue().equals(password)) {
            throw new Exception("Password incorrect!");
        }

        authTokenModel auth = new authTokenModel();

        LoginData data = new LoginData(serverModel.getInstance().getGames(), auth);
        return new Results("Login", true, data);
    }
}
