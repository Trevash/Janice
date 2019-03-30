package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.chatboxModel;

public class GameStatusData {
    private int turnCounter;
    private int numTrainCards;
    private int numDestinationCards;
    private chatboxModel gameHistory;

    public GameStatusData(int turnCounter, chatboxModel gameHistory, int numTrainCards, int numDestinationCards) {
        this.turnCounter = turnCounter;
        this.gameHistory = gameHistory;
        this.numTrainCards = numTrainCards;
        this.numDestinationCards = numDestinationCards;
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
}
