package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

public class DestinationCardListModel {

    private List<DestinationCardModel> list;
    private gameIDModel gameID;

    public DestinationCardListModel(List<DestinationCardModel> list, gameIDModel gameID) {
        this.list = list;
        this.gameID = gameID;
    }

    public List<DestinationCardModel> getCardList() {
        return list;
    }

    public gameIDModel getGameID() {
        return gameID;
    }
}
