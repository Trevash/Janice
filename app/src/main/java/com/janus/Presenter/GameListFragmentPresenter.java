package com.janus.Presenter;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientModel;
import com.janus.Communication.CreateGameTask;
import com.janus.Communication.JoinGameTask;

import java.util.List;

public class GameListFragmentPresenter implements JoinGameTask.Caller, CreateGameTask.Caller, ClientModel.CurrentView {

    public interface View {
        void updateButtons(boolean isActive);
        void displayError(String message);
        void updateGameList(List<gameModel> games);
        void displaySuccess();
    }

    private View view;
    private gameModel gameSelected;
    private ClientModel model = ClientModel.getInstance();

    public GameListFragmentPresenter(View v) {
        this.view = v;
    }

    public void setFragment() {
        model.setCurrentView(this);
    }

    public void selectGame(gameModel gameSelected) {
        this.gameSelected = gameSelected;
        view.updateButtons(true);
    }

    public void joinGameClicked() {
        view.updateButtons(false);
        JoinGameRequest request = new JoinGameRequest(gameSelected, model.getAuth());
        JoinGameTask joinGameTask = new JoinGameTask(this);
        joinGameTask.execute(request);
    }

    public void createGameClicked() {
        view.updateButtons(false);
        CreateGameTask createGameTask = new CreateGameTask(this);
        createGameTask.execute(model.getAuth());
    }

    @Override
    public void onError(String s) {
        view.displayError(s);
        view.updateButtons(true);
    }

    @Override
    public void onJoinGameComplete(Results r) {
        //JoinGameData data = (JoinGameData) r.getData();
        view.displaySuccess();
    }

    @Override
    public void onCreateComplete(Results r) {
        view.displaySuccess();
    }

    @Override
    public void updateUI() {
        view.updateGameList(model.getServerGameList());
    }
}
