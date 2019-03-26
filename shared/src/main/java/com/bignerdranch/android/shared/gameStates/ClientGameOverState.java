package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.gameIDModel;

public class ClientGameOverState extends AbstractClientGameState {


    public ClientGameOverState(IServer server, gameIDModel gameID, int destCardDeckSize) {
        super(server, gameID, destCardDeckSize);
    }

    @Override
    public boolean canDrawTrainCards() {
        return false;
    }

    @Override
    public boolean canDrawDestCards() {
        return false;
    }

    @Override
    public boolean canClaimRoute() {
        return false;
    }
}
