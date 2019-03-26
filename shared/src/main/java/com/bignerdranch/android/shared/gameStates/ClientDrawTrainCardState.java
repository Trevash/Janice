package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.gameIDModel;

public class ClientDrawTrainCardState extends AbstractClientGameState {

    /**
     * Constructs a ClientIntialGameState object that can interact with the provided server for the
     * provided gameID.<!-- -->
     * Preconditions: server is a valid, working IServer
     * Preconditions: gameID is the gameID of the game that this state object is a part of
     *
     * @param server An Iserver, typically a serverProxy, that can be used to interact with the
     *               server's version of the game.
     * @param gameID the game ID of the game for this state object.
     */
    public ClientDrawTrainCardState(IServer server, gameIDModel gameID, int destCardDeckSize) {
        super(server, gameID, destCardDeckSize);
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
    public boolean canClaimRoute() {
        return false;
    }
}
