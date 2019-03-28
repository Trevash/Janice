package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.models.trainCardModel;

import java.util.List;

public interface ITrainCardBank { // TODO integrate

    public trainCardModel drawTrainCardFromDeck();
    public trainCardModel drawFaceUpTrainCard(int i);

    /**
     * method that returns a group of cards that were removed from a player's hand or from the face-
     * up train cards
     *
     * @param discardedCards cards that have been removed from a player's hands
     */
    public void addToDiscard(List<trainCardModel> discardedCards);

    public int deckSize();
    public int discardSize();
    public List<trainCardModel> getFaceUpTrainCards();

}
