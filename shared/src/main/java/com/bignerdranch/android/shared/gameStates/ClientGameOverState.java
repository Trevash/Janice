package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;

public class ClientGameOverState extends AbstractClientGameState {


    public ClientGameOverState(IServer server, gameModel game, int destCardDeckSize) {
        super(server, game, destCardDeckSize);
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
