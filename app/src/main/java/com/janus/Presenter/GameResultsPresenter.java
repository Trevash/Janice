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

    public List<Integer> getFinalStats() {
        return facade.getFinalStats();
    }

    public List<int[]> getStats() {
        return facade.getStats();
    }
}
