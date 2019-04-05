package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.models.DestinationCardDeck;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.TrainCardBank;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;

import java.util.List;

public abstract class AbstractServerGameState extends AbstractGameState {

    // TODO still possible to peak at the decks (destCard and trainCard) when the game is given to the clients (when game starts)
    private IDestinationCardDeck destinationCardDeck;
    private TrainCardBank trainCardBank;

    public AbstractServerGameState(gameModel game) {
        super(game);
        trainCardBank = new TrainCardBank();
        destinationCardDeck = new DestinationCardDeck();
    }

    public AbstractServerGameState(AbstractServerGameState prevState) {
        super(prevState);
        this.destinationCardDeck = prevState.destinationCardDeck;
        this.trainCardBank = prevState.trainCardBank;
        if (destinationCardDeck == null) {
            throw new IllegalArgumentException("The destination card deck cannot be null");
        }
        if (trainCardBank == null) {
            throw new IllegalArgumentException("The train card bank cannot be null");
        }
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
    public List<DestinationCardModel> drawDestinationCards(playerIDModel clientID) {
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
     * Postcondition: the turn counter is advanced, indicating that the current player's turn is over
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
    public int getDestinationCardDeckSize() {
        return destinationCardDeck.size();
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

    @Override
    public int getTrainCardDiscardSize() {
        return trainCardBank.discardSize();
    }

    public List<trainCardModel> getTrainCardDiscardPile() {
        return trainCardBank.getTrainCardDiscard();
    }

    @Override
    public int getTrainCardDeckSize() {
        return trainCardBank.deckSize();
    }

    @Override
    public List<trainCardModel> getFaceUpTrainCards() {
        return trainCardBank.getFaceUpTrainCards();
    }

    @Override
    public void discard(List<trainCardModel> discardedCards) {
        trainCardBank.addToDiscard(discardedCards);
    }

    public void onRouteClaimed(playerIDModel claimer) {
        super.advanceTurn();
    }
}
