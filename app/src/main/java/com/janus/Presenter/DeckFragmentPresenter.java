package com.janus.Presenter;

import com.bignerdranch.android.shared.models.trainCardModel;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.List;

public class DeckFragmentPresenter implements ClientFacade.Presenter{
    public interface View {
        void updateDeck(List<trainCardModel> cards);
        void updateFaceUpCards(List<trainCardModel> cards);
    }
    private View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();

    public DeckFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI(){
        view.updateDeck(model.getGame().getTrainCardDeck());
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
