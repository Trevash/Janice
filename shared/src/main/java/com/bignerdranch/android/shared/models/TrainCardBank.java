package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A class to hold all of the train card functionality for the game, excluding individual player
 * hands. A class to be used on the server side
 */
public class TrainCardBank {

    private List<trainCardModel> trainCardDeck = new ArrayList<>();
    // Face-up
    private List<trainCardModel> faceUpCards = new ArrayList<>();
    // Discard
    private List<trainCardModel> trainCardDiscard = new LinkedList<>();

    public TrainCardBank() {
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
    }

    /**
     * method to shuffle the train cards that are currently in the train card deck
     */
    private void shuffleTrainCards() {
        for (int i = 0; i < trainCardDeck.size(); i++) {
            int j = (int) (Math.random() * trainCardDeck.size());
            trainCardModel temp = trainCardDeck.get(i);
            trainCardDeck.set(i, trainCardDeck.get(j));
            trainCardDeck.set(j, temp);
        }
    }

    
    public trainCardModel drawTrainCardFromDeck() {
        if(trainCardDeck.isEmpty()) {
            if(trainCardDiscard.isEmpty()) {
                // if discard empty, throw exception
                throw new IllegalStateException("There are no train cards in the deck or discard to draw");
            }
            // shuffle discard, move into train card deck
            trainCardDeck.addAll(trainCardDiscard);
            trainCardDiscard = new ArrayList<>(); // quick, easy way to empty a list
            shuffleTrainCards();
        }
        return trainCardDeck.remove(trainCardDeck.size() - 1); // eliminate and return the top
    }

    public boolean canDrawTrainCardFromDeck() {
        return !trainCardDeck.isEmpty() || !trainCardDiscard.isEmpty();
    }

}
