package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;

import java.util.List;

/**
 * The state representing the client side at the beginning of the game.<!-- --> See IGameState for
 * more specific information
 */
public class ClientInitialGameState extends AbstractClientGameState implements IGameState {
    // note: some of the information for this class is written in IGameState, specifically the
    // overridden methods

    /*
     * A proxy deck that has the ability to send messages to the server, which is used to interact
     * with the actual Destination Card deck on the server.
     */
    //private DestinationCardDeckProxy destinationCardDeck;
    private List<DestinationCardModel> drawnCards;


    //public ClientInitialGameState(IServer server, gameIDModel gameID) {
    //  super(server, gameID);
    //destinationCardDeck = new DestinationCardDeckProxy(server, gameID);
    //}

    /*
     * Constructs a ClientIntialGameState object that can interact with the provided server for the
     * provided gameID.<!-- -->
     * Preconditions: server is a valid, working IServer
     * Preconditions: gameID is the gameID of the game that this state object is a part of
     *
     * @param server An Iserver, typically a serverProxy, that can be used to interact with the
     *               server's version of the game.
     * @param game the game for this state object.
     * @param destCardDeckSize the size of the destination card deck
     */
    //public ClientInitialGameState(IServer server, gameModel game, int destCardDeckSize, playerIDModel clientID) {
    //    super(server, game, destCardDeckSize, clientID);
    //}

    public ClientInitialGameState(IServer server, gameModel game, int playerNum, AbstractServerGameState serverState) {
        super(server, game, serverState.getDestinationCardDeckSize(), game.getPlayers().get(playerNum).getId());
        this.setFaceUpTrainCards(serverState.getFaceUpTrainCards());
    }

    /*
    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        return destinationCardDeck.drawDestinationCards();
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        destinationCardDeck.returnDestinationCards(selectedCards, rejectedCards);
    }


    @Override
    public int getDestinationCardDeckSize() {
        return destinationCardDeck.size();
    }
    */

    @Override
    public List<DestinationCardModel> drawDestinationCards(playerIDModel clientID) {
        if (drawnCards == null) {
            drawnCards = super.drawDestinationCards(clientID);
        }
        return drawnCards;
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        // add in check for num of destination cards?
        getDestinationCardDeck().returnDestinationCards(selectedCards, rejectedCards);
        //super.advanceTurn();
        if (super.getGame().isPlayersTurn(super.getClientID())) {
            // TODO this may cause issues, but right now, this is needed to ensure that the first player is in the right state
            super.updateGameState(new ClientActivePlayerState(this));
        } else {
            super.updateGameState(new ClientInactiveState(this));
        }
    }

    @Override
    public boolean canDrawTrainCards() {
        return false;
    }

    @Override
    public boolean canDrawDestCards() {
        return true;
    }

    @Override
    public boolean canClaimRoute() {
        return false;
    }

    @Override
    public void notifyTurnAdvancement() {
        // TODO change this state so that it doesn't have a turn counter?
        System.out.println(this.getClass().toString() + " was notified of a turn advancement: " +
                "check for bug, as this state should automatically change states");
        //if(getGame().isPlayersTurn(super.getClientID())) {
        //super.updateGameState(new ClientActivePlayerState(this));
        //}
    }
}
