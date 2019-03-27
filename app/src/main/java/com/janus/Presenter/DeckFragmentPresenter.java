package com.janus.Presenter;

import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest;
import com.janus.ClientFacade;
import com.janus.ClientModel;
import com.janus.Communication.DrawTrainCardTask;

import java.util.List;

public class DeckFragmentPresenter implements ClientFacade.Presenter, DrawTrainCardTask.Caller{
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

    public void drawCard(int index){
        DrawTrainCardTask task = new DrawTrainCardTask(this);
        usernameModel username = model.getUser().getUserName();
        playerIDModel currentPlayerID = model.getGame().getPlayerByUsername(username).getId();
        DrawTrainCardRequest request =
                new DrawTrainCardRequest(model.getAuth(), index, currentPlayerID, model.getGame().getGameID());
        task.execute(request);
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    @Override
    public void onError(String s) {

    }
}
