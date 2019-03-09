package com.janus.Presenter;


import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.List;

public class DestinationFragmentPresenter implements ClientFacade.Presenter {
    public interface View {
        void updateDestinationCards(/*List<DestinationCardModel> cards*/);
    }
    private DestinationFragmentPresenter.View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();

    public DestinationFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI(){
        view.updateDestinationCards(/*model.getGame().getDestinationCards()*/);
        //ToDo: Need a way of getting destination cards
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public void selectDestinationCards(int[] indices){
        //Start task to get destination cards
        //Request objects for getting destination cards
    }
}
