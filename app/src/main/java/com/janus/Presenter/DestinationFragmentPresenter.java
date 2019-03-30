package com.janus.Presenter;


import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;
import com.janus.ClientModel;
import com.janus.Communication.ReturnDestinationCardsTask;

import java.util.ArrayList;
import java.util.List;

public class DestinationFragmentPresenter implements ClientFacade.Presenter, ReturnDestinationCardsTask.Caller {
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
        List<DestinationCardModel> rejectedCards = new ArrayList<>(availableCards);
        rejectedCards.removeAll(selectedCards);
        //gameIDModel gameID = model.getGame().getGameID();
        //model.getGame().returnRejectedDestinationCards(selectedCards, rejectedCards);
        //ReturnDestinationCardsRequest request = new ReturnDestinationCardsRequest(gameID, selectedCards, rejectedCards);
        ReturnDestinationCardsTask task = new ReturnDestinationCardsTask(this);
        //task.execute(request);
        task.execute(selectedCards, rejectedCards);
    }

    @Override
    public void onError(String s) {

    }
}
