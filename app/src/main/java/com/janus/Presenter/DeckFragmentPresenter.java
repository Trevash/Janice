package com.janus.Presenter;

import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.List;
import java.util.Map;

public class DeckFragmentPresenter implements ClientFacade.Presenter{
    public interface View {
        void updateDeck(List<trainCard> cards);
        void updateFaceUpCards(List<String> cards);
    }
    private View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();

    public DeckFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI(){
        view.updateDeck(model.getGame().getDeck());
        view.updateFaceUpCards(model.getGame().getFaceUpCards());
    }

    void drawCard(int index){
        //Create a drawCard task?
        //Create drawCardRequest?
        //drawCardTask.execute(drawCardRequest);
    }

    public void setFragment() {
        facade.setPresenter(this);
    }
}
