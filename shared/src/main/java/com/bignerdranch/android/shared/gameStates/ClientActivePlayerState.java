package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.gameIDModel;

public class ClientActivePlayerState extends AbstractClientGameState {


    public ClientActivePlayerState(IServer server, gameIDModel gameID) {
        super(server, gameID);
    }

    @Override
    public boolean canDrawTrainCards() {
        return true;
    }

    @Override
    public boolean canDrawDestCards() {
        return true;
    }

    @Override
    public boolean canClaimRoute() {
        return true;
    }
}
