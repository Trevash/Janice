package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.bignerdranch.android.shared.models.usernameModel;

import java.util.List;

public class DrawTrainCardData {
    private gameIDModel gameID;
    private List<trainCardModel> hand;
    private List<trainCardModel> faceUpCards;
    private int numTrainCards;
    private usernameModel username;

    public DrawTrainCardData(gameIDModel gameID, List<trainCardModel> hand, List<trainCardModel> faceUpCards, int numTrainCards, usernameModel username) {
        this.gameID = gameID;
        this.hand = hand;
        this.faceUpCards = faceUpCards;
        this.numTrainCards = numTrainCards;
        this.username = username;
    }


    public gameIDModel getGameID() {
        return gameID;
    }

    public List<trainCardModel> getHand() {
        return hand;
    }

    public usernameModel getUsername() {
        return username;
    }

    public List<trainCardModel> getFaceUpCards() {
        return faceUpCards;
    }

    public int getNumTrainCards() {
        return numTrainCards;
    }
}
