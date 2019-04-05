package com.janus.Presenter;

import com.bignerdranch.android.shared.models.colors.cardColorEnum;
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
        void updateDeckSize(int trainCardDeckSize);

        void updateFaceUpCards(List<trainCardModel> cards);

        void returnToMap();

        void errorToast(String message);
    }

    private View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();
    private int cardCounter = 0;

    public DeckFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI() {
        if(model.isYourTurn()){
            view.updateDeckSize(model.getGame().getNumTrainCards());
            view.updateFaceUpCards(model.getGame().getFaceUpCards()); // TODO faceUpCards is not on the client side during the initial part of the game
        } else {
            view.returnToMap();
        }
    }

    public void drawCard(int index) { //index 0 is face-down deck, 1-5 is face-up cards
        if(facade.userCanDrawTrainCards()) {
            boolean isLocomotive = false;
            if(index != 0) {
                isLocomotive = model.getGame().getFaceUpCards().get(index - 1).getColor() == cardColorEnum.LOCOMOTIVE;
            }
            if((isLocomotive && !facade.userCanDrawLocomotive()) && (index != 0)){
                view.errorToast("You already drew one card.  You can't draw a locomotive now!");
            } else {
                DrawTrainCardTask task = new DrawTrainCardTask(this);
                usernameModel username = model.getUser().getUserName();
                playerIDModel currentPlayerID = model.getGame().getPlayerByUsername(username).getId();

                DrawTrainCardRequest request =
                        new DrawTrainCardRequest(model.getUser().getAuthToken(), index, currentPlayerID, model.getGame().getGameID());
                task.execute(request);
                // TtRClient can inform the state that a train card was drawn, if successful
            }
        } else {
            view.errorToast("It's not your turn!");
        }
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    @Override
    public void onError(String s) {
        // TODO implement: currently doesn't do anything
    }
}
