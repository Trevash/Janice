package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

public class ServerInGameState implements IGameState {

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
        return null; // TODO implement state
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

    }

    /**
     * returns the number of cards in the destination card deck.<!-- -->
     * Precondition: the state has a valid reference to the game's destination card deck.<!-- -->
     * Postcondition: the return value contains the number of cards currently in the destination card
     * deck.<!-- -->
     *
     * @return the size of the destination card deck
     */
    @Override
    public int destinationCardDeckSize() {
        return 0;
    }

    /**
     * Returns a client-side equivalent of the current state, which has the ability to interact with
     * the server via the provided proxy.<!-- --> If the current state is a client state, then this
     * method simply returns the current state.<!-- -->
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
    public AbstractClientGameState toClientState(IServer serverProxy, gameIDModel id) {
        return null;
    }
}
