package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.playerIDModel;

import java.util.List;

public class ReturnDestinationCardsRequest {
    private gameIDModel id;
    private playerIDModel playerID;
    private List<DestinationCardModel> selectedCards;
    private List<DestinationCardModel> rejectedCards;

    public List<DestinationCardModel> getRejectedCards() {
        return rejectedCards;
    }

    //public ReturnDestinationCardsRequest(gameIDModel gameID, List<DestinationCardModel> selectedCards,
    //                                     List<DestinationCardModel> rejectedCards) {
    //    id = gameID;
    //    this.selectedCards = selectedCards;
    //    this.rejectedCards = rejectedCards;
    //}

    public ReturnDestinationCardsRequest(gameIDModel id, playerIDModel playerID,
                                         List<DestinationCardModel> selectedCards,
                                         List<DestinationCardModel> rejectedCards) {
        this.id = id;
        this.playerID = playerID;
        this.selectedCards = selectedCards;
        this.rejectedCards = rejectedCards;
    }

    public gameIDModel getGameID() {
        return id;
    }

    public playerIDModel getPlayerID() {
        return playerID;
    }

    public List<DestinationCardModel> getSelectedCards() {
        return selectedCards;
    }
    
}
