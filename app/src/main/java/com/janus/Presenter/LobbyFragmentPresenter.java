package com.janus.Presenter;

public class LobbyFragmentPresenter {
    public interface View {
        void updateButtons(boolean isActive);
        void displayError(String message);
        void displaySuccess();
    }

    private LobbyFragmentPresenter.View view;

    public LobbyFragmentPresenter(View v) {
        this.view = v;
    }

    public void startGameClicked() {
        //Check to make sure everybody is ready
    }
}
