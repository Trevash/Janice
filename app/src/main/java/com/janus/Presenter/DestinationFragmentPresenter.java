package com.janus.Presenter;


import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;
import com.janus.ClientModel;
import com.janus.Communication.ReturnDestinationCardsTask;
import com.janus.Communication.ServerProxy;

import java.util.ArrayList;
import java.util.List;

public class DestinationFragmentPresenter implements ClientFacade.Presenter, ReturnDestinationCardsTask.Caller {
    public interface View {
        void updateDestinationCards(List<DestinationCardModel> cards, int minDestinationCards);
        void endGame();
    }
    private DestinationFragmentPresenter.View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();
    private ServerProxy proxy = ServerProxy.getInstance();

    public DestinationFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI(){
        view.updateDestinationCards(model.getGame().drawDestinationCards(facade.getPlayer().getId()), facade.getMinKeepDestCards());
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public void selectDestinationCards(List<DestinationCardModel> selectedCards,
                                       List<DestinationCardModel> availableCards) {
        List<DestinationCardModel> rejectedCards = new ArrayList<>(availableCards);
        rejectedCards.removeAll(selectedCards);
        ReturnDestinationCardsTask task = new ReturnDestinationCardsTask(this);
        task.execute(selectedCards, rejectedCards);
    }

    public int getMinDestinationCards(){
       return facade.getMinKeepDestCards();
    }

    @Override
    public void onError(String s) {

    }

    public void endGame(){
        view.endGame();
    }

    public void lastRound(){}

    public boolean connectedToServer(){
        return proxy.isClientConnected();
    }
}
