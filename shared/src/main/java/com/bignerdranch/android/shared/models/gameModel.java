package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.IServer;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class gameModel {
    private gameIDModel gameID;
    private String gameName;
    private boolean gameStarted; // TODO can remove this - by adding another state to represent an unstarted game
    private List<playerModel> players = new ArrayList<>();
    private chatboxModel chatbox;
    private int turnCounter;
    private Map<String, playerModel> mapPlayerIDToModel;
    private chatboxModel gameHistory;

    // TODO create states - will want more for phase 3
    private IGameState state;

    // private playerModel hostPlayer;

    // MapPic
    // Map<City, coordinate)
    private List<abstractRoute> routes = new ArrayList<>();
    // ChatBox
    // Decks
    // Train
    private ArrayList<trainCardModel> trainCardDeck = new ArrayList<>();
    // Dest
    // Face-up
    private List<trainCardModel> faceUpCards = new ArrayList<>();
    // Discard
    private LinkedList trainCardDiscard = new LinkedList();

    /*
    public gameModel(String newGameName, playerModel hostPlayer, IGameState state) {
        gameID = new gameIDModel();
        setGameName(newGameName);
        gameStarted = false;
        mapPlayerIDToModel = new ConcurrentHashMap<>();
        this.setDecks();

        try {
            addPlayer(hostPlayer);
        } catch (DuplicateException e) {
            e.printStackTrace();
        }
        chatbox = new chatboxModel();
        gameHistory = new chatboxModel();
        this.turnCounter = 0;

        this.state = state;
    } // */

    public gameModel(String newGameName, playerModel hostPlayer) {
        gameID = new gameIDModel();
        setGameName(newGameName);
        gameStarted = false;
        mapPlayerIDToModel = new ConcurrentHashMap<>();
        this.setDecks();

        try {
            addPlayer(hostPlayer);
        } catch (DuplicateException e) {
            e.printStackTrace();
        }
        chatbox = new chatboxModel();
        gameHistory = new chatboxModel();
        this.turnCounter = 0;

        this.state = new ServerInitialGameState(this);
    }

    private void setDecks() {

        //Create dest cards deck here
        //this.destinationCardDeck.add(Constants.DestinationCards.LOS_ANGELES_NEW_YORK);

        //Create train card deck here, then shuffles
        for (int i = 0; i < 12; i++) {
            this.trainCardDeck.add(Constants.TrainCards.BLUE);
            this.trainCardDeck.add(Constants.TrainCards.ORANGE);
            this.trainCardDeck.add(Constants.TrainCards.PURPLE);
            this.trainCardDeck.add(Constants.TrainCards.WHITE);
            this.trainCardDeck.add(Constants.TrainCards.BLACK);
            this.trainCardDeck.add(Constants.TrainCards.GREEN);
            this.trainCardDeck.add(Constants.TrainCards.RED);
            this.trainCardDeck.add(Constants.TrainCards.YELLOW);
        }
        for (int i = 0; i < 14; i++) {
            this.trainCardDeck.add(Constants.TrainCards.LOCOMOTIVE);
        }
        shuffleTrainCards();
        //Draw 5 cards from deck, assign to the faceUp stuff
        this.faceUpCards.add(this.drawTrainCardFromDeck());
        this.faceUpCards.add(this.drawTrainCardFromDeck());
        this.faceUpCards.add(this.drawTrainCardFromDeck());
        this.faceUpCards.add(this.drawTrainCardFromDeck());
        this.faceUpCards.add(this.drawTrainCardFromDeck());

        //destinationCardDeck = new DestinationCardDeckProxy();
    }

    private void createRoutes() {
        routes.addAll(singleRouteModel.createSingleRoutes());
        if (players.size() > 3) {
            routes.addAll(doubleRouteModelMany.createDoubleRoutesMany());
        } else {
            routes.addAll(doubleRouteModelFew.createDoubleRoutesFew());
        }
    }

    private void shuffleTrainCards() {
        for (int i = 0; i < trainCardDeck.size(); i++) {
            int j = (int) (Math.random() * trainCardDeck.size());
            trainCardModel temp = trainCardDeck.get(i);
            trainCardDeck.set(i, trainCardDeck.get(j));
            trainCardDeck.set(j, temp);
        }
    }

    // Todo: Check for empty deck and other special cases - recommend moving decks into their own classes
    public trainCardModel drawTrainCardFromDeck() {
        int numCards = (trainCardDeck.size() - 1);
        return trainCardDeck.remove(numCards); //Eliminate top card from array
    }

    public trainCardModel drawFaceUpTrainCard(int pos) throws Exception {
        if (pos < 0 || pos > 4) {
            throw new Exception("Invalid card position requested from face up train cards: " + pos);
        }

        trainCardModel curCard = this.faceUpCards.get(pos);
        this.faceUpCards.set(pos, drawTrainCardFromDeck());

        return curCard;
    }

    // public or private
    public List<DestinationCardModel> drawDestinationCards() {
        // state draws the destination cards: if on the client side, it calls a method on the server proxy
        // if on the server side, the state draws cards from the deck inside of it
        return state.drawDestinationCards();
    }

    public void returnRejectedDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        // similar to draw destination cards: clients call method on server, server returns to the deck
        // inside of it.
        state.returnDestinationCards(selectedCards, rejectedCards);
    }

    public void updateCurrentPlayerDestinationCards(List<DestinationCardModel> selectedCards) {
    	playerModel curPlayer = players.get(turnCounter);
    	curPlayer.addDestinationCards(selectedCards);
    	//TODO: increment turn order, update game history
    }
    
    // host is the first player in the list
    public playerModel getHostPlayer() {
        return players.get(0);
    }

    public void setRoutes(List<abstractRoute> routes){
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
        mapPlayerIDToModel.put(newPlayer.getId().getValue(), newPlayer);
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
        if (players.size() < 1){
            throw new IllegalStateException("Insufficient number of players to start game!");
        }
        if (players.size() > 5) {
            throw new IllegalStateException("Too many players to start game!");
        }

        this.gameStarted = true;
        this.createRoutes();

        // TODO have each player draw destinationCards - have drawn destination cards,
        // TODO and each player starts in the draw destination card fragment

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

    public ArrayList<trainCardModel> getTrainCardDeck() {
        return trainCardDeck;
    }

    public List<trainCardModel> getFaceUpCards() {
        return faceUpCards;
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
        totals[0] = trainCardDeck.size();
        totals[1] = state.destinationCardDeckSize();
        stats.add(totals);

        for (int i = 0; i < players.size(); i++) {
            stats.add(players.get(i).getStats());
        }
        return stats;
    }

    public playerModel getPlayerModelFromID(playerIDModel id) {
        return mapPlayerIDToModel.get(id.getValue());
    }

    /**
     *
     * @param serverProxy the server proxy that the client is using
     * @param playerNum the player number of the client in this game
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

    public void incrementTurnCounter(){
        turnCounter += 1;
        if (turnCounter >= this.players.size())
            turnCounter = 0;
    }

    public boolean isPlayersTurn(playerIDModel testPlayer){
        return players.get(turnCounter).getId().equals(testPlayer);
    }

    public boolean isPlayersTurn(int playerNum) {
        return turnCounter == playerNum;
    }

    public boolean canDrawDestCards() {
        if(state instanceof AbstractClientGameState) {
            return ((AbstractClientGameState) state).canDrawDestCards();
        } else {
            throw new IllegalStateException("canDrawDestCard operation currently a ClientSide only op");
        }
    }

    public boolean canDrawTrainCards() {
        if(state instanceof AbstractClientGameState) {
            return ((AbstractClientGameState) state).canDrawTrainCards();
        } else {
            throw new IllegalStateException("canDrawTrainCard operation currently a ClientSide only op");
        }
    }

    public boolean canClaimRoute() {
        if(state instanceof AbstractClientGameState) {
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
            if(route.getRouteID().getValue().equals(routeID.getValue())){
                return route;
            }
        }
        throw new RouteNotFoundException("Route at id " + routeID.getValue() + " not found in game " + this.getGameID().getValue());
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setGameHistory(chatboxModel gameHistory) {
        this.gameHistory = gameHistory;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public void setTrainCardDeck(ArrayList<trainCardModel> newDeck) {
        this.trainCardDeck = newDeck;
    }

    public cardColorEnum getFaceUpTrainCardColor(int i) {
        return this.faceUpCards.get(i).getColor();
    }

    public void addTrainCardToPlayersHand(trainCardModel returnCard, usernameModel username) {
        getPlayerByUsername(username).addTrainCardToHand(returnCard);
    }
}
