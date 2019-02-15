package server.handlers;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.exceptions.GameNotFoundException;
import com.bignerdranch.android.shared.exceptions.InvalidAuthorizationException;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.passwordModel;
import server.serverModel;

import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.userIDModel;
import com.bignerdranch.android.shared.models.userModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;
import com.bignerdranch.android.shared.requestObjects.RegisterRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;

public class commandHandler extends handlerBase { //implements IServer {
    // TODO fix this - unify the commandHandler and the ServerProxy
    @Override
    public String execute(String s) {
        GenericCommand command = Serializer.getInstance().deserializeCommand(s);
        Results results = command.execute();
        String ss = Serializer.getInstance().serializeObject(results);

        if(results.isSuccess()){ return results.getJSONdata(); }
        else{ return results.getJSONdata(); }
    }

    public Results register(RegisterRequest request) throws DuplicateException {
        usernameModel newUserName = new usernameModel(request.getUsername());
        if(serverModel.getInstance().userExists(newUserName.getValue())){
            throw new DuplicateException("The username is already taken!");
        }
        passwordModel newPassword = new passwordModel(request.getPassword());
        userIDModel newUserID = new userIDModel();
        authTokenModel auth = new authTokenModel();

        userModel user = new userModel(newUserName,
                newPassword,
                newUserID,
                auth);
        serverModel.getInstance().addUser(user);

        return new Results("Register", true, user);
    }

    public Results createGame(CreateGameRequest request) throws Exception {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new InvalidAuthorizationException("Invalid Auth Token passed to createGame");
        }

        String newGameName = serverModel.getInstance().getUser(request.getAuth()).getUserName().getValue() + "'s_Game!";
        playerModel hostPlayer = new playerModel(serverModel.getInstance().getUser(request.getAuth()).getUserName(), false, true);

        gameModel newGame = new gameModel(newGameName, hostPlayer);
        serverModel.getInstance().addGame(newGame);
        return new Results("Create", true, newGame);
    }

    public Results startGame(StartGameRequest request) throws InvalidAuthorizationException, GameNotFoundException {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new InvalidAuthorizationException("Invalid Auth Token passed to startGame");
        }

        serverModel.getInstance().startGame(request);

        return new Results("Start", true, new GameListData(serverModel.getInstance().getGames()));
    }

    public Results login(LoginRequest request) throws Exception {
        userModel curUser = serverModel.getInstance().getUser(request.getUsername());

        if (!curUser.getPassword().getValue().equals(request.getPassword())) {
            throw new InvalidAuthorizationException("Password incorrect!");
            // probably not the ideal exception, but it does work
        }

        authTokenModel auth = new authTokenModel();
        curUser.setAuthToken(auth);
        return new Results("Login", true, curUser);
    }

    public Results joinGame(JoinGameRequest request)
            throws InvalidAuthorizationException, DuplicateException, GameNotFoundException {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new InvalidAuthorizationException("Invalid Auth Token passed to joinGame");
        }

        gameModel game = serverModel.getInstance().joinGame(request);

        return new Results("Join", true, game);
    }
}
