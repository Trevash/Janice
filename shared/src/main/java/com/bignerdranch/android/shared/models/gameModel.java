package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.interfaces.IGameState;
import com.bignerdranch.android.shared.models.colors.playerColorEnum;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;

import java.util.ArrayList;
import java.util.Hashtable;
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
        this.turnCounter = 0;

        this.state = state;
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

    private void setRoutes() {
        routes.addAll(singleRouteModel.createSingleRoutes());
        if (players.size() > 3) {
            this.setDoubleRoutesMany();
        } else {
            this.setDoubleRoutesFew();
        }
    }

    private void setDoubleRoutesFew() {
        this.routes.add(new doubleRouteModelFew(Constants.Cities.VANCOUVER, Constants.Cities.SEATTLE, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.PORTLAND, Constants.Cities.SEATTLE, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.PORTLAND, Constants.Cities.SAN_FRANCISCO, 5, routeColorEnum.GREEN, routeColorEnum.PURPLE));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.SAN_FRANCISCO, Constants.Cities.LOS_ANGELES, 3, routeColorEnum.PURPLE, routeColorEnum.YELLOW));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.SAN_FRANCISCO, Constants.Cities.SALT_LAKE_CITY, 5, routeColorEnum.ORANGE, routeColorEnum.WHITE));

        this.routes.add(new doubleRouteModelFew(Constants.Cities.SALT_LAKE_CITY, Constants.Cities.DENVER, 3, routeColorEnum.RED, routeColorEnum.YELLOW));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.DENVER, Constants.Cities.KANSAS_CITY, 4, routeColorEnum.ORANGE, routeColorEnum.BLACK));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.DULUTH, Constants.Cities.OMAHA, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.OMAHA, Constants.Cities.KANSAS_CITY, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.KANSAS_CITY, Constants.Cities.OKLAHOMA_CITY, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));

        this.routes.add(new doubleRouteModelFew(Constants.Cities.OKLAHOMA_CITY, Constants.Cities.DALLAS, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.DALLAS, Constants.Cities.HOUSTON, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.KANSAS_CITY, Constants.Cities.ST_LOUIS, 2, routeColorEnum.PURPLE, routeColorEnum.BLUE));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.ST_LOUIS, Constants.Cities.CHICAGO, 2, routeColorEnum.GREEN, routeColorEnum.WHITE));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.CHICAGO, Constants.Cities.PITTSBURGH, 3, routeColorEnum.ORANGE, routeColorEnum.BLACK));

        this.routes.add(new doubleRouteModelFew(Constants.Cities.PITTSBURGH, Constants.Cities.NEW_YORK_CITY, 2, routeColorEnum.GREEN, routeColorEnum.WHITE));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.NEW_YORK_CITY, Constants.Cities.BOSTON, 2, routeColorEnum.YELLOW, routeColorEnum.RED));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.BOSTON, Constants.Cities.MONTREAL, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.NEW_YORK_CITY, Constants.Cities.WASHINGTON, 2, routeColorEnum.ORANGE, routeColorEnum.BLACK));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.WASHINGTON, Constants.Cities.RALEIGH, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));

        this.routes.add(new doubleRouteModelFew(Constants.Cities.RALEIGH, Constants.Cities.ATLANTA, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelFew(Constants.Cities.NEW_ORLEANS, Constants.Cities.ATLANTA, 4, routeColorEnum.YELLOW, routeColorEnum.ORANGE));
    }

    private void setDoubleRoutesMany() {
        this.routes.add(new doubleRouteModelMany(Constants.Cities.VANCOUVER, Constants.Cities.SEATTLE, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.PORTLAND, Constants.Cities.SEATTLE, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.PORTLAND, Constants.Cities.SAN_FRANCISCO, 5, routeColorEnum.GREEN, routeColorEnum.PURPLE));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.SAN_FRANCISCO, Constants.Cities.LOS_ANGELES, 3, routeColorEnum.PURPLE, routeColorEnum.YELLOW));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.SAN_FRANCISCO, Constants.Cities.SALT_LAKE_CITY, 5, routeColorEnum.ORANGE, routeColorEnum.WHITE));

        this.routes.add(new doubleRouteModelMany(Constants.Cities.SALT_LAKE_CITY, Constants.Cities.DENVER, 3, routeColorEnum.RED, routeColorEnum.YELLOW));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.DENVER, Constants.Cities.KANSAS_CITY, 4, routeColorEnum.ORANGE, routeColorEnum.BLACK));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.DULUTH, Constants.Cities.OMAHA, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.OMAHA, Constants.Cities.KANSAS_CITY, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.KANSAS_CITY, Constants.Cities.OKLAHOMA_CITY, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));

        this.routes.add(new doubleRouteModelMany(Constants.Cities.OKLAHOMA_CITY, Constants.Cities.DALLAS, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.DALLAS, Constants.Cities.HOUSTON, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.KANSAS_CITY, Constants.Cities.ST_LOUIS, 2, routeColorEnum.PURPLE, routeColorEnum.BLUE));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.ST_LOUIS, Constants.Cities.CHICAGO, 2, routeColorEnum.GREEN, routeColorEnum.WHITE));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.CHICAGO, Constants.Cities.PITTSBURGH, 3, routeColorEnum.ORANGE, routeColorEnum.BLACK));

        this.routes.add(new doubleRouteModelMany(Constants.Cities.PITTSBURGH, Constants.Cities.NEW_YORK_CITY, 2, routeColorEnum.GREEN, routeColorEnum.WHITE));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.NEW_YORK_CITY, Constants.Cities.BOSTON, 2, routeColorEnum.YELLOW, routeColorEnum.RED));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.BOSTON, Constants.Cities.MONTREAL, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.NEW_YORK_CITY, Constants.Cities.WASHINGTON, 2, routeColorEnum.ORANGE, routeColorEnum.BLACK));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.WASHINGTON, Constants.Cities.RALEIGH, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));

        this.routes.add(new doubleRouteModelMany(Constants.Cities.RALEIGH, Constants.Cities.ATLANTA, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        this.routes.add(new doubleRouteModelMany(Constants.Cities.NEW_ORLEANS, Constants.Cities.ATLANTA, 4, routeColorEnum.YELLOW, routeColorEnum.ORANGE));
    }

    private void shuffleTrainCards() {
        for (int i = 0; i < trainCardDeck.size(); i++) {
            int j = (int) (Math.random() * trainCardDeck.size());
            trainCardModel temp = trainCardDeck.get(i);
            trainCardDeck.set(i, trainCardDeck.get(j));
            trainCardDeck.set(j, temp);
        }
    }

    //Todo: Check for empty deck and other special cases - recommend moving decks into their own classes
    public trainCardModel drawTrainCardFromDeck() {
        int numCards = (trainCardDeck.size() - 1);
        trainCardModel card = this.trainCardDeck.get(numCards); //Get top card
        trainCardDeck.remove(numCards); //Eliminate top card from array
        return card;
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

    public void returnDestinationCards(List<DestinationCardModel> destinationCards) {
        // similar to draw destination cards: clients call method on server, server returns to the deck
        // inside of it.
        state.returnDestinationCards(destinationCards);
    }

    // host is the first player in the list
    public playerModel getHostPlayer() {
        return players.get(0);
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
        //if (players.size() < 2){
        //    throw new IllegalStateException("Insufficient number of players to start game!");
        //}
        if (players.size() > 5) {
            throw new IllegalStateException("Too many players to start game!");
        }

        this.gameStarted = true;
        this.setRoutes();

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
        for (int i = 0; i < players.size(); i++) {
            stats.add(players.get(i).getStats());
        }
        return stats;
    }

    public playerModel getPlayerModelFromID(playerIDModel id) {
        return mapPlayerIDToModel.get(id.getValue());
    }
}
