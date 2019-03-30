package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.chatboxModel;
import com.bignerdranch.android.shared.models.trainCardModel;

import java.util.List;

public class GameStatusData {
    private int turnCounter;

    private int numTrainCards;
    private List<trainCardModel> faceUpTrainCards;
    // TODO this probably should be getting used somewhere: people might be interested in the discard pile
    //private int numTrainCardDiscard;
    private List<trainCardModel> trainCardDiscard;

    private int numDestinationCards;
    private chatboxModel gameHistory;

    public GameStatusData(int turnCounter, chatboxModel gameHistory, int numTrainCards,
                          List<trainCardModel> faceUpTrainCards, List<trainCardModel> trainCardDiscard,
                          int numDestinationCards) {
        this.turnCounter = turnCounter;
        this.gameHistory = gameHistory;
        this.numTrainCards = numTrainCards;
        this.numDestinationCards = numDestinationCards;
        this.faceUpTrainCards = faceUpTrainCards;
        //this.numTrainCardDiscard = numTrainCardDiscard;
        this.trainCardDiscard = trainCardDiscard;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public chatboxModel getGameHistory() {
        return gameHistory;
    }

    public int getNumTrainCards() {
        return numTrainCards;
    }

    public int getNumDestinationCards() {
        return numDestinationCards;
    }

    public List<trainCardModel> getFaceUpTrainCards() {
        return faceUpTrainCards;
    }

    public List<trainCardModel> getTrainCardDiscard() {
        return trainCardDiscard;
    }
}
