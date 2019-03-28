package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.models.DestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.TrainCardBank;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.trainCardModel;

import java.util.List;

public class ServerInitialGameState extends AbstractGameState implements IGameState {

    private IDestinationCardDeck destinationCardDeck = new DestinationCardDeck();
    private TrainCardBank trainCardBank = new TrainCardBank();

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
            // updating the state ends up being redundant: the game state automatically updates
            // whenever a new state is made
            super.updateGameState(new ServerInGameState(this, destinationCardDeck, trainCardBank));
        }

    }

    public AbstractClientGameState toClientState(IServer server, gameModel game, int playerNum) {
        return new ClientInitialGameState(server, game, getDestinationCardDeckSize());
    }


    public int getDestinationCardDeckSize() {
        return destinationCardDeck.size();
    }

    @Override
    public trainCardModel drawTrainCardFromDeck() {
        // note that this method is called when starting the game
        return trainCardBank.drawTrainCardFromDeck();
        //throw new IllegalStateException("Cannot draw train cards during the start of the game");
    }

    /**
     * @param cardLocation the number representing the card's location in the face up "pile", which
     *                     should be a number from 0 through 4
     * @return the drawn card
     */
    @Override
    public trainCardModel drawFaceUpTrainCard(int cardLocation) {
        throw new IllegalStateException("Cannot draw train cards during the start of the game");
    }
}
