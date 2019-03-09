package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.proxy.DestinationCardDeckProxy;

import java.util.List;

public class ClientInitialGameState implements IGameState {

    IDestinationCardDeck destinationCardDeck = new DestinationCardDeckProxy();

    public ClientInitialGameState() {

    }

    public List<DestinationCardModel> drawDestinationCards() {
        return destinationCardDeck.drawDestinationCards();
    }

    public void returnDestinationCards(List<DestinationCardModel> destinationCards) {

    }

}
