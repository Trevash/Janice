package com.bignerdranch.android.shared.proxy;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.resultobjects.DestinationCardListModel;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
import com.bignerdranch.android.shared.resultobjects.Results;

import java.util.List;

public class DestinationCardDeckProxy implements IDestinationCardDeck {

    private IServer server;
    private gameIDModel gameID;
    int size = 30;

    public DestinationCardDeckProxy(IServer server, gameIDModel gameID) {
        this.server = server;
        this.gameID = gameID;
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        List<DestinationCardModel> list;// = new ArrayList<>();
        //return list;
        Results results = server.drawDestinationCards(new DrawDestinationCardsRequest(gameID));
        Object result = results.getData(DestinationCardListModel.class);
        // TODO what happens if the results contains an error message/exception? - currently doesn't handle
        list = ((DestinationCardListModel) result).getCardList();
        size -= list.size();
        return list;
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        Results results = server.returnDestinationCard(new ReturnDestinationCardsRequest(gameID, selectedCards, rejectedCards));
        // TODO what should occur when results contains an error message/exception?
        size -= selectedCards.size();
    }

    @Override
    public int size() {
        return size; // TODO this will be a difficult thing to implement
    }

    public void updateSize(int providedSize) {
        // TODO may want to log when the sizes are not equal
        size = providedSize;
    }
}
