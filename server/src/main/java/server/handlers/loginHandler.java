package server.handlers;

import com.bignerdranch.android.shared.Results;
import com.bignerdranch.android.shared.models.*;
import com.bignerdranch.android.shared.randomIDGenerator;

public class loginHandler {
    public Results login(String username, String password) throws Exception {
        userModel curUser = serverModel.getInstance().getUser(username);

        if (!curUser.getPassword().getValue().equals(password)) {
            throw new Exception("Password incorrect!");
        }

        authTokenModel auth = new authTokenModel(randomIDGenerator.getInstance().getRandomString(16));

        return new Results(true, auth.getValue(), "");
    }
}
