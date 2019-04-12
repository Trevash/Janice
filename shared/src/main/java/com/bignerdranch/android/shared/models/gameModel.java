package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.gameStates.AbstractServerGameState;
import com.bignerdranch.android.shared.gameStates.ServerGameNotStartedState;
import com.bignerdranch.android.shared.gameStates.ServerLastRoundState;
import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.exceptions.RouteNotFoundException;
import com.bignerdranch.android.shared.gameStates.AbstractClientGameState;
import com.bignerdranch.android.shared.gameStates.ServerInitialGameState;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.bignerdranch.android.shared.models.colors.playerColorEnum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class gameModel {
    private gameIDModel gameID;
    private String gameName;
    private boolean gameStarted; // TODO can remove this - by adding another state to represent an unstarted game
    private List<playerModel> players = new ArrayList<>();
    private chatboxModel chatbox;
    private int turnCounter;
    private chatboxModel gameHistory;
    private List<int[]> stats = new ArrayList<int[]>();

    private IGameState state;

    private List<abstractRoute> routes = new ArrayList<>();

    //For Database Connections:
    private LinkedList commands = new LinkedList();

    public gameModel(String newGameName, playerModel hostPlayer) {
        gameID = new gameIDModel();
        setGameName(newGameName);
        gameStarted = false;
        //this.setDecks(); decks are set in the game state
        this.state = new ServerGameNotStartedState(this);

        try {
            addPlayer(hostPlayer);
        } catch (DuplicateException e) {
            e.printStackTrace();
        }
        chatbox = new chatboxModel();
        gameHistory = new chatboxModel();
        setTurnCounter(-1);
    }

    public void setStats(List<int[]> s, usernameModel username) {
    	this.stats = s;
        List<trainCardModel> curPlayerHand = getPlayerByUsername(username).getTrainCardHand();
        int[] cardTypes = new int[9];
        for (int i = 0; i < curPlayerHand.size(); i++) {
            cardTypes[curPlayerHand.get(i).getColor().ordinal()] += 1;
        }
        stats.add(cardTypes);
    	this.stats.add(0, cardTypes);
    }
    
    // Returns a list of integers
    // indices corresponding to player indices return their respective final scores
    // all following integers are player indices corresponding to who had the longest
    //    routes.
    // For example, player 0 and player 1. Their respective scores at the end of the
    // 	  game are 100 and 105. Player 0 had the longest route. Thus, the returned
    //    list of variables will be [100, 105, 0].
    public List<Integer> getFinalStats() {
    	List<Integer> finalPoints = new ArrayList<>(); 
    	int maxRoute = -1;
    	List<Integer> longestRoutes = new ArrayList<>();
    	
    	for(int i = 0; i < players.size(); i++) {
    		playerModel tempPlayer = players.get(i);
    		int points = tempPlayer.getPoints();
    		points += tempPlayer.calculatePointsFromDestinationCards();
    		finalPoints.add(points);
    		int playerRouteLength = tempPlayer.calculateLongestRouteOfPlayer();
    		if (playerRouteLength > maxRoute) {
    			maxRoute = playerRouteLength;
    			longestRoutes = new ArrayList<>(i);
    		}
    		else if (playerRouteLength == maxRoute) {
    			longestRoutes.add(i);
    		}
    	}
    	for(int i = 0; i< longestRoutes.size(); i++) {
    		int index = longestRoutes.get(i);
    		finalPoints.set(index, finalPoints.get(index)+10);
    		finalPoints.add(index);
    	}
     	return finalPoints;
    }
    
    public List<int[]> getVariableStats(){
    	return this.stats;
    }
    
    private void createRoutes() {
        routes.addAll(singleRouteModel.createSingleRoutes());
        if (players.size() > 3) {
            routes.addAll(doubleRouteModelMany.createDoubleRoutesMany());
        } else {
            routes.addAll(doubleRouteModelFew.createDoubleRoutesFew());
        }
    }


    // Todo: Check for empty deck and other special cases - recommend moving decks into their own classes
    public trainCardModel drawTrainCardFromDeck() {
        return state.drawTrainCardFromDeck();
        // method handles case of empty train card deck
    }

    public trainCardModel drawFaceUpTrainCard(int pos) {
        return state.drawFaceUpTrainCard(pos);
        // method handles error checking and drawing replacement cards

        //if (pos < 0 || pos > 4) {
        //    throw new IllegalArgumentException("Invalid card position requested from face up train cards: " + pos);
        //}
        //trainCardModel curCard = this.faceUpCards.get(pos);
        //this.faceUpCards.set(pos, drawTrainCardFromDeck());
        //return curCard;
    }

    // public or private
    public List<DestinationCardModel> drawDestinationCards(playerIDModel clientID) {
        // state draws the destination cards: if on the client side, it calls a method on the server proxy
        // if on the server side, the state draws cards from the deck inside of it
        return state.drawDestinationCards(clientID);
    }

    public void returnRejectedDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        // similar to draw destination cards: clients call method on server, server returns to the deck
        // inside of it.
        state.returnDestinationCards(selectedCards, rejectedCards);
    }

    public void updateCurrentPlayerDestinationCards(List<DestinationCardModel> selectedCards) {
        // note: changed to be turn-independent - now directly returns to the player
        playerModel curPlayer = players.get(turnCounter);
        curPlayer.addDestinationCards(selectedCards);
        // turn order incremented when the card is returned to the deck, by the state
    }

    // host is the first player in the list
    public playerModel getHostPlayer() {
        return players.get(0);
    }

    public void setRoutes(List<abstractRoute> routes) {
        this.routes = routes;
    }

    public gameIDModel getGameID() {
        return gameID;
    }

    public void addPlayer(playerModel newPlayer) throws DuplicateException {
        if (gameStarted) {
            throw new IllegalStateException("Game has already been started");
        }
        for (playerModel curPlayer : this.players) {
            if (curPlayer.getUserName().getValue().equals(newPlayer.getUserName().getValue())) {
                throw new DuplicateException("User is already a player in this game");
            }
        }
        if (players.size() >= 5) {
            throw new IllegalStateException("Max number of players reached!");
        }
        //assigns player color
        newPlayer.setPlayerColor(playerColorEnum.values()[players.size()]);
        //draws new player's starting hand
        for (int i = 0; i < 4; i++) {
            newPlayer.addTrainCardToHand(drawTrainCardFromDeck());
        }
        players.add(newPlayer);
    }

    public int numPlayers() {
        return players.size();
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void startGame() {
        if (players.size() < 2) {
            throw new IllegalStateException("Insufficient number of players to start game!");
        }
        if (players.size() > 5) {
            throw new IllegalStateException("Too many players to start game!");
        }


        if (state instanceof AbstractServerGameState) {
            this.state = new ServerInitialGameState((AbstractServerGameState) state);
            this.gameStarted = true;
            this.createRoutes();
        } else {
            throw new IllegalStateException("the game needs to be started on the serverSide");
        }

        //color assigned in the addPlayer() function
        //determine player order: order they joined (order in 'players' array)
        //starting hand provided in the addPlayer() function
        //each player chooses their destination cards in turn order?
    }

    public List<playerModel> getPlayers() {
        return players;
    }

    public void updateChatbox(chatMessageModel message) {
        this.chatbox.addMessage(message);
    }

    public chatboxModel getChatbox() {
        return chatbox;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        } else {
            return this.getGameID() == ((gameModel) o).getGameID();
        }
    }

    //public ArrayList<trainCardModel> getTrainCardDeck() {
    //    return trainCardDeck;
    //}

    public List<trainCardModel> getFaceUpCards() {
        // TODO going to the draw train card screen calls this method twice
        return state.getFaceUpTrainCards();
    }

    public List<abstractRoute> getRoutes() {
        return routes;
    }

    public playerModel getPlayerByUsername(usernameModel username) {
        for (playerModel player : this.players) {
            if (player.getUserName().equals(username)) {
                return player;
            }
        }
        return null;
    }

    //Returns a list of int arrays
    // stats[0] = numbers of each color train card held by the player [int array length 9]
    // stats[1...n] = stats for player 1, player 2... player n [int array length 4]
    public List<int[]> getStats(usernameModel username) {
        List<int[]> stats = new ArrayList<>();

        List<trainCardModel> curPlayerHand = getPlayerByUsername(username).getTrainCardHand();
        int[] cardTypes = new int[9];
        for (int i = 0; i < curPlayerHand.size(); i++) {
            cardTypes[curPlayerHand.get(i).getColor().ordinal()] += 1;
        }
        stats.add(cardTypes);

        int[] totals = new int[2];
        totals[0] = getNumTrainCards();
        totals[1] = state.getDestinationCardDeckSize();
        stats.add(totals);

        for (int i = 0; i < players.size(); i++) {
            stats.add(players.get(i).getStats());
        }
        return stats;
    }

    public playerModel getPlayerModelFromID(playerIDModel id) {
        for (int i = 0; i < players.size(); i++) {
            if (id.getValue().equals(players.get(i).getId().getValue())) {
                return players.get(i);
            }
        }
        return null;
    }

    // wrong place to put this, but I (Jason) am not putting forth the effort in figuring out the
    // best way to add this to the client model.
    public playerIDModel getClientID() {
        if (state instanceof AbstractClientGameState) {
            return ((AbstractClientGameState) state).getClientID();
        } else {
            throw new IllegalStateException("Server-side does not have a client ID for itself");
        }
    }

    /**
     * @param serverProxy the server proxy that the client is using
     * @param playerNum   the player number of the client in this game
     */
    public void setClientMode(IServer serverProxy, int playerNum) {
        this.state = state.toClientState(serverProxy, this, playerNum);
    }

    // method to update the state: is there a way to make method accessible only from the state?
    // such as by having this call directly from the state
    public void setState(IGameState newState) {
        // note: this should only be called by the state
        this.state = newState;
    }

    public IGameState getState() {
        return state;
    }

    public void incrementTurnCounter() {
        if(turnCounter < 0){
            turnCounter = 0;
        }
        turnCounter += 1;
        if (turnCounter >= this.players.size()) {
            turnCounter = 0;
        }
        if (state instanceof AbstractClientGameState) {
            ((AbstractClientGameState) state).notifyTurnAdvancement();
        }
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
        if (state instanceof AbstractClientGameState) {
            ((AbstractClientGameState) state).notifyTurnAdvancement();
        }
    }
    public int getTurnCounter() {
        return turnCounter;
    }


    public void notifyTrainCardDrawn() {
        if (state instanceof AbstractClientGameState) {
            ((AbstractClientGameState) state).notifyTrainCardDrawn();
        }
    }

    public boolean isPlayersTurn(playerIDModel testPlayer) {
        if(getTurnCounter() == -1) {
            return false;
        }
        return players.get(turnCounter).getId().equals(testPlayer);
    }

    public boolean isPlayersTurn(int playerNum) {
        return turnCounter == playerNum;
    }

    public boolean canDrawDestCards() {
        if (state instanceof AbstractClientGameState) {
            return ((AbstractClientGameState) state).canDrawDestCards();
        } else {
            throw new IllegalStateException("canDrawDestCard operation currently a ClientSide only op");
        }
    }


    public boolean canDrawTrainCards() {
        if (state instanceof AbstractClientGameState) {
            return ((AbstractClientGameState) state).canDrawTrainCards();
        } else {
            throw new IllegalStateException("canDrawTrainCard operation currently a ClientSide only op");
        }
    }

    public boolean canDrawLocomotive() {
        if (state instanceof AbstractClientGameState) {
            return ((AbstractClientGameState) state).canDrawLocomotive();
        } else {
            throw new IllegalStateException("canDrawLocomotive operation currently a ClientSide only op");
        }
    }

    public boolean canClaimRoute() {
        if (state instanceof AbstractClientGameState) {
            return ((AbstractClientGameState) state).canClaimRoute();
        } else {
            throw new IllegalStateException("canClaimRoute operation currently a ClientSide only op");
        }
    }

    public void updateGameHistory(chatMessageModel entry) {
        this.gameHistory.addMessage(entry);
    }

    public chatboxModel getGameHistory() {
        return this.gameHistory;
    }

    public abstractRoute getRouteById(routeIDModel routeID) throws RouteNotFoundException {
        for (abstractRoute route : routes) {
            if (route.getRouteID().getValue().equals(routeID.getValue())) {
                return route;
            }
        }
        throw new RouteNotFoundException("Route at id " + routeID.getValue() + " not found in game " + this.getGameID().getValue());
    }

    public void setGameHistory(chatboxModel gameHistory) {
        this.gameHistory = gameHistory;
    }


    public cardColorEnum getFaceUpTrainCardColor(int i) {
        //return this.faceUpCards.get(i).getColor();
        //return trainCardBank.getFaceUpTrainCards().get(i).getColor();
        return getFaceUpCards().get(i).getColor();
    }

    public void addTrainCardToPlayersHand(trainCardModel returnCard, usernameModel username) {
        getPlayerByUsername(username).addTrainCardToHand(returnCard);
    }

    public int getNumTrainCards() {
        return state.getTrainCardDeckSize();
    }

    public int getNumTrainCardDiscards() {
        return state.getTrainCardDiscardSize();
    }

    public int getNumDestinationCards() {
        return this.state.getDestinationCardDeckSize();
    }

    public void setNumTrainCards(int numTrainCards) {
        if (state instanceof AbstractClientGameState) {
            ((AbstractClientGameState) state).setTrainCardDeckSize(numTrainCards);
        } else {
            throw new IllegalStateException("setting the number of train cards to a new value is " +
                    "a client-side-only operation");
        }
        //this.numTrainCardDeck = numTrainCards;
    }

    public void setNumDestinationCards(int numDestinationCards) {
        if (state instanceof AbstractClientGameState) {
            ((AbstractClientGameState) state).updateNumDestinationCards(numDestinationCards);
        } else {
            throw new IllegalStateException("manually setting the number of destination cards in " +
                    "the deck to a new value is a client-side-only operation");

        }

    }

    public void addToTrainDiscards(List<trainCardModel> discards) {
        //trainCardBank.addToDiscard(discards);
        // TODO is this a server-side only operation? currently implemented as such
        state.discard(discards);
        //if(state instanceof AbstractServerGameState) {
        //    state.discard(discards);
        // } else {
        //    throw new IllegalStateException("Client states")
        //}
        //this.trainCardDiscard.addAll(discards);
    }

    public List<trainCardModel> getTrainCardDiscards() {
        return state.getTrainCardDiscardPile();
    }

    public int getTrainCardDiscardSize() {
        return state.getTrainCardDiscardSize();
    }

    public void setTrainCardDiscards(List<trainCardModel> trainCardDiscards) {
        if (state instanceof AbstractClientGameState) {
            ((AbstractClientGameState) state).setTrainCardDiscard(trainCardDiscards);
        } else {
            throw new IllegalStateException("Manually changing the train card discard pile is a " +
                    "client-side-only operation");
        }

    }

    public void setPlayersHand(List<trainCardModel> hand, usernameModel username) {
        getPlayerByUsername(username).setTrainCardHand(hand);
    }

    public void setFaceUpCards(List<trainCardModel> faceUpCards) {
        //this.faceUpCards = faceUpCards;
        if (state instanceof AbstractClientGameState) {
            ((AbstractClientGameState) state).setFaceUpTrainCards(faceUpCards);
        } else {
            throw new IllegalStateException("Manually changing the face-up-train cards is a " +
                    "client-side-only operation");
        }
    }

    public int minKeepDestCards() {
        return state.minKeepDestCards();
    }

    public void onRouteClaimed(playerIDModel claimerID, IServer serverFacade) {
        if (state instanceof AbstractServerGameState) {
            ((AbstractServerGameState) state).onRouteClaimed(claimerID);
        }
    }

    public boolean isLastRound() {
        return state instanceof ServerLastRoundState;
    }

    public boolean isLastTurn() {
        if(isLastRound()){
            return ((ServerLastRoundState) state).isLastTurn();
        }
        return false;
    }

    public void checkIfLastTurn() {
        if(isLastRound()){
            ((ServerLastRoundState) state).checkIfLastTurn();
        }
    }

    public void setFinalStats(List<Integer> finalStats) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setStats(finalStats.get(i));
        }
    }

    public void addCommand(GenericCommand command) {
        this.commands.add(command);
    }

    public int numCommands(){
        return this.commands.size();
    }

    public void clearCommands() {
        this.commands.clear();
    }
}
