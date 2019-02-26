package server.handlers;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.exceptions.GameNotFoundException;
import com.bignerdranch.android.shared.exceptions.InvalidAuthorizationException;
import com.bignerdranch.android.shared.exceptions.UserNotFoundException;

import server.serverClasses.serverFacade;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;
import com.bignerdranch.android.shared.requestObjects.RegisterRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;

public class commandHandler extends handlerBase implements IServer {
    @Override
    public String execute(String s) {
        GenericCommand command = Serializer.getInstance().deserializeCommand(s);
        Results results = command.execute();
        String ss = Serializer.getInstance().serializeObject(results);

        if (results.isSuccess()) {
            return results.getJSONdata();
        } else {
            return results.getJSONdata();
        }
    }

    public Results register(RegisterRequest request)
            throws DuplicateException {
        return serverFacade.getInstance().register(request);
    }

    public Results createGame(CreateGameRequest request)
            throws InvalidAuthorizationException, UserNotFoundException {
        return serverFacade.getInstance().createGame(request);
    }

    public Results startGame(StartGameRequest request)
            throws InvalidAuthorizationException, GameNotFoundException {
        return serverFacade.getInstance().startGame(request);
    }

    public Results login(LoginRequest request)
            throws InvalidAuthorizationException, UserNotFoundException {
        return serverFacade.getInstance().login(request);
    }

    public Results joinGame(JoinGameRequest request) throws DuplicateException,
            GameNotFoundException, InvalidAuthorizationException {
        return serverFacade.getInstance().joinGame(request);
    }

	@Override
	public Results updateChatbox(UpdateChatboxRequest request) throws DuplicateException,
			GameNotFoundException, InvalidAuthorizationException {
		return serverFacade.getInstance().updateChatbox(request);
	}
}
