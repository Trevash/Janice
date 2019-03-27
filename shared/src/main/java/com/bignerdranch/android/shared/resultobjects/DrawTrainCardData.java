package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.bignerdranch.android.shared.models.usernameModel;

public class DrawTrainCardData {
    private gameIDModel gameID;
    private trainCardModel returnCard;
    private usernameModel username;

    public DrawTrainCardData(gameIDModel gameID, trainCardModel returnCard, usernameModel username) {
        this.gameID = gameID;
        this.returnCard = returnCard;
        this.username = username;
    }


    public gameIDModel getGameID() {
        return gameID;
    }

    public trainCardModel getReturnCard() {
        return returnCard;
    }

    public usernameModel getUsername() {
        return username;
    }
}
