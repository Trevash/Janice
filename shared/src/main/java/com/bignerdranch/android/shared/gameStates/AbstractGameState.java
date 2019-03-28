package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.gameModel;

public abstract class AbstractGameState implements IGameState { // TODO is this registered?

    private transient gameModel game; // transient means that it won't be serialized - needed, as
    // it is a circular dependency
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

    public AbstractGameState(AbstractGameState prevState) {
        this.game = prevState.game;
        updateGameState(this);
    }

    protected void advanceTurn() {
        game.incrementTurnCounter();
        // change state as appropriate
    }

    protected void updateGameState(AbstractGameState state) {
        game.setState(state);
    }

    protected gameModel getGame() {
        return game;
    }

    //protected void updateGame()
}
