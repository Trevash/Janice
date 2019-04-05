package com.bignerdranch.android.shared.gameStates;

public class ClientGameOverState extends AbstractClientGameState {

    public ClientGameOverState(AbstractClientGameState prevState) {
        super(prevState);
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

    @Override
    public boolean canDrawLocomotive() {
        return false;
    }

    @Override
    public void notifyTurnAdvancement() {
        throw new IllegalStateException("The turn counter should not be changing once the game is " +
                "over - check for bug");
    }
}
