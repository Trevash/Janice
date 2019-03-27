package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.gameStates.AbstractClientGameState;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;

public class ClientActivePlayerState extends AbstractClientGameState {

    /**
     * Constructs a AbstractClientGameState object that can interact with the provided server for the
     * provided gameID.<!-- -->
     * Preconditions: server is a valid, working IServer
     * Preconditions: gameID is the gameID of the game that this state object is a part of
     *
     * @param server An Iserver, typically a serverProxy, that can be used to interact with the
     *               server's version of the game.
     * @param game the game for this state object.
     */
    public ClientActivePlayerState(IServer server, gameModel game, int destCardDeckSize) {
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
