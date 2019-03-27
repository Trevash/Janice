package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.usernameModel;

import java.util.List;

public class ReturnDestinationCardData {

    private gameIDModel gameID;
    private usernameModel username;
    private List<DestinationCardModel> selectedCards;

    public ReturnDestinationCardData(gameIDModel gameID, usernameModel username, List<DestinationCardModel> cards){

        this.gameID = gameID;
        this.username = username;
        this.selectedCards = cards;
    }

    public gameIDModel getGameID() {
        return gameID;
    }

    public usernameModel getUsername() {
        return username;
    }
    
    public List<DestinationCardModel> getSelectedCards(){
    	return selectedCards;
    }
}
