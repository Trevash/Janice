package com.bignerdranch.android.shared.gameStates;


public class ClientDrawTrainCardState extends AbstractClientGameState {

    public ClientDrawTrainCardState(AbstractClientGameState prevState) {
        super(prevState);
    }

    @Override
    public boolean canDrawTrainCards() {
        return true;
    }

    @Override
    public boolean canDrawDestCards() {
        return false;
    }

    @Override
    public boolean canDrawLocomotive() {
        return false;
    }

    @Override
    public boolean canClaimRoute() {
        return false;
    }

    @Override
    public void notifyTurnAdvancement() {
        if(!getGame().isPlayersTurn(super.getClientID())) {
            super.updateGameState(new ClientInactiveState(this));
        } else {
            //System.out.println(this.getClass().toString() + " was notified of a turn advancement: " +
            //        "check for bug, as this state should automatically change states");
            System.out.println("ERROR: draw train card state went straight to the active " +
                    "player state, which should never happen");
            super.updateGameState(new ClientActivePlayerState(this));
        }
    }


    @Override
    public void notifyTrainCardDrawn() {
        super.updateGameState(new ClientInactiveState(this));
    }
}
