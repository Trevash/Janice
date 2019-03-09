package com.bignerdranch.android.shared;

import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;
import com.bignerdranch.android.shared.requestObjects.RegisterRequest;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.resultobjects.Results;

public interface IServer {
    // TODO update the Exceptions thrown to be more specific

    public Results login(LoginRequest request) throws Exception;
    public Results register(RegisterRequest request) throws Exception;
    public Results createGame(CreateGameRequest request) throws Exception;
    public Results startGame(StartGameRequest request) throws Exception;
    public Results joinGame(JoinGameRequest request) throws Exception;
    public Results updateChatbox(UpdateChatboxRequest request) throws Exception;

    public Results drawDestinationCards(DrawDestinationCardsRequest request);
    public Results returnDestinationCard(ReturnDestinationCardsRequest request);
}
