package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.proxy.DestinationCardDeckProxy;

import java.util.List;

public abstract class AbstractClientGameState extends AbstractGameState implements IGameState {

    /**
     * A proxy deck that has the ability to send messages to the server, which is used to interact
     * with the actual Destination Card deck on the server.
     */
    private DestinationCardDeckProxy destinationCardDeck;


    public AbstractClientGameState(IServer server, gameModel game) {
        this(server, game, 30);
        //super(game)
        // should be used when going from client state to client state - if at all
        //destinationCardDeck = new DestinationCardDeckProxy(server, game.getGameID(), 30);
    }

    public AbstractClientGameState(AbstractClientGameState prevState) {
        super(prevState);
        destinationCardDeck = prevState.destinationCardDeck;
    }

    /**
     * Constructs a AbstractClientGameState object that can interact with the provided server for the
     * provided gameID.<!-- -->
     * Preconditions: server is a valid, working IServer
     * Preconditions: gameID is the gameID of the game that this state object is a part of
     *
     * @param server An Iserver, typically a serverProxy, that can be used to interact with the
     *               server's version of the game.
     * @param game the game for this state object.
     * @param destCardDeckSize the number of cards in the destination card deck
     */
    public AbstractClientGameState(IServer server, gameModel game, int destCardDeckSize) {
        super(game);
        // should be used when going from Server state to client state
        destinationCardDeck = new DestinationCardDeckProxy(server, game.getGameID(), destCardDeckSize);
        //destinationCardDeck.updateSize(destCardDeckSize);
    }


    public abstract boolean canDrawTrainCards();
    public abstract boolean canDrawDestCards();
    public abstract boolean canClaimRoute();

    protected DestinationCardDeckProxy getDestinationCardDeck() {
        return destinationCardDeck;
    }

    /**
     * Returns a client-side equivalent of the current state, which has the ability to interact with
     * the server via the provided proxy.<!-- --> As this state is a client state, this method
     * simply returns the current state.<!-- -->
     * Precondition: IServer != null
     * Precondition: gameIDModel is the game id of the game for this state.<!-- -->
     * Postcondition: the returned IGameState is the client's equivalent state of the implementing state.<!-- -->
     *
     * @param game        the game that this state is associated with
     * @param serverProxy a reference to the server (in this case, a server proxy) so that the game's
     *                    state can interact with the server
     * @param playerNum
     * @return the client version of this game state (itself if a client version)
     */
    @Override
    public IGameState toClientState(IServer serverProxy, gameModel game, int playerNum) {
        return this;
    }


    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        if(canDrawDestCards()) {
            return destinationCardDeck.drawDestinationCards();
            //return null; // TODO move functionality to here
        } else {
            throw new IllegalStateException("Cannot draw destination cards in this state");
        }
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        if(canDrawDestCards()) {
            destinationCardDeck.returnDestinationCards(selectedCards, rejectedCards);
        } else {
            throw new IllegalStateException("Cannot draw/return destination cards in this state");
        }
    }

    @Override
    public int destinationCardDeckSize() {
        return destinationCardDeck.size();
    }
}
