package com.janus.Presenter;

import com.bignerdranch.android.shared.models.playerModel;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.List;

public class MapFragmentPresenter implements ClientFacade.Presenter {
    public interface View {
        void updateTurnIndicator(List<playerModel> players);
    }
    private MapFragmentPresenter.View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();

    public MapFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI(){
        view.updateTurnIndicator(model.getGame().getPlayers());
    }

    public void setFragment() {
        facade.setPresenter(this);
    }
}
