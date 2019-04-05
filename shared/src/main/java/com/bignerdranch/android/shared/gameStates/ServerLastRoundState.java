package com.bignerdranch.android.shared.gameStates;


import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerModel;

public class ServerLastRoundState extends ServerInGameState {
    // several ways to do the last-turn check: could check to see if the player has 2 or fewer train cards at
    // the start of their turn: if they do, then the game ends at the end of their turn

    // Could also store which player initialized this round, and check when the turn increments


    private boolean lastTurn = false; // boolean value to tell if it is the very last player's turn

    public ServerLastRoundState(AbstractServerGameState prevState) {
        super(prevState);
        // note: turn counter is not yet updated
        //startingPlayer = super.getGame().getPlayers().get(getGame().getTurnCounter());
    }



    // TODO implement now

    @Override
    protected void advanceTurn() {
        super.advanceTurn();
        if(getCurrentPlayer().getLocomotives() <= 2) {
            // advanced to the player who initiated the turn
            lastTurn = true;
        }
    }

    private playerModel getCurrentPlayer() {
        return super.getGame().getPlayers().get(getGame().getTurnCounter());
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
     * @param game        the game that this state is associated with
     * @param playerNum
     * @return the client version of this game state (itself if a client version)
     */
    @Override
    public AbstractClientGameState toClientState(IServer serverProxy, gameModel game, int playerNum) {
        return new ClientGameOverState(serverProxy, game, super.getDestinationCardDeckSize(),
                game.getPlayers().get(playerNum).getId());
    }

    // need a method to remove the game from the server

    public boolean isLastTurn(){
        return lastTurn;
    }
}
