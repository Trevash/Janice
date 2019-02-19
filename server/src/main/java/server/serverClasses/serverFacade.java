package server.serverClasses;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.passwordModel;
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

public class serverFacade implements IServer {
    private static serverFacade sf = null;

    public static serverFacade getInstance(){
        if (sf == null){
            sf = new serverFacade();
        }
        return sf;
    }


    @Override
    public Results login(LoginRequest request) throws Exception {
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

    @Override
    public Results register(RegisterRequest request) throws Exception {
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

    @Override
    public Results createGame(CreateGameRequest request) throws Exception {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new Exception("Invalid Auth Token passed to createGame");
        }

        String newGameName = serverModel.getInstance().getUser(request.getAuth()).getUserName().getValue() + "'s_Game!";
        playerModel hostPlayer = new playerModel(serverModel.getInstance().getUser(request.getAuth()).getUserName(), false, true);

        gameModel newGame = new gameModel(newGameName, hostPlayer);
        serverModel.getInstance().addGame(newGame);
        return new Results("Create", true, newGame);
    }

    @Override
    public Results startGame(StartGameRequest request) throws Exception {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new Exception("Invalid Auth Token passed to startGame");
        }

        serverModel.getInstance().startGame(request);

        return new Results("Start", true, new GameListData(serverModel.getInstance().getGames()));
    }

    @Override
    public Results joinGame(JoinGameRequest request) throws Exception {
        if(!serverModel.getInstance().authTokenExists(request.getAuth())){
            throw new Exception("Invalid Auth Token passed to joinGame");
        }

        gameModel game = serverModel.getInstance().joinGame(request);

        return new Results("Join", true, game);
    }
}
