package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.bignerdranch.android.shared.models.colors.playerColorEnum;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;

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
    private LinkedList<destinationCardModel> destinationCardDeck = new LinkedList<>();
        // Face-up
    private List<trainCardModel> faceUpCards = new ArrayList<>();
        // Discard
    private LinkedList trainCardDiscard = new LinkedList();


    public gameModel(String newGameName, playerModel hostPlayer) {
        gameID = new gameIDModel();
        setGameName(newGameName);
        gameStarted = false;
        players.add(hostPlayer);
        chatbox = new chatboxModel();
        this.setGameRoutesAndDecks();
        this.turnCounter = 0;
    }

    private void setGameRoutesAndDecks() {

        //Create city models here
        cityModel losAngelas = new cityModel("Los Angelas");
        cityModel newYork = new cityModel("New York");

        //Create default routes here
        this.routes.add(new singleRouteModel(losAngelas, newYork, 5, routeColorEnum.WHITE));

        //Create dest cards deck here
        this.destinationCardDeck.add(new destinationCardModel(losAngelas, newYork, 20));

        //Create train card deck here (shuffle?)
        this.trainCardDeck.add(new trainCardModel(cardColorEnum.WHITE));
        this.trainCardDeck.add(new trainCardModel(cardColorEnum.BLUE));
        this.trainCardDeck.add(new trainCardModel(cardColorEnum.RED));
        this.trainCardDeck.add(new trainCardModel(cardColorEnum.GREEN));
        this.trainCardDeck.add(new trainCardModel(cardColorEnum.BLACK));
        this.trainCardDeck.add(new trainCardModel(cardColorEnum.YELLOW));

        //Draw 5 cards from deck, assign to the faceUp stuff
        this.faceUpCards.add(this.drawTrainCard());
        this.faceUpCards.add(this.drawTrainCard());
        this.faceUpCards.add(this.drawTrainCard());
        this.faceUpCards.add(this.drawTrainCard());
        this.faceUpCards.add(this.drawTrainCard());
    }

    //Todo: Check for empty deck and other special cases
    private trainCardModel drawTrainCard() {
        int numCards = (trainCardDeck.size() - 1);
        trainCardModel card = this.trainCardDeck.get(numCards); //Get top card
        trainCardDeck.remove(numCards); //Eliminate top card from array
        return card;
    }

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
        newPlayer.drawStartingTrainCardHand();
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
        if (players.size() < 2){
            throw new IllegalStateException("Insufficient number of players to start game!");
        }
        else if(players.size() > 5){
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
}
