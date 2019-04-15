package com.janus.Presenter;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.janus.ClientFacade;
import com.janus.ClientModel;
import com.janus.Communication.ServerProxy;

import java.util.ArrayList;
import java.util.List;

public class MapFragmentPresenter implements ClientFacade.Presenter {
    public interface View {
        void updateTurnIndicator(List<playerModel> players);
        void updateRoutes(List<abstractRoute> routes);
        void updateButtons(boolean canDrawDestCards, boolean canClaimRoute, boolean canDrawTrainCards);
        void lastRoundToast();
        void endGame();
    }

    private MapFragmentPresenter.View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();
    private ServerProxy proxy = ServerProxy.getInstance();

    public MapFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI() {
        view.updateTurnIndicator(model.getGame().getPlayers());
        view.updateRoutes(model.getGame().getRoutes());
        view.updateButtons(facade.userCanDrawDestCards(), facade.userCanClaimRoute(), facade.userCanDrawTrainCards());
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public playerModel getPlayerByID(playerIDModel pidModel) {
        return model.getGame().getPlayerModelFromID(pidModel);
    }

    public boolean isPlayersTurn(playerIDModel pidModel) {
        return model.getGame().isPlayersTurn(pidModel);
    }

    public boolean outOfDestinationCards(){
        return model.getGame().getNumDestinationCards() == 0;
    }

    public void endGame(){
        view.endGame();
    }

    public void lastRound(){
        view.lastRoundToast();
    }

    public boolean connectedToServer(){
        return proxy.isClientConnected();
    }

}
