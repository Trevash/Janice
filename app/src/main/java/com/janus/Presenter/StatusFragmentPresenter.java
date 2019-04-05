package com.janus.Presenter;

import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.userModel;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.List;

public class StatusFragmentPresenter implements ClientFacade.Presenter {

    private View view;
    private ClientModel model = ClientModel.getInstance();
    private ClientFacade facade = ClientFacade.getInstance();

    public interface View {
        void updateUI();
        void lastRoundToast();
        void endGame();
    }

    public void updateUI() {
        if(model.getGame().isLastRound()){
            view.lastRoundToast();
        }
        view.updateUI();
    }

    public StatusFragmentPresenter(View v) {
        this.view = v;
    }

    public List<int[]> getStats() {
        return facade.getStats();
    }

    public List<playerModel> getPlayers() {
        return facade.getPlayers();
    }

    public userModel getCurrentPlayer() {
        return facade.getUser();
    }

    public void setStatusPresenter() {
        facade.setStatusPresenter(this);
    }

    public void removeStatusPresenter() {
        facade.removeStatusPresenter();
    }

    public void endGame(){
        view.endGame();
    }

    public void lastRound(){
        view.lastRoundToast();
    }
}
