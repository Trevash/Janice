package com.bignerdranch.android.shared.gameStates;


public class ServerLastRoundState extends ServerInGameState {
    // TODO implement state - add in a check to see if it is the last player's turn
    // several ways to do the check: could check to see if the player has 2 or fewer train cards at
    // the start of their turn: if they do, then the game ends

    // Could also store which player initialized this round, and check when the turn increments




    public ServerLastRoundState(AbstractServerGameState prevState) {
        super(prevState);
    }



    // TODO implement now

    // need a method to remove the game from the server
}
