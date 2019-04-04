package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.models.gameModel;

public class ServerGameNotStartedState extends AbstractServerGameState {
    public ServerGameNotStartedState(gameModel game) {
        super(game);
    }

    @Override
    public AbstractClientGameState toClientState(IServer serverProxy, gameModel game, int playerNum) {
        return new ClientGameNotStartedState(serverProxy, game, super.getDestinationCardDeckSize(),
                game.getPlayers().get(playerNum).getId());
    }
}
