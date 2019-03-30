package com.janus.Presenter;

import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest;
import com.janus.ClientFacade;
import com.janus.ClientModel;
import com.janus.Communication.DrawTrainCardTask;

import java.util.List;

public class DeckFragmentPresenter implements ClientFacade.Presenter, DrawTrainCardTask.Caller {
    public interface View {
        //void updateDeck(List<trainCardModel> cards);
        void updateDeckSize(int trainCardDeckSize);

        void updateFaceUpCards(List<trainCardModel> cards);

        void returnToMap();
    }

    private View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();
    private int cardCounter = 0;

    public DeckFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI() {
        view.updateDeckSize(model.getGame().getNumTrainCards());
        view.updateFaceUpCards(model.getGame().getFaceUpCards()); // TODO faceUpCards is not on the client side during the initial part of the game
    }

    public void drawCard(int index) {
        DrawTrainCardTask task = new DrawTrainCardTask(this);
        usernameModel username = model.getUser().getUserName();
        playerIDModel currentPlayerID = model.getGame().getPlayerByUsername(username).getId();
        DrawTrainCardRequest request =
                new DrawTrainCardRequest(model.getUser().getAuthToken(), index, currentPlayerID, model.getGame().getGameID());
        task.execute(request);
        /*if(!model.getGame().isPlayersTurn(currentPlayerID)){
            view.returnToMap();
        }*/
        cardCounter++;   //This is for testing purposes only
        if (cardCounter == 2) {
            cardCounter = 0;
            view.returnToMap();
        }
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    @Override
    public void onError(String s) {

    }
}
