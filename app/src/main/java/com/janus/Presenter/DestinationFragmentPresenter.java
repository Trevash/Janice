package com.janus.Presenter;


import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.ArrayList;
import java.util.List;

public class DestinationFragmentPresenter implements ClientFacade.Presenter {
    public interface View {
        void updateDestinationCards(List<DestinationCardModel> cards);
    }
    private DestinationFragmentPresenter.View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();

    public DestinationFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI(){
        view.updateDestinationCards(model.getGame().drawDestinationCards());
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public void selectDestinationCards(List<DestinationCardModel> selectedCards,
                                       List<DestinationCardModel> availableCards) {
        List<DestinationCardModel> returnCards = new ArrayList<>(availableCards);
        returnCards.removeAll(selectedCards);
        gameModel game = ClientModel.getInstance().getGame();
        game.returnDestinationCards(returnCards);
        game.getPlayerByID(ClientModel.getInstance().getUser().getUserName())
                .addDestinationCards(selectedCards);
    }
}
