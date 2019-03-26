package com.janus;

import com.bignerdranch.android.shared.models.*;
import com.bignerdranch.android.shared.resultobjects.GameListData;

import java.util.ArrayList;
import java.util.List;

public class ClientFacade {

    public interface Presenter {
        void updateUI();
    }

    private static ClientFacade facade;
    private Presenter currentPresenter;
    private Presenter statusPresenter;
    private ClientModel model = ClientModel.getInstance();

    private ClientFacade() {}

    public static ClientFacade getInstance() {
        if (facade == null) {
            facade = new ClientFacade();
        }
        return facade;
    }

    public void update() {
        currentPresenter.updateUI();
        if (statusPresenter != null) {
            statusPresenter.updateUI();
        }
    }

    public void setPresenter(Presenter presenter) {
        this.currentPresenter = presenter;
    }

    public void setStatusPresenter(Presenter statusPresenter) {
        this.statusPresenter = statusPresenter;
    }

    public void removeStatusPresenter() {
        statusPresenter = null;
    }

    public userModel getUser() {
        return model.getUser();
    }

    public void setUser(userModel user) {
        model.setUser(user);
        currentPresenter.updateUI();
        if (statusPresenter != null) {
            statusPresenter.updateUI();
        }
    }

    public List<gameModel> getServerGameList() {
        return model.getServerGameList();
    }

    public void setServerGameList(GameListData games) {
        model.setServerGameList(games);
        currentPresenter.updateUI();
        if (statusPresenter != null) {
            statusPresenter.updateUI();
        }
    }

    public gameModel getGame() {
        return model.getGame();
    }

    public void setGame(gameModel game) {
        model.setGame(game);
        currentPresenter.updateUI();
        if (statusPresenter != null) {
            statusPresenter.updateUI();
        }
    }

    public void setTurnCounter(int turnCounter) {
        model.setTurnCounter(turnCounter);
    }

    public chatboxModel getChatbox() {
        return model.getChatbox();
    }

    public void setChatbox(chatboxModel chatbox) {
        model.setChatbox(chatbox);
        currentPresenter.updateUI();
    }

    public void setHistory(chatboxModel gameHistory) {
        model.setGameHistory(gameHistory);
    }

    public chatboxModel getHistory() {
        return model.getHistory();
    }

    public List<int[]> getStats() {
        return getGame().getStats(getUser().getUserName());
    }

    public List<playerModel> getPlayers() {
        return getGame().getPlayers();
    }

    public boolean userCanDrawTrainCards() {
        return getGame().canDrawTrainCards();
    }

    public boolean userCanDrawDestCards() {
        return getGame().canDrawDestCards();
    }

    public boolean userCanClaimRoute() {
        return getGame().canClaimRoute();
    }
}
