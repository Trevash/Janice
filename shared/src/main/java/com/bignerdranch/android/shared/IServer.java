package com.bignerdranch.android.shared;

import com.bignerdranch.android.shared.models.gameIDModel;
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

public interface IServer {
    // TODO update the Exceptions thrown to be more specific

    Results login(LoginRequest request) throws Exception;
    Results register(RegisterRequest request) throws Exception;
    Results createGame(CreateGameRequest request) throws Exception;
    Results startGame(StartGameRequest request) throws Exception;
    Results joinGame(JoinGameRequest request) throws Exception;
    Results claimRoute(ClaimRouteRequest request) throws Exception;
    Results updateChatbox(UpdateChatboxRequest request) throws Exception;

    Results drawDestinationCards(DrawDestinationCardsRequest request);
    Results returnDestinationCard(ReturnDestinationCardsRequest request);
}
