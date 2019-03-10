package com.bignerdranch.android.shared.proxy;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.requestObjects.DrawDestinationCardsRequest;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;

import java.util.ArrayList;
import java.util.List;

public class DestinationCardDeckProxy implements IDestinationCardDeck {

    private IServer server;
    private gameIDModel gameID;

    public DestinationCardDeckProxy(IServer server, gameIDModel gameID) {
        this.server = server;
        this.gameID = gameID;
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        List<DestinationCardModel> list = new ArrayList<>();
        list.add(Constants.DestinationCards.BOSTON_MIAMI);
        list.add(Constants.DestinationCards.CALGARY_SALT_LAKE);
        list.add(Constants.DestinationCards.LITTLE_ROCK_WINNIPEG);
        return list;
        // TODO figure out later
        //return server.drawDestinationCards(new DrawDestinationCardsRequest());
    }

    @Override
    public void returnDestinationCards(List<DestinationCardModel> cards) {
        server.returnDestinationCard(new ReturnDestinationCardsRequest(gameID, cards));
    }

    @Override
    public int size() {
        return 0;
    }
}
