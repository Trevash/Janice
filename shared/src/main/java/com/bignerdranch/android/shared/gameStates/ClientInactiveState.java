package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

/**
 * state representing the state that it is not the client's turn
 */
public class ClientInactiveState extends AbstractClientGameState {

    public ClientInactiveState(IServer server, gameIDModel gameID) {
        super(server, gameID);
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

    /**
     * Returns all of the destination cards in the provided list to the game's destination card deck,
     * as specified in the rules of the game.<!-- -->
     * Precondition: the state has a valid reference to the game's destination card deck.<!-- -->
     * Precondition: the destination cards in the list were cards previously drawn by a player
     * Precondition: the destination cards that are being returned have been removed from the player
     * Postcondition: the returned cards are returned to the bottom of the destination card deck
     * Postcondition: the size of the destination card deck is increased by the number of returned
     * destination cards
     *
     * @param destinationCards the cards that are to be returned
     */
    @Override
    public void returnDestinationCards(List<DestinationCardModel> destinationCards) {
        throw new IllegalStateException("Cannot return destination cards outside of your turn");
    }

}
