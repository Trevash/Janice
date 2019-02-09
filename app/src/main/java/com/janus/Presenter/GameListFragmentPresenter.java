package com.janus.Presenter;

import com.bignerdranch.android.shared.models.gameModel;

public class GameListFragmentPresenter {

    public interface View {
        void updateButtons(boolean isActive);
        void displayError(String message);
        void displaySuccess();
    }

    private View view;
    private gameModel gameSelected;

    public GameListFragmentPresenter(View v) {
        this.view = v;
    }

    public void selectGame(gameModel gameSelected) {
        this.gameSelected = gameSelected;
    }

    public void joinGameClicked() {

    }

    public void createGameClicked() {
        view.updateButtons(false);
        CreateGameTask createGameTask = new CreateGameTask(this);
        createGameTask.execute();
    }
}
