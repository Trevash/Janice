package com.bignerdranch.android.shared.proxy;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.resultobjects.DestinationCardListModel;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
import com.bignerdranch.android.shared.resultobjects.Results;

import java.util.List;

public class DestinationCardDeckProxy implements IDestinationCardDeck {

    private transient IServer server; // transient means that this won't get serialized
    private gameIDModel gameID;
    private playerIDModel clientID;
    private int size;

    public DestinationCardDeckProxy(IServer server, gameIDModel gameID, playerIDModel clientID, int size) {
        this.server = server;
        this.gameID = gameID;
        this.clientID = clientID;
        this.size = size;
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        List<DestinationCardModel> list;// = new ArrayList<>();
        //return list;
        Results results = server.drawDestinationCards(new DrawDestinationCardsRequest(gameID, clientID));
        Object result = results.getData(DestinationCardListModel.class);
        // TODO what happens if the results contains an error message/exception? - currently doesn't handle
        list = ((DestinationCardListModel) result).getCardList();
        size -= list.size();
        return list;
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        Results results = server.returnDestinationCard(new ReturnDestinationCardsRequest(gameID, clientID, selectedCards, rejectedCards));
        if(results.isSuccess()) {
            size += rejectedCards.size();
        }
    }

    @Override
    public int size() {
        return size;
    }

    public void updateSize(int providedSize) {
        size = providedSize;
    }
}
