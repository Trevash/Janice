package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.models.DestinationCardModel;

import java.util.List;

public interface IGameState {

    public List<DestinationCardModel> drawDestinationCards();
    public void returnDestinationCards(List<DestinationCardModel> destinationCards);
    public int size();
}
