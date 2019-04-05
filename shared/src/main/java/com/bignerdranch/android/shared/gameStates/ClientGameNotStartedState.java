package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;

public class ClientGameNotStartedState extends AbstractClientGameState {
    /**
     * Constructs a AbstractClientGameState object that can interact with the provided server for the
     * provided gameID.<!-- -->
     * Preconditions: server is a valid, working IServer
     * Preconditions: gameID is the gameID of the game that this state object is a part of
     *
     * @param server           An Iserver, typically a serverProxy, that can be used to interact with the
     *                         server's version of the game.
     * @param game             the game for this state object.
     * @param destCardDeckSize the number of cards in the destination card deck
     * @param clientID
     */
    public ClientGameNotStartedState(IServer server, gameModel game, int destCardDeckSize, playerIDModel clientID) {
        //super(server, game, serverState.getDestinationCardDeckSize(), game.getPlayers().get(playerNum).getId());
        super(server, game, destCardDeckSize, clientID);
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

    /**
     * a method that notifies the client game state that it needs to check that its state is valid
     * based on the turn
     */
    @Override
    public void notifyTurnAdvancement() {
        //throw new IllegalStateException() // shouldn't ever get called in this state
    }
}
