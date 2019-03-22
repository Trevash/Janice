package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.DestinationCardModel;

import java.util.List;

public class DestinationCardListModel {

    List<DestinationCardModel> list;

    public DestinationCardListModel(List<DestinationCardModel> list) {
        this.list = list;
    }

    public List<DestinationCardModel> getCardList() {
        return list;
    }

}
