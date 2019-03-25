package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.proxy.DestinationCardDeckProxy;

import java.util.List;

public abstract class AbstractClientGameState implements IGameState {

    /**
     * A proxy deck that has the ability to send messages to the server, which is used to interact
     * with the actual Destination Card deck on the server.
     */
    private DestinationCardDeckProxy destinationCardDeck;

    /**
     * Constructs a AbstractClientGameState object that can interact with the provided server for the
     * provided gameID.<!-- -->
     * Preconditions: server is a valid, working IServer
     * Preconditions: gameID is the gameID of the game that this state object is a part of
     *
     * @param server An Iserver, typically a serverProxy, that can be used to interact with the
     *               server's version of the game.
     * @param gameID the game ID of the game for this state object.
     */
    public AbstractClientGameState(IServer server, gameIDModel gameID) {
        destinationCardDeck = new DestinationCardDeckProxy(server, gameID);
    }


    public abstract boolean canDrawTrainCards();
    public abstract boolean canDrawDestCards();
    public abstract boolean canClaimRoute();

    protected DestinationCardDeckProxy getDestinationCardDeck() {
        return destinationCardDeck;
    }

    /**
     * Returns a client-side equivalent of the current state, which has the ability to interact with
     * the server via the provided proxy.<!-- --> As this state is a client state, this method
     * simply returns the current state.<!-- -->
     * Precondition: IServer != null
     * Precondition: gameIDModel is the game id of the game for this state.<!-- -->
     * Postcondition: the returned IGameState is the client's equivalent state of the implementing state.<!-- -->
     *
     * @param serverProxy a reference to the server (in this case, a server proxy) so that the game's
     *                    state can interact with the server
     * @param id          the id of the game that this state is associated with
     * @return the client version of this game state (itself if a client version)
     */
    @Override
    public IGameState toClientState(IServer serverProxy, gameIDModel id) { // TODO add in the player/userID?
        return this;
    }


    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        if(canDrawDestCards()) {
            return destinationCardDeck.drawDestinationCards();
            //return null; // TODO move functionality to here
        } else {
            throw new IllegalStateException("Cannot draw destination cards in this state");
        }
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> destinationCards) {
        if(canDrawDestCards()) {
            destinationCardDeck.returnDestinationCards(destinationCards);
        } else {
            throw new IllegalStateException("Cannot draw/return destination cards in this state");
        }
        // TODO move implementation into Abstract Client Game State
    }

    @Override
    public int destinationCardDeckSize() {
        return destinationCardDeck.size();
    }
}
