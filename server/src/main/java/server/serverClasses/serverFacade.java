package server.serverClasses;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.exceptions.GameNotFoundException;
import com.bignerdranch.android.shared.exceptions.InvalidAuthorizationException;
import com.bignerdranch.android.shared.exceptions.UserNotFoundException;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.chatboxModel;
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
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.resultobjects.GameListData;
import com.bignerdranch.android.shared.resultobjects.Results;

public class serverFacade implements IServer {
    private static serverFacade sf = null;

    public static serverFacade getInstance() {
        if (sf == null) {
            sf = new serverFacade();
        }
        return sf;
    }

    @Override
    public Results login(LoginRequest request)
            throws UserNotFoundException, InvalidAuthorizationException {
        userModel curUser = serverModel.getInstance().getUser(request.getUsername());

        if (!curUser.getPassword().getValue().equals(request.getPassword())) {
            throw new InvalidAuthorizationException("Password incorrect!");
            // probably not the ideal exception, but it does work
        }

        authTokenModel auth = new authTokenModel();
        curUser.setAuthToken(auth);
        return new Results("Login", true, curUser);
    }

    @Override
    public Results register(RegisterRequest request) throws DuplicateException {
        usernameModel newUserName = new usernameModel(request.getUsername());
        if (serverModel.getInstance().userExists(newUserName.getValue())) {
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
    public Results createGame(CreateGameRequest request) throws InvalidAuthorizationException,
            UserNotFoundException {
        if (!serverModel.getInstance().authTokenExists(request.getAuth())) {
            throw new InvalidAuthorizationException("Invalid Auth Token passed to createGame");
        }

        String newGameName = serverModel.getInstance().getUser(request.getAuth()).getUserName().getValue() + "'s_Game!";
        playerModel hostPlayer = new playerModel(serverModel.getInstance().getUser(request.getAuth()).getUserName(), false, true);

        gameModel newGame = new gameModel(newGameName, hostPlayer);
        serverModel.getInstance().addGame(newGame);
        return new Results("Create", true, newGame);
    }

    @Override
    public Results startGame(StartGameRequest request) throws InvalidAuthorizationException,
            GameNotFoundException {
        if (!serverModel.getInstance().authTokenExists(request.getAuth())) {
            throw new InvalidAuthorizationException("Invalid Auth Token passed to startGame");
        }

        serverModel.getInstance().startGame(request);

        return new Results("Start", true, new GameListData(serverModel.getInstance().getGames()));
    }

    @Override
    public Results joinGame(JoinGameRequest request) throws InvalidAuthorizationException,
            DuplicateException, GameNotFoundException {
        if (!serverModel.getInstance().authTokenExists(request.getAuth())) {
            throw new InvalidAuthorizationException("Invalid Auth Token passed to joinGame");
        }

        gameModel game = serverModel.getInstance().joinGame(request);

        return new Results("Join", true, game);
    }

	@Override
	public Results updateChatbox(UpdateChatboxRequest request) throws InvalidAuthorizationException, 
			GameNotFoundException, DuplicateException {
        if (!serverModel.getInstance().authTokenExists(request.getAuth())) {
            throw new InvalidAuthorizationException("Invalid Auth Token passed to updateChatBox");
        }
        
        chatboxModel chatbox = serverModel.getInstance().updateChatbox(request);
        return new Results("UpdateChat", true, chatbox);
	}
}
