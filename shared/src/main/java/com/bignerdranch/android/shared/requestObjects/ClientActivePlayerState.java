package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.gameStates.AbstractClientGameState;

public class ClientActivePlayerState extends AbstractClientGameState {
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
