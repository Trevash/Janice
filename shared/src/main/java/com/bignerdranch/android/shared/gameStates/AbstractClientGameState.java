package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.bignerdranch.android.shared.proxy.DestinationCardDeckProxy;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractClientGameState extends AbstractGameState implements IGameState {

    /**
     * A proxy deck that has the ability to send messages to the server, which is used to interact
     * with the actual Destination Card deck on the server.
     */
    private DestinationCardDeckProxy destinationCardDeck;

    //private int trainCardDiscardSize;
    private List<trainCardModel> trainCardDiscard;
    private int trainCardDeckSize;
    private List<trainCardModel> faceUpTrainCards;
    private playerIDModel clientID;


    public AbstractClientGameState(IServer server, gameModel game, playerIDModel clientID) {
        this(server, game, 30, clientID);
        //super(game)
        // should be used when going from client state to client state - if at all
        //destinationCardDeck = new DestinationCardDeckProxy(server, game.getGameID(), 30);
    }

    public AbstractClientGameState(AbstractClientGameState prevState) {
        super(prevState);
        destinationCardDeck = prevState.destinationCardDeck;
        this.clientID = prevState.clientID;
        this.trainCardDiscard = prevState.trainCardDiscard;
        this.trainCardDeckSize = prevState.trainCardDeckSize;
        this.faceUpTrainCards = prevState.faceUpTrainCards;
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
     */
    public AbstractClientGameState(IServer server, gameModel game, int destCardDeckSize, playerIDModel clientID) {
        super(game);
        // should be used when going from Server state to client state
        destinationCardDeck = new DestinationCardDeckProxy(server, game.getGameID(), clientID, destCardDeckSize);
        this.clientID = clientID;
        // these next two vars are here to avoid null-pointer-exceptions
        trainCardDiscard = new ArrayList<>();
        faceUpTrainCards = trainCardDiscard;
        //destinationCardDeck.updateSize(destCardDeckSize);
    }


    public abstract boolean canDrawTrainCards();

    public abstract boolean canDrawDestCards();

    public abstract boolean canClaimRoute();

    /**
     * a method that notifies the client game state that it needs to check that its state is valid
     * based on the turn
     */
    public abstract void notifyTurnAdvancement();

    public playerIDModel getClientID() {
        return clientID;
    }

    protected DestinationCardDeckProxy getDestinationCardDeck() {
        return destinationCardDeck;
    }

    public void updateNumDestinationCards(int numDestinationCards) {
        getDestinationCardDeck().updateSize(numDestinationCards);
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
    public AbstractClientGameState toClientState(IServer serverProxy, gameModel game, int playerNum) {
        return this;
    }


    @Override
    public List<DestinationCardModel> drawDestinationCards(playerIDModel clientID) {
        if(!clientID.equals(this.clientID)) {
            throw new IllegalArgumentException("Error: the clientID passed into " +
                    "drawDestinationCards does not match the clientID in the state");
        }
        if (canDrawDestCards()) {
            return destinationCardDeck.drawDestinationCards();
        } else {
            throw new IllegalStateException("Cannot draw destination cards in this state");
        }
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        if (canDrawDestCards()) {
            destinationCardDeck.returnDestinationCards(selectedCards, rejectedCards);
        } else {
            throw new IllegalStateException("Cannot draw/return destination cards in this state");
        }
    }

    @Override
    public int getDestinationCardDeckSize() {
        return destinationCardDeck.size();
    }

    @Override
    public trainCardModel drawTrainCardFromDeck() {
        throw new RuntimeException("drawing train cards not usable in this client state");
        //return null;
    }

    /**
     * @param cardLocation the number representing the card's location in the face up "pile", which
     *                     should be a number from 0 through 4
     * @return the drawn card
     */
    @Override
    public trainCardModel drawFaceUpTrainCard(int cardLocation) {
        throw new RuntimeException("drawing train cards not usable in this client state");
        //return null;
    }

    @Override
    public int getTrainCardDiscardSize() {
        //return trainCardDiscardSize;
        return trainCardDiscard.size();
    }

    public List<trainCardModel> getTrainCardDiscardPile() {
        return trainCardDiscard;
    }

    public void setTrainCardDiscard(List<trainCardModel> trainCardDiscard) {
        this.trainCardDiscard = trainCardDiscard;
    }

    @Override
    public int getTrainCardDeckSize() {
        return trainCardDeckSize;
    }

    public void setTrainCardDeckSize(int size) {
        trainCardDeckSize = size;
    }

    @Override
    public List<trainCardModel> getFaceUpTrainCards() {
        return faceUpTrainCards;
    }

    public void setFaceUpTrainCards(List<trainCardModel> faceUpTrainCards) {
        this.faceUpTrainCards = faceUpTrainCards;
    }

    @Override
    public void discard(List<trainCardModel> discardedCards) {
        throw new IllegalStateException("discarding train cards is currently implemented as a " +
                "server-side only operation");
    }

    public void notifyTrainCardDrawn() {
        // does nothing, most of the time
    }


    public boolean canDrawLocomotive() {
        return false;
    }

}
