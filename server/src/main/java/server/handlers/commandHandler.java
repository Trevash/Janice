package server.handlers;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.exceptions.GameNotFoundException;
import com.bignerdranch.android.shared.exceptions.InvalidAuthorizationException;
import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.exceptions.RouteNotFoundException;
import com.bignerdranch.android.shared.exceptions.UserNotFoundException;

import server.serverClasses.ServerCommunicator;
import server.serverClasses.serverFacade;
import server.serverClasses.serverModel;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;
import com.bignerdranch.android.shared.requestObjects.RegisterRequest;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
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

	public Results updateChatbox(UpdateChatboxRequest request) throws
			GameNotFoundException, InvalidAuthorizationException {
		return serverFacade.getInstance().updateChatbox(request);
	}
	
	public Results testSocket(UpdateChatboxRequest test) {
		return new Results("test", true, "It worked!");
		
	}

	public Results claimRoute(ClaimRouteRequest request) throws InvalidAuthorizationException, RouteNotFoundException, RouteAlreadyClaimedException {
        return serverFacade.getInstance().claimRoute(request);
    }

    public Results drawDestinationCards(DrawDestinationCardsRequest request) {
        return serverFacade.getInstance().drawDestinationCards(request);
    }

    public Results returnDestinationCard(ReturnDestinationCardsRequest request) {
        return serverFacade.getInstance().returnDestinationCard(request);
    }
}
