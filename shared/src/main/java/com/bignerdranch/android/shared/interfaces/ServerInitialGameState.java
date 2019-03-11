package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.models.DestinationCardModel;

import java.util.List;

public class ServerInitialGameState implements IGameState {

    private IDestinationCardDeck destinationCardDeck = new DestinationCardDeck();


    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        return destinationCardDeck.drawDestinationCards();
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> destinationCards) {
        //for(int i = 0; i < destinationCards.size(); i++) {
        //    destinationCardDeck.returnDestinationCard(destinationCards.get(i));
        //}
        destinationCardDeck.returnDestinationCards(destinationCards);
    }


    public int size() {
        return destinationCardDeck.size();
    }
}
