package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IGameState;

public abstract class AbstractClientGameState implements IGameState {

    public abstract boolean canDrawTrainCards();
    public abstract boolean canDrawDestCards();
    public abstract boolean canClaimRoute();
}
