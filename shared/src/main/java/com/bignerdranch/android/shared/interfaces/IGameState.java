package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

public interface IGameState {

    public List<DestinationCardModel> drawDestinationCards();
    public void returnDestinationCards(List<DestinationCardModel> destinationCards);
    public int size();

    /**
     *
     * @param serverProxy a reference to the server (in this case, a server proxy) so that the game
     *                    state can interact with the server
     * @param id the id of the game
     * @return the client version of this game state (itself if a client version)
     */
    public IGameState toClientState(IServer serverProxy, gameIDModel id);

    // TODO may want to add a way to send the gameModel from the client to the server - currently don't work

}
