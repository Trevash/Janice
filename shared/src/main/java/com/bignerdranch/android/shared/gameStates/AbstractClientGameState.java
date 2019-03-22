package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.gameIDModel;

public abstract class AbstractClientGameState implements IGameState {

    public abstract boolean canDrawTrainCards();
    public abstract boolean canDrawDestCards();
    public abstract boolean canClaimRoute();

    /**
     * Returns a client-side equivalent of the current state, which has the ability to interact with
     * the server via the provided proxy.<!-- --> As this state is a client state, this method
     * simply returns the current state.<!-- -->
     * Precondition: IServer != null
     * Precondition: gameIDModel is the game id of the game for this state.<!-- -->
     * Postcondition: the returned IGameState is the client's equivalent state of the implementing state.<!-- -->
     *
     * @param serverProxy a reference to the server (in this case, a server proxy) so that the game's
     *                    state can interact with the server
     * @param id          the id of the game that this state is associated with
     * @return the client version of this game state (itself if a client version)
     */
    @Override
    public AbstractClientGameState toClientState(IServer serverProxy, gameIDModel id) { // TODO add in the player/userID?
        return this;
    }
}
