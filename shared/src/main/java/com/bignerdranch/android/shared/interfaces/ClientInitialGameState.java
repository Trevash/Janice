package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.proxy.DestinationCardDeckProxy;

import java.util.List;

public class ClientInitialGameState implements IGameState {

    IDestinationCardDeck destinationCardDeck;// = new DestinationCardDeckProxy();

    public ClientInitialGameState(IServer server, gameIDModel gameID) {
        destinationCardDeck = new DestinationCardDeckProxy(server, gameID);
    }

    public List<DestinationCardModel> drawDestinationCards() {
        return destinationCardDeck.drawDestinationCards();
    }

    public void returnDestinationCards(List<DestinationCardModel> destinationCards) {

    }

    public int size() {
        return destinationCardDeck.size();
    }

}
