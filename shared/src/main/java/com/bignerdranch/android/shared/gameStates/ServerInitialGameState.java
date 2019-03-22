package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.DestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

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

    public AbstractClientGameState toClientState(IServer server, gameIDModel id) {
        return new ClientInitialGameState(server, id);
    }


    public int destinationCardDeckSize() {
        return destinationCardDeck.size();
    }
}
