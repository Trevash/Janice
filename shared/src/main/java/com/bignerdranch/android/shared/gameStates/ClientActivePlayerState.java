package com.bignerdranch.android.shared.gameStates;

import com.bignerdranch.android.shared.IServer;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;

import java.util.List;

public class ClientActivePlayerState extends AbstractClientGameState {


    public ClientActivePlayerState(IServer server, gameModel game, int destCardDeckSize) {
        super(server, game, destCardDeckSize);
    }

    @Override
    public boolean canDrawTrainCards() {
        return true;
    }

    @Override
    public boolean canDrawDestCards() {
        return true;
    }

    @Override
    public boolean canClaimRoute() {
        return true;
    }

    @Override
    public List<DestinationCardModel> drawDestinationCards() {
        // TODO provide error handling - also may want way to avoid two quick succession draws
        List<DestinationCardModel> drawnCards = super.drawDestinationCards();
        // do if succeeded
        super.updateGameState(new ClientChooseDestCardState(this, drawnCards));
        return drawnCards;
        // else if failed
            // error message - which would likely mean an exception
    }
}
