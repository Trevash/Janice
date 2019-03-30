package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;

import java.util.List;

/**
 * state representing the state that it is not the client's turn
 */
public class ClientInactiveState extends AbstractClientGameState {

    public ClientInactiveState(AbstractClientGameState state) {
        super(state);
    }

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
    public ClientInactiveState(IServer server, gameModel game, int destCardDeckSize, playerIDModel clientID) {
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

    // TODO rewrite javadocs
    /**
     * Draws up to 3 destination cards, as specified by the rules of the game.<!-- -->
     * Precondition: the state has a valid reference to the game's destination card deck.<!-- -->
     * Postcondition: the returned list contains all of the destination cards removed from the deck.<!-- -->
     * Postcondition: the deck's size is reduced by the number of destination cards drawn.<!-- -->
     *
     * @return a list containing all of the drawn destination cards.
     */
    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        throw new IllegalStateException("Cannot draw destination cards outside of your turn");
    }

    @Override
    public void notifyTurnAdvancement() {
        if(getGame().isPlayersTurn(super.getClientID())) {
            System.out.println(this.getClass().toString() + " was notified of a turn advancement: " +
                    "check for bug, as this state should automatically change states");
            super.updateGameState(new ClientActivePlayerState(this));
        }
    }
}
