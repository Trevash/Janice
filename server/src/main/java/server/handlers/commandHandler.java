package server.handlers;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.passwordModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.serverModel;
import com.bignerdranch.android.shared.models.userIDModel;
import com.bignerdranch.android.shared.models.userModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.bignerdranch.android.shared.resultobjects.LoginData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;

public class commandHandler extends handlerBase {
    @Override
    public String execute(String s) {
        GenericCommand command = Serializer.getInstance().deserializeCommand(s);
        Results results = command.execute();
        String ss = Serializer.getInstance().serializeObject(results);

        if(results.isSuccess()){ return (String) results.getData(); }
        else{ return (String) results.getData(); }
    }

    public Results register(String username, String password) throws Exception{
        serverModel.getInstance().addUser(new userModel(new usernameModel(username),
                new passwordModel(password),
                new userIDModel()));

        authTokenModel auth = new authTokenModel();

        LoginData data = new LoginData(new GameListData(), auth);

        return new Results("Register", true, data);
    }

    public Results login(String username, String password) throws Exception {
        userModel curUser = serverModel.getInstance().getUser(username);

        if (!curUser.getPassword().getValue().equals(password)) {
            throw new Exception("Password incorrect!");
        }

        authTokenModel auth = new authTokenModel();

        LoginData data = new LoginData(new GameListData(), auth);
        return new Results("Login", true, data);
    }

    public Results createGame(CreateGameRequest request) throws Exception {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new Exception("Invalid Auth Token passed to createGame");
        }

        serverModel.getInstance().addGame(new gameModel(request.getAuth()));
        return new Results("Create", true, new GameListData());
    }

    public Results joinGame(JoinGameRequest request) throws Exception {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new Exception("Invalid Auth Token passed to joinGame");
        }

        serverModel.getInstance().joinGame(request);

        return new Results("Join", true, new GameListData());
    }

    public Results startGame(StartGameRequest request) throws Exception {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new Exception("Invalid Auth Token passed to startGame");
        }

        serverModel.getInstance().startGame(request);

        return new Results("Start", true, new GameListData());
    }
}
