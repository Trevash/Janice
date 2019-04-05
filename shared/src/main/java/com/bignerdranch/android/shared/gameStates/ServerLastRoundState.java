package com.bignerdranch.android.shared.gameStates;


import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.models.playerModel;

public class ServerLastRoundState extends ServerInGameState {
    // TODO implement state - add in a check to see if it is the last player's turn
    // several ways to do the check: could check to see if the player has 2 or fewer train cards at
    // the start of their turn: if they do, then the game ends

    // Could also store which player initialized this round, and check when the turn increments


    private boolean lastTurn; // boolean value to tell if it is the very last player's turn
    private IServer serverFacade;

    public ServerLastRoundState(AbstractServerGameState prevState, IServer serverFacade) {
        super(prevState);
        this.serverFacade = serverFacade;
        // note: turn counter is not yet updated
        //startingPlayer = super.getGame().getPlayers().get(getGame().getTurnCounter());
    }



    // TODO implement now

    @Override
    protected void advanceTurn() {
        if(lastTurn) {
            serverFacade.endGame(getGame().getGameID());
        }
        super.advanceTurn();
        if(getCurrentPlayer().getLocomotives() <= 2) {
            // advanced to the player who initiated the turn
            lastTurn = true;
        }
    }

    private playerModel getCurrentPlayer() {
        return super.getGame().getPlayers().get(getGame().getTurnCounter());
    }

    // need a method to remove the game from the server
}
