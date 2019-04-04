package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.interfaces.ITrainCardBank;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A class to hold all of the train card functionality for the game, excluding individual player
 * hands. A class to be used on the server side
 */
public class TrainCardBank implements ITrainCardBank { // TODO can this class be moved into the server?

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
     * method to shuffle the train cards that are currently in the train card deck.<--- ></--->
     * This method does not add take the discard pile and put it into the train card deck
     */
    private void shuffleTrainCards() {
        for (int i = 0; i < trainCardDeck.size(); i++) {
            int j = (int) (Math.random() * trainCardDeck.size());
            trainCardModel temp = trainCardDeck.get(i);
            trainCardDeck.set(i, trainCardDeck.get(j));
            trainCardDeck.set(j, temp);
        }
    }

    /**
     * moves the discard pile into the deck, then shuffles the deck
     */
    private void moveDiscardToDeck() {
        trainCardDeck.addAll(trainCardDiscard);
        trainCardDiscard = new ArrayList<>(); // quick, easy way to empty a list
        shuffleTrainCards();
    }


    public trainCardModel drawTrainCardFromDeck() {
        if(trainCardDeck.isEmpty()) {
            if(trainCardDiscard.isEmpty()) {
                // if discard empty, throw exception
                throw new IllegalStateException("There are no train cards in the deck or discard to draw");
            }
            // shuffle discard, move into train card deck
            moveDiscardToDeck();
        }
        return trainCardDeck.remove(trainCardDeck.size() - 1); // eliminate and return the top
    }

    public trainCardModel drawFaceUpTrainCard(int i) {
        if(i < 0 || i >= faceUpCards.size()) {
            throw new IllegalArgumentException("There isn't a face-up train card in slot " + i);
        }
        trainCardModel drawnCard = faceUpCards.get(i);
        // replace card, if possible
        if(canDrawTrainCardFromDeck()) {
            faceUpCards.set(i, drawTrainCardFromDeck());
            
            int locomotiveCount = 0;
            for (int j = 0; j< faceUpCards.size(); j++) {
            	if(faceUpCards.get(j).equals(Constants.TrainCards.LOCOMOTIVE)) {locomotiveCount += 1;}
            }
            if (locomotiveCount > 2) {
            	addToDiscard(faceUpCards);
            	faceUpCards.clear();
                faceUpCards.add(drawTrainCardFromDeck());
                faceUpCards.add(drawTrainCardFromDeck());
                faceUpCards.add(drawTrainCardFromDeck());
                faceUpCards.add(drawTrainCardFromDeck());
                faceUpCards.add(drawTrainCardFromDeck());
            }
            
            
        } else {
            // will need to move the train cards
            faceUpCards.remove(i);
        }
        return drawnCard;
    }

    public boolean canDrawTrainCardFromDeck() {
        return !trainCardDeck.isEmpty() || !trainCardDiscard.isEmpty();
    }

    public void addToDiscard(List<trainCardModel> discardedCards) {
        trainCardDiscard.addAll(discardedCards);
    }

    @Override
    public int deckSize() {
        return trainCardDeck.size();
    }

    @Override
    public int discardSize() {
        return trainCardDiscard.size();
    }

    public List<trainCardModel> getTrainCardDiscard() {
        return new ArrayList<>(trainCardDiscard);
    }

    @Override
    public List<trainCardModel> getFaceUpTrainCards() {
        // this makes a new copy of the list - likely a shallow copy, but changing this new list
        // should not change the list in the bank...
        return new ArrayList<>(faceUpCards);
    }
}
