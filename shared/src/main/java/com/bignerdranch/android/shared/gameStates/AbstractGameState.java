package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.gameModel;

public abstract class AbstractGameState implements IGameState {

    private transient gameModel game; // transient means that it won't be serialized - needed, as
    // it is a circular dependency
    private int playerTurn; // TODO should this be here? Is this needed by every state? Don't think so
    // Could optionally make the gameOverStates just leave the player turn on the player with the last
    // state
    // states with player turns:
    //Clientside:
    //  activeplayer
    //  chooseDestCardState
    //  drawTrainCardState
    //  InactiveState
    //  Initial Game State?
    //Server side:
    //  InGameState
    //  LastRoundState

    // uncertain states:
    //  Initial game states (Client/Server)

    // no turn states:
    //  GameOverState(s)


    public AbstractGameState(gameModel game) {
        this.game = game;
        //game.getPlayers().size();
    }

    //protected void updateGame()
}
