package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.bignerdranch.android.shared.models.colors.playerColorEnum;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;
import com.bignerdranch.android.shared.proxy.DestinationCardDeckProxy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class gameModel {
    private gameIDModel gameID;
    private String gameName;
    private boolean gameStarted;
    private List<playerModel> players = new ArrayList<>();
    private chatboxModel chatbox;
    private int turnCounter;

    // TODO create states
    private IGameState state;

    // private playerModel hostPlayer;

    // MapPic
    // Map<City, coordinate)
    // List<Route> routes
    private List<abstractRoute> routes = new ArrayList<>();
    // ChatBox
    // Decks
        // Train
    private ArrayList<trainCardModel> trainCardDeck = new ArrayList<>();
        // Dest
    // TODO change how this is implemented - idea: have an interface for this: interface is implemented
    // by the deck, and also by a proxy which calls the server proxy: Is this workable?
    //private LinkedList<DestinationCardModel> destinationCardDeck = new LinkedList<>();
    private IDestinationCardDeck destinationCardDeck;
        // Face-up
    private List<trainCardModel> faceUpCards = new ArrayList<>();
        // Discard
    private LinkedList trainCardDiscard = new LinkedList();


    public gameModel(String newGameName, playerModel hostPlayer, IGameState state) {
        gameID = new gameIDModel();
        setGameName(newGameName);
        gameStarted = false;
        players.add(hostPlayer);
        chatbox = new chatboxModel();
        this.setGameRoutesAndDecks();
        this.turnCounter = 0;

        this.state = state;

        destinationCardDeck = new DestinationCardDeckProxy();
    }

    private void setGameRoutesAndDecks() {

        //Create dest cards deck here
        //this.destinationCardDeck.add(Constants.DestinationCards.LOS_ANGELES_NEW_YORK);

        //Create train card deck here (shuffle?)
        for(int i = 0; i < 12; i++) {
            this.trainCardDeck.add(new trainCardModel(cardColorEnum.WHITE));
            this.trainCardDeck.add(new trainCardModel(cardColorEnum.BLUE));
            this.trainCardDeck.add(new trainCardModel(cardColorEnum.RED));
            this.trainCardDeck.add(new trainCardModel(cardColorEnum.GREEN));
            this.trainCardDeck.add(new trainCardModel(cardColorEnum.BLACK));
            this.trainCardDeck.add(new trainCardModel(cardColorEnum.YELLOW));
        }
        shuffleTrainCards();
        //Draw 5 cards from deck, assign to the faceUp stuff
        this.faceUpCards.add(this.drawTrainCard());
        this.faceUpCards.add(this.drawTrainCard());
        this.faceUpCards.add(this.drawTrainCard());
        this.faceUpCards.add(this.drawTrainCard());
        this.faceUpCards.add(this.drawTrainCard());
    }

    private void shuffleTrainCards() {
        for(int i = 0; i < trainCardDeck.size(); i++) {
            int j = (int)(Math.random() * trainCardDeck.size());
            trainCardModel temp = trainCardDeck.get(i);
            trainCardDeck.set(i, trainCardDeck.get(j));
            trainCardDeck.set(j, temp);
        }
    }

    //Todo: Check for empty deck and other special cases - recommend moving decks into their own classes
    private trainCardModel drawTrainCard() {
        int numCards = (trainCardDeck.size() - 1);
        trainCardModel card = this.trainCardDeck.get(numCards); //Get top card
        trainCardDeck.remove(numCards); //Eliminate top card from array
        return card;
    }

    private List<DestinationCardModel> drawDestinationCards() {
        return state.drawDestinationCards();
    }

    // TODO have way to draw destination cards: pass in an interface as a parameter,
    // then call a method on said interface? something to think about
    // I think that it may be a good idea to call a method in IServer for the actual action
    // of drawing a destination card.

    // host is the first player in the list
    public playerModel getHostPlayer() {
        return players.get(0);
    }

    public gameIDModel getGameID() {
        return gameID;
    }

    public void addPlayer(playerModel newPlayer) throws DuplicateException {
        if(gameStarted) {
            throw new IllegalStateException("Game has already been started");
        }
        for (playerModel curPlayer : this.players) {
            if(curPlayer.getUserName().getValue().equals(newPlayer.getUserName().getValue())){
                throw new DuplicateException("User is already a player in this game");
            }
        }
        if(players.size() >= 5) {
            throw new IllegalStateException("Max number of players reached!");
        }
        //assigns player color
        newPlayer.setPlayerColor(playerColorEnum.values()[players.size()]);
        //draws new player's starting hand
        for(int i = 0; i<4; i++) {
        	newPlayer.addTrainCardToHand(drawTrainCard());
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

    public boolean isGameStarted(){return gameStarted;}

    public void startGame() {
        //if (players.size() < 2){
        //    throw new IllegalStateException("Insufficient number of players to start game!");
        //}
        if(players.size() > 5){
            throw new IllegalStateException("Too many players to start game!");
        }

        this.gameStarted = true;
        
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
    
    public playerModel getPlayerByID(usernameModel username) {
    	for (playerModel player : this.players) {
    		if(player.getUserName().equals(username)) {
    			return player;
    		}
    	}
    	return null;
    }
    
    //Returns a list of int arrays
    // stats[0] = numbers of each color train card held by the player [int array length 9]
    // stats[1...n] = stats for player 1, player 2... player n [int array length 4]
    public List<int[]> getStats(usernameModel username) {
    	List<int[]> stats = new ArrayList<int[]>();

    	List<trainCardModel> curPlayerHand = getPlayerByID(username).getTrainCardHand();
    	int[] cardTypes = new int[9];
    	for (int i = 0; i < curPlayerHand.size(); i++) {
    		cardTypes[curPlayerHand.get(i).getColor().ordinal()] += 1;
    		}
    	stats.add(cardTypes);
    	for (int i = 0; i < players.size(); i++) {stats.add(players.get(i).getStats());}
    	return stats;
    }
    
}
