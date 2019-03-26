package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

public class ClientChooseDestCardState extends AbstractClientGameState {


    private List<DestinationCardModel> drawnCards;

    public ClientChooseDestCardState(IServer server, gameIDModel gameID, List<DestinationCardModel> drawnCards) {
        super(server, gameID);
        this.drawnCards = drawnCards;

    }

    @Override
    public boolean canDrawTrainCards() {
        return false;
    }

    @Override
    public boolean canDrawDestCards() {
        return true; // simply reveal the cards already drawn
    }

    @Override
    public boolean canClaimRoute() {
        return false;
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        return drawnCards; // Don't want to draw new cards here
    }
}
