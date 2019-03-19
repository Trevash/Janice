package com.janus.Presenter;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;
import com.janus.Communication.CreateGameTask;
import com.janus.Communication.JoinGameTask;

import java.util.List;

public class GameResultsPresenter {

    private ClientFacade facade = ClientFacade.getInstance();

    public GameResultsPresenter() {}

    public List<playerModel> getPlayers() {
        return facade.getPlayers();
    }

    //TODO: Calculate winner and longest path so Text Views can be set
    public playerModel getWinner() {
        return facade.getPlayers().get(0);
    }

    public playerModel getLongestRoute() {
        return facade.getPlayers().get(0);
    }
}
