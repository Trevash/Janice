package com.janus;

import com.bignerdranch.android.shared.models.*;
import com.bignerdranch.android.shared.resultobjects.GameListData;

import java.util.List;

public class ClientFacade {

    public interface Presenter {
        void updateUI();
    }

    private static ClientFacade facade;
    private Presenter currentPresenter; // TODO from the looks of it, this sometimes gets out of sync
    private Presenter statusPresenter;
    private ClientModel model = ClientModel.getInstance();

    private ClientFacade() {
    }

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
        //return getGame().getStats(getUser().getUserName());
    	return getGame().getVariableStats();
    }

    public List<playerModel> getPlayers() {
        return getGame().getPlayers();
    }

    /**
     * method to get the player representing this client
     *
     * @return the playerModel representing this client
     */
    public playerModel getPlayer() {
        return getGame().getPlayerByUsername(model.getUser().getUserName());
    }

    public void setNumTrainCards(int numTrainCards) {
        getGame().setNumTrainCards(numTrainCards);
    }

    public void setNumDestinationCards(int numDestinationCards) {
        getGame().setNumDestinationCards(numDestinationCards);
    }

    public void setTrainCardDiscards(List<trainCardModel> trainCardDiscardPile) {
        getGame().setTrainCardDiscards(trainCardDiscardPile);
    }

    public void setFaceUpTrainCards(List<trainCardModel> faceUpTrainCards) {
        getGame().setFaceUpCards(faceUpTrainCards);
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

    public boolean userCanDrawLocomotive() {return getGame().canDrawLocomotive();}

    public void addDestinationCardsToHand(List<DestinationCardModel> selectedCards) {
        //getGame().
        getPlayer().addDestinationCards(selectedCards);
    }

    /**
     * method indicates to the game states that a train card was drawn
     */
    public void notifyTrainCardDrawn() {
        getGame().notifyTrainCardDrawn();
    }

    public int getMinKeepDestCards() {
        return getGame().minKeepDestCards();
    }
}
