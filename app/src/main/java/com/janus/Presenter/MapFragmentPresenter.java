package com.janus.Presenter;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.ArrayList;
import java.util.List;

public class MapFragmentPresenter implements ClientFacade.Presenter {
    public interface View {
        void updateTurnIndicator(List<playerModel> players);

        void updateRoutes(List<abstractRoute> routes);

        void updateDestinationsDrawable(boolean canDrawDestCards);
    }

    private MapFragmentPresenter.View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();

    public MapFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI() {
        view.updateTurnIndicator(model.getGame().getPlayers());
        view.updateRoutes(model.getGame().getRoutes());
        view.updateDestinationsDrawable(facade.userCanDrawDestCards());
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

    public boolean isCurrentPlayersTurn() {
        usernameModel username = model.getUser().getUserName();
        return isPlayersTurn(model.getGame().getPlayerByUsername(username).getId());
    }

    // a method that could be used to alter whether the destination card button is clickable
    //public void stateChanged() {
    //    view.updateDestinationsDrawable(facade.userCanDrawDestCards());
    //}
}
