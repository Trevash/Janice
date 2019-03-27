package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.models.DestinationCardModel;

import java.util.List;

public class ClientChooseDestCardState extends AbstractClientGameState {


    private List<DestinationCardModel> drawnCards;

    public ClientChooseDestCardState(AbstractClientGameState prevState, List<DestinationCardModel> drawnCards) {
        super(prevState);
        this.drawnCards = drawnCards;
    }

    @Override
    public boolean canDrawTrainCards() {
        return false;
    }

    @Override
    public boolean canDrawDestCards() {
        return true; // simply reveal the cards already drawn
    }

    @Override
    public boolean canClaimRoute() {
        return false;
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        return drawnCards; // Don't want to draw new cards here
    }

    //@Override
    //public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
    //    super.returnDestinationCards(selectedCards, rejectedCards);
        // DO we need to advance the turn counter here? Should be advanced by the server
    //}
}
