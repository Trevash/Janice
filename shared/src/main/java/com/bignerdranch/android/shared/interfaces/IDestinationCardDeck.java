package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.models.DestinationCardModel;

import java.util.List;

public interface IDestinationCardDeck {

    public List<DestinationCardModel> drawDestinationCards();

    public void returnDestinationCard(DestinationCardModel card);

    public int size();
}
