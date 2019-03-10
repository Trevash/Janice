package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

public class ReturnDestinationCardsRequest {
    private gameIDModel id;
    private List<DestinationCardModel> destinationCards;

    public ReturnDestinationCardsRequest(gameIDModel gameID, List<DestinationCardModel> destinationCards) {
        id = gameID;
        this.destinationCards = destinationCards;
    }

    public gameIDModel getGameID() {
        return id;
    }

    public List<DestinationCardModel> getDestinationCards() {
        return destinationCards;
    }
    
}
