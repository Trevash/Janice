package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

public class ReturnDestinationCardsRequest {
    private gameIDModel id;
    private List<DestinationCardModel> selectedCards;
    private List<DestinationCardModel> rejectedCards;

    public gameIDModel getId() {
        return id;
    }

    public List<DestinationCardModel> getRejectedCards() {
        return rejectedCards;
    }

    public ReturnDestinationCardsRequest(gameIDModel gameID, List<DestinationCardModel> selectedCards,
                                         List<DestinationCardModel> rejectedCards) {
        id = gameID;
        this.selectedCards = selectedCards;
        this.rejectedCards = rejectedCards;
    }

    public gameIDModel getGameID() {
        return id;
    }

    public List<DestinationCardModel> getSelectedCards() {
        return selectedCards;
    }
    
}
