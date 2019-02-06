package com.janus.Presenter;

public class GameListFragmentPresenter {

    public interface View {
        void updateButtons(boolean isActive);
        void displayError(String message);
        void displaySuccess();
    }

    private View view;
    private Integer gameSelected;

    public GameListFragmentPresenter(View v) {
        this.view = v;
    }

    public void joinGameClicked(Integer gameSelected) {
        this.gameSelected = gameSelected;

    }

    public void createGameClicked() {

    }
}
