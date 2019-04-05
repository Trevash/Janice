package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.TrainCardBank;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;


import java.util.List;

public class ServerInGameState extends AbstractServerGameState implements IGameState {

    //private IDestinationCardDeck destinationCardDeck;
    //private TrainCardBank trainCardBank;

    //public ServerInGameState(AbstractServerGameState prevState, IDestinationCardDeck destinationCardDeck, TrainCardBank bank) {
    public ServerInGameState(AbstractServerGameState prevState) {
        super(prevState);
    }

    /**
     * Draws up to 3 destination cards, as specified by the rules of the game.<!-- -->
     * Precondition: the state has a valid reference to the game's destination card deck.<!-- -->
     * Postcondition: the returned list contains all of the destination cards removed from the deck.<!-- -->
     * Postcondition: the deck's size is reduced by the number of destination cards drawn.<!-- -->
     *
     * @param clientID
     * @return a list containing all of the drawn destination cards.
     */
    @Override
    public List<DestinationCardModel> drawDestinationCards(playerIDModel clientID) {
        if(super.getGame().isPlayersTurn(clientID)) {
            return super.drawDestinationCards(clientID);
        } else {
            throw new IllegalArgumentException("Only the client whose turn it is may " +
                    "draw destination cards");
        }
    }


    /**
     * Returns a client-side equivalent of the current state, which has the ability to interact with
     * the server via the provided proxy.<!-- --> If the current state is a client state, then this
     * method simply returns the current state.<!-- -->
     * Precondition: IServer != null
     * Precondition: gameIDModel is the game id of the game for this state.<!-- -->
     * Postcondition: the returned IGameState is the client's equivalent state of the implementing state.<!-- -->
     *
     * @param serverProxy a reference to the server (in this case, a server proxy) so that the game's
     *                    state can interact with the server
     * @param game  the game that this state is associated with
     * @param playerNum
     * @return the client version of this game state (itself if a client version)
     */
    @Override
    public AbstractClientGameState toClientState(IServer serverProxy, gameModel game, int playerNum) {
        if(game.isPlayersTurn(playerNum)) {
            // active player state
            return new ClientActivePlayerState(serverProxy, game, getDestinationCardDeckSize(), game.getPlayers().get(playerNum).getId());
        } else {
            return new ClientInactiveState(serverProxy, game, getDestinationCardDeckSize(), game.getPlayers().get(playerNum).getId());
        }
    }

    @Override
    public void onRouteClaimed(playerIDModel claimer, IServer serverFacade) {
        if(getGame().getPlayerModelFromID(claimer).getLocomotives() <= 2) {
            super.updateGameState(new ServerLastRoundState(this, serverFacade));
        }
        super.onRouteClaimed(claimer, serverFacade); // to update the turn counter
        serverFacade.sendLastRoundMessage(getGame().getGameID());
    }


}
