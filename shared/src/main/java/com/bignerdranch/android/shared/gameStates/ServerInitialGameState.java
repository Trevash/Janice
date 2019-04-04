package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.exceptions.WrongGameException;
import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.trainCardModel;

import java.util.ArrayList;
import java.util.List;

public class ServerInitialGameState extends AbstractServerGameState implements IGameState {


    // idea: have an array of size of the # of players containing the returned lists of destination
    // cards
    List<List<DestinationCardModel>> drawnCards;
    List<List<DestinationCardModel>> returnedCards; // store the list of returned cards, so as to avoid race conditions

    public ServerInitialGameState(AbstractServerGameState prevState) {
        super(prevState);
        int numPlayers = super.getGame().getPlayers().size();
        List<playerModel> players = super.getGame().getPlayers();
        //int[] cat = new int[5];
        drawnCards = new ArrayList<>();
        returnedCards = new ArrayList<>();
        //List<DestinationCardModel>[] drawnCards = new ArrayList<DestinationCardModel>[numPlayers];

        for (int i = 0; i < players.size(); i++) {
            // draw the cards for each player
            drawnCards.add(super.drawDestinationCards(players.get(i).getId()));
            // nonr of the players have returned their cards yet
            returnedCards.add(null);
        }
    }

    public AbstractClientGameState toClientState(IServer server, gameModel game, int playerNum) {
        //super.getFaceUpTrainCards();
        //super.getGame();
        //return new ClientInitialGameState(server, game, getDestinationCardDeckSize(), game.getPlayers().get(playerNum).getId());
        return new ClientInitialGameState(server, game, playerNum, this);
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
        //List<playerModel> players = super.getGame().getPlayers();
        //for (int i = 0; i < players.size(); i++) {
        //    playerModel player = players.get(i);
        //    if (clientID.equals(player.getId())) {
        //        return drawnCards.get(i);
        //    }
        //}
        return drawnCards.get(getPlayerNum(clientID));
        //throw new WrongGameException("Client is not one of the players in this game");
        //return super.drawDestinationCards(clientID);
    }

    private int getPlayerNum(playerIDModel clientID) {
        List<playerModel> players = super.getGame().getPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (clientID.equals(players.get(i).getId())) {
                //return drawnCards.get(i);
                return i;
            }
        }
        throw new WrongGameException("Client is not one of the players in this game");
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {

        List<playerModel> players = super.getGame().getPlayers();
        for(int i = 0; i < players.size(); i++) {
            if(drawnCards.get(i).containsAll(selectedCards) && drawnCards.get(i).containsAll(rejectedCards)) {
                returnedCards.set(i, rejectedCards);
                updateStateIfAllReturned();
                return;
            }
        }

        //super.returnDestinationCards(selectedCards, rejectedCards);
        //if (super.getGame().isPlayersTurn(0)) {
            // updating the state ends up being redundant: the game state automatically updates
            // whenever a new state is made
        //    super.updateGameState(new ServerInGameState(this));
        //}
    }

    // helper method that checks if everyone has returned, and updates the state if all players
    // have returned their cards
    private void updateStateIfAllReturned() {
        // if has everyone returned cards yet
        for(List<DestinationCardModel> rejectedCards: returnedCards) {
            if(rejectedCards == null) {
                return; // not everyone has returned their cards yet
            }
        }
        // all players have ret'd cards, so put them back in the deck - note that this does not
        // return in the order that the cards were returned
        // return the cards
        for(List<DestinationCardModel> rejectedCards: returnedCards) {
            // selectedCards is currently neither used nor checked in this case
            super.returnDestinationCards(null, rejectedCards);
        }
        // update the state
        super.updateGameState(new ServerInGameState(this));
    }



    // note: drawing a train card from the deck is used in starting the game, so it does not
    // throw in this state


    /**
     * @param cardLocation the number representing the card's location in the face up "pile", which
     *                     should be a number from 0 through 4
     * @return the drawn card
     */
    @Override
    public trainCardModel drawFaceUpTrainCard(int cardLocation) {
        throw new IllegalStateException("Cannot draw train cards during the start of the game");
    }
}
