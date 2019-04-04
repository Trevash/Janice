package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.gameStates.AbstractClientGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;

import java.util.List;

/**
 * An interface for game states, which is to implement any code of the gameModel that changes
 * functionality based on the state or location of the game.<!-- --> This includes things such as
 * drawing destination cards, which are drawn from a deck stored on the server.<!-- -->
 */
public interface IGameState {

    /**
     * Draws up to 3 destination cards, as specified by the rules of the game.<!-- -->
     * Precondition: the state has a valid reference to the game's destination card deck.<!-- -->
     * Postcondition: the returned list contains all of the destination cards removed from the deck.<!-- -->
     * Postcondition: the deck's size is reduced by the number of destination cards drawn.<!-- -->
     * @return a list containing all of the drawn destination cards.
     */
    List<DestinationCardModel> drawDestinationCards(playerIDModel clientID);

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
     * @param rejectedCards the cards that are to be returned
     */
    void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards);

    /**
     * returns the number of cards in the destination card deck.<!-- -->
     * Precondition: the state has a valid reference to the game's destination card deck.<!-- -->
     * Postcondition: the return value contains the number of cards currently in the destination card
     * deck.<!-- -->
     * @return the size of the destination card deck
     */
    int getDestinationCardDeckSize();

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
     * @param game
     * @param playerNum
     * @return the client version of this game state (itself if a client version)
     */
    AbstractClientGameState toClientState(IServer serverProxy, gameModel game, int playerNum);

    trainCardModel drawTrainCardFromDeck();

    /**
     *
     * @param cardLocation the number representing the card's location in the face up "pile", which
     *                     should be a number from 0 through 4
     * @return the drawn card
     */
    trainCardModel drawFaceUpTrainCard(int cardLocation);

    int getTrainCardDiscardSize();

    List<trainCardModel> getTrainCardDiscardPile();

    void discard(List<trainCardModel> discardedCards);

    int getTrainCardDeckSize();

    List<trainCardModel> getFaceUpTrainCards();

    //int minKeepDestCards(); // or some such method
    // TODO do we want a method that gives out the number of returnable destination cards?

}
