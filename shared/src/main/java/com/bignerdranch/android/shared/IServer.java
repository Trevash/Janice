package com.bignerdranch.android.shared;

import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.resultobjects.Results;

public interface IServer {
    // TODO update the Exceptions thrown to be more specific

    public Results login(String username, String password) throws Exception;
    public Results register(String username, String password) throws Exception;
    public Results createGame(authTokenModel auth) throws Exception;
    public Results startGame(gameModel game, authTokenModel auth) throws Exception;
    public Results joinGame(JoinGameRequest request) throws Exception;

}
