package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;

import java.util.List;

public class ClientActivePlayerState extends AbstractClientGameState {

    public ClientActivePlayerState(AbstractClientGameState prevState) {
        super(prevState);
    }

    /**
     * Constructs a AbstractClientGameState object that can interact with the provided server for the
     * provided gameID.<!-- -->
     * Preconditions: server is a valid, working IServer
     * Preconditions: gameID is the gameID of the game that this state object is a part of
     *
     * @param server           An Iserver, typically a serverProxy, that can be used to interact with the
     *                         server's version of the game.
     * @param game             the game for this state object.
     * @param destCardDeckSize the number of cards in the destination card deck
     * @param clientID
     */
    public ClientActivePlayerState(IServer server, gameModel game, int destCardDeckSize, playerIDModel clientID) {
        super(server, game, destCardDeckSize, clientID);
    }

    @Override
    public boolean canDrawTrainCards() {
        return true;
    }

    @Override
    public boolean canDrawDestCards() {
        return true;
    }

    @Override
    public boolean canClaimRoute() {
        return true;
    }

    @Override
    public boolean canDrawLocomotive() {
        return true;
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards(playerIDModel client) {
        // TODO provide error handling - also may want way to avoid two quick succession draws
        List<DestinationCardModel> drawnCards = super.drawDestinationCards(client);
        // do if succeeded
        super.updateGameState(new ClientChooseDestCardState(this, drawnCards));
        return drawnCards;
        // else if failed
            // error message - which would likely mean an exception
    }

    /**
     * a method that notifies the client game state that it needs to check that its state is valid
     * based on the turn
     */
    @Override
    public void notifyTurnAdvancement() {
        if(!getGame().isPlayersTurn(super.getClientID())) {
            updateGameState(new ClientInactiveState(this));
        }
    }

    @Override
    public void notifyTrainCardDrawn() {
        super.updateGameState(new ClientDrawTrainCardState(this));
    }
}
