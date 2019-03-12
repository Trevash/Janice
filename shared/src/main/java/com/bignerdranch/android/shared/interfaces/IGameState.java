package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

public interface IGameState {

    /**
     *
     * @return
     */
    public List<DestinationCardModel> drawDestinationCards();
    public void returnDestinationCards(List<DestinationCardModel> destinationCards);

    /**
     * returns the number of cards in the destination card deck
     * @return
     */
    public int destinationCardDeckSize();

    /**
     *
     * Precondition: IServer != null, gameIDModel is the game id of the game for this state.
     * Postcondition: the returned IGameState is the client's equivalent state of the implementing state
     *
     * @param serverProxy a reference to the server (in this case, a server proxy) so that the game
     *                    state can interact with the server
     * @param id the id of the game
     * @return the client version of this game state (itself if a client version)
     */
    public IGameState toClientState(IServer serverProxy, gameIDModel id);

    // TODO may want to add a way to send the gameModel from the client to the server - currently don't work

}
