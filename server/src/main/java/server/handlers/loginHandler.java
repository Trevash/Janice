package server.handlers;

import com.bignerdranch.android.shared.Results;
import com.bignerdranch.android.shared.models.*;

public class loginHandler {
    public Results login(String username, String password) throws Exception {
        userModel curUser = serverModel.getInstance().getUser(username);

        if (!curUser.getPassword().getValue().equals(password)) {
            throw new Exception("Password incorrect!");
        }

        authTokenModel auth = new authTokenModel();

        return new Results(true, auth.getValue(), "");
    }
}
