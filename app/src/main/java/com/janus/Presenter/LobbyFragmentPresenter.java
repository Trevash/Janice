package com.janus.Presenter;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientModel;

public class LobbyFragmentPresenter implements ClientModel.CurrentView{

    private ClientModel model = ClientModel.getInstance();

    public interface View {
        void updateButtons(boolean isActive);
        void updateUI(gameModel game);
        void displayError(String message);
        void displaySuccess();
    }

    public void setFragment() {
        model.setCurrentView(this);
    }

    private LobbyFragmentPresenter.View view;
    //private Player[] players;

    public LobbyFragmentPresenter(View v) {
        this.view = v;
    }

    public void startGameClicked() {
        //Call startGame on serverproxy
    }

    public void readyClicked(){
        //Change ready/not ready
    }

    /*public void updatePlayerList(Player[] p) {
        this.players = p;
        checkButtons();
    }*/

    private void checkButtons() {
        //Check to make sure everybody is ready and that there are at least 2 players
        view.updateButtons(true);
    }

    public usernameModel getUsername(){
        usernameModel username = model.getUser().getUserName();
        return username;
    }

    public void updateUI() {
        gameModel g = model.getGame();
        view.updateUI(g);
    }
}
