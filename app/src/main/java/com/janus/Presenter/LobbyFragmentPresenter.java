package com.janus.Presenter;

import com.bignerdranch.android.shared.resultobjects.Results;

public class LobbyFragmentPresenter {

    public interface View {
        void updateButtons(boolean isActive);
        void displayError(String message);
        void displaySuccess();
    }

    private LobbyFragmentPresenter.View view;
    //private Player[] players;

    public LobbyFragmentPresenter(View v) {
        this.view = v;
    }

    public void startGameClicked() {
        //Call startGame on serverproxy
    }

    /*public void updatePlayerList(Player[] p) {
        this.players = p;
        checkButtons();
    }*/

    private void checkButtons() {
        //Check to make sure everybody is ready and that there are at least 2 players
        view.updateButtons(true);
    }

    @Override
    public void onCreateGameComplete(Results r) {
        //user.setGames(data.getGames());
        view.displaySuccess();
    }
}
