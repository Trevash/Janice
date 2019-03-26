package com.janus.Presenter;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;
import com.janus.Communication.CreateGameTask;
import com.janus.Communication.JoinGameTask;

import java.util.List;

public class GameListFragmentPresenter implements JoinGameTask.Caller, CreateGameTask.Caller, ClientFacade.Presenter {

    public interface View {
        void updateGameListButtons(boolean isActive);
        void displayGameListError(String message);
        void updateGameList(List<gameModel> games);
        void displayGameListSuccess();
    }

    private View view;
    private gameModel gameSelected;
    private ClientFacade facade = ClientFacade.getInstance();

    public GameListFragmentPresenter(View v) {
        this.view = v;
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public void selectGame(gameModel gameSelected) {
        this.gameSelected = gameSelected;
        view.updateGameListButtons(true);
    }

    public gameModel getGameSelected() {
        return gameSelected;
    }

    public void joinGameClicked() {
        view.updateGameListButtons(false);
        JoinGameRequest request = new JoinGameRequest(gameSelected, facade.getUser().getAuthToken());
        JoinGameTask joinGameTask = new JoinGameTask(this);
        joinGameTask.execute(request);
    }

    public void createGameClicked() {
        view.updateGameListButtons(false);
        CreateGameTask createGameTask = new CreateGameTask(this);
        createGameTask.execute(facade.getUser().getAuthToken());
    }

    @Override
    public void onError(String s) {
        view.displayGameListError(s);
        view.updateGameListButtons(true);
    }

    @Override
    public void onJoinGameComplete(Results r) {
        //JoinGameData data = (JoinGameData) r.getData();
        view.displayGameListSuccess();
    }

    @Override
    public void onCreateComplete(Results r) {
        view.displayGameListSuccess();
    }

    @Override
    public void updateUI() {
        view.updateGameList(facade.getServerGameList());
    }
}
