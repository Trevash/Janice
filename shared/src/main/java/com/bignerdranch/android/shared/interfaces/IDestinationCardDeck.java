package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.models.DestinationCardModel;

import java.util.List;

public interface IDestinationCardDeck {

    public List<DestinationCardModel> drawDestinationCards();

    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards);

    public int size();
}
