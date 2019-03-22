package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.proxy.DestinationCardDeckProxy;

import java.util.List;

/**
 * The state representing the client side at the beginning of the game.<!-- --> See IGameState for
 * more specific information
 */
public class ClientInitialGameState extends AbstractClientGameState implements IGameState {
    // note: some of the information for this class is written in IGameState, specifically the
    // overridden methods

    /**
     * A proxy deck that has the ability to send messages to the server, which is used to interact
     * with the actual Destination Card deck on the server.
     */
    private DestinationCardDeckProxy destinationCardDeck;

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
    public ClientInitialGameState(IServer server, gameIDModel gameID) {
        destinationCardDeck = new DestinationCardDeckProxy(server, gameID);
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        return destinationCardDeck.drawDestinationCards();
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> destinationCards) {
        destinationCardDeck.returnDestinationCards(destinationCards);
    }

    @Override
    public int destinationCardDeckSize() {
        return destinationCardDeck.size();
    }

    @Override
    public IGameState toClientState(IServer server, gameIDModel id) {
        return this;
    }

    @Override
    public boolean canDrawTrainCards() {
        return false;
    }

    @Override
    public boolean canDrawDestCards() {
        // TODO
        return true;
    }

    @Override
    public boolean canClaimRoute() {
        return false;
    }
}
