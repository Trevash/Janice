package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.trainCardModel;

import java.util.List;

public class ServerInitialGameState extends AbstractServerGameState implements IGameState {


    // idea: have an array of size of the # of players containing the returned lists of destination
    // cards


    public ServerInitialGameState(gameModel game) {
        super(game);
    }


    public AbstractClientGameState toClientState(IServer server, gameModel game, int playerNum) {
        //super.getFaceUpTrainCards();
        //super.getGame();
        //return new ClientInitialGameState(server, game, getDestinationCardDeckSize(), game.getPlayers().get(playerNum).getId());
        return new ClientInitialGameState(server, game, playerNum, this);
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        super.returnDestinationCards(selectedCards, rejectedCards);
        if (super.getGame().isPlayersTurn(0)) {
            // updating the state ends up being redundant: the game state automatically updates
            // whenever a new state is made
            super.updateGameState(new ServerInGameState(this));
        }
    }

    // note: drawing a train card from the deck is used in starting the game, so it does not
    // throw in this state


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
