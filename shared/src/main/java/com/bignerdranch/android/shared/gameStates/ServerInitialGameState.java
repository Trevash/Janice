package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.DestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;

import java.util.List;

public class ServerInitialGameState extends AbstractGameState implements IGameState {

    private IDestinationCardDeck destinationCardDeck = new DestinationCardDeck();

    // idea: have an array of size of the # of players containing the returned lists of destination
    // cards


    public ServerInitialGameState(gameModel game) {
        super(game);
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        return destinationCardDeck.drawDestinationCards();
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        destinationCardDeck.returnDestinationCards(selectedCards, rejectedCards);
        // TODO I'm assuming that the game actually returned the rejected cards? test that it does
        super.advanceTurn();
        if (super.getGame().isPlayersTurn(0)) {
            super.updateGameState(new ServerInGameState(this, destinationCardDeck));
        }

    }

    public AbstractClientGameState toClientState(IServer server, gameModel game, int playerNum) {
        return new ClientInitialGameState(server, game, getDestinationCardDeckSize());
    }


    public int getDestinationCardDeckSize() {
        return destinationCardDeck.size();
    }
}
