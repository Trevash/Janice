package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.TrainCardBank;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.bignerdranch.android.shared.requestObjects.ClientActivePlayerState;

import java.util.List;

public class ServerInGameState extends AbstractGameState implements IGameState {

    private IDestinationCardDeck destinationCardDeck;
    private TrainCardBank trainCardBank;

    public ServerInGameState(AbstractGameState prevState, IDestinationCardDeck destinationCardDeck, TrainCardBank bank) {
        super(prevState);
        if(destinationCardDeck == null) {
            throw new IllegalArgumentException("The destination card deck cannot be null");
        }
        this.destinationCardDeck = destinationCardDeck;
        this.trainCardBank = bank;
    }

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
        return destinationCardDeck.drawDestinationCards();
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
     * @param selectedCards
     * @param rejectedCards
     */
    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        destinationCardDeck.returnDestinationCards(selectedCards, rejectedCards);
        super.advanceTurn();
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
        return destinationCardDeck.size();
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
     * @param game  the game that this state is associated with
     * @param playerNum
     * @return the client version of this game state (itself if a client version)
     */
    @Override
    public AbstractClientGameState toClientState(IServer serverProxy, gameModel game, int playerNum) {
        if(game.isPlayersTurn(playerNum)) {
            // active player state
            return new ClientActivePlayerState(serverProxy, game, destinationCardDeckSize());
        } else {
            return new ClientInactiveState(serverProxy, game, destinationCardDeckSize());
        }
    }

    @Override
    public trainCardModel drawTrainCardFromDeck() {
        return trainCardBank.drawTrainCardFromDeck();
    }

    /**
     * @param cardLocation the number representing the card's location in the face up "pile", which
     *                     should be a number from 0 through 4
     * @return the drawn card
     */
    @Override
    public trainCardModel drawFaceUpTrainCard(int cardLocation) {
        return trainCardBank.drawFaceUpTrainCard(cardLocation);
    }
}
