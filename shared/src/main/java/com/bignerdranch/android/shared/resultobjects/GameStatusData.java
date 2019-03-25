package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.chatboxModel;

public class GameStatusData {
    private int turnCounter;
    private chatboxModel gameHistory;

    public GameStatusData(int turnCounter, chatboxModel gameHistory){
        this.turnCounter = turnCounter;
        this.gameHistory = gameHistory;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public chatboxModel getGameHistory() {
        return gameHistory;
    }
}
